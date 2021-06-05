package com.eshaan.urlshortner.service;

import com.eshaan.urlshortner.dto.UrlLongRequest;
import com.eshaan.urlshortner.entity.Url;
import com.eshaan.urlshortner.repository.UrlRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final BaseConversion conversion;

    public UrlService(UrlRepository urlRepository, BaseConversion baseConversion) {
        this.urlRepository = urlRepository;
        this.conversion = baseConversion;
    }

    public String convertToShortUrl(UrlLongRequest request) {
        int count = 0;
        Url url = new Url();
        url.setLongUrl(request.getLongUrl());
        url.setExpiresDate(request.getExpiresDate());
        url.setCreatedDate(new Date());
        url.setCount(count);
        Url entity = urlRepository.save(url);

        return conversion.encode(entity.getId());
    }

    public Map getOriginalUrl(String shortUrl) {
        int count = 0;
        HashMap map = new HashMap();
        long id = conversion.decode(shortUrl);
        Url entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (entity.getExpiresDate() != null && entity.getExpiresDate().before(new Date())) {
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        } else {
            count = entity.getCount() + 1;
            entity.setCount(count);
            urlRepository.save(entity);
        }

        map.put("longUrl", entity.getLongUrl());
        map.put("traffic", count);

        return map ;
    }
}
