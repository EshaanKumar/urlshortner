package com.eshaan.urlshortner.controller;

import com.eshaan.urlshortner.dto.UrlLongRequest;
import com.eshaan.urlshortner.service.UrlService;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/url/version1")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping("create-short")
    public String convertToShortUrl(@RequestBody UrlLongRequest request) {
        return urlService.convertToShortUrl(request);
    }

    @ApiOperation(value = "Find Original Url", notes = "Finds original url from short url and Traffic")
    @GetMapping(value = "{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public Map getAndRedirect(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl);
    }
}
