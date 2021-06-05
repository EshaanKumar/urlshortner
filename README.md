# urlshortner

Url shortener service 

Url shortener is service that converts long urls into short aliases to save space when sharing urls in messages, twitter, presentations etc.
When user opens short url, it will be automatically redirected to original (long) url.

Using the In memory Database h2. To check the console for H2 memory use below url
```
    http://localhost:9000/h2-console                        //  Refer Table - Url
```


$ git clone https://github.com/EshaanKumar/urlshortner.git
```
    - Make sure you have access to local or any MySQL server.
    - Open project in your favorite editor and change application.properties file to point to your MySQL database
    - Build Spring project 
    - Open localhost:9000/swagger-ui.html to see endpoints.
    - SELECT URLCONTROLLER and method create-short (to convert long url to short)
           -  expireDate - Change this to ensure the expiry date is greater then today.
           -  longUrl - Provide the Long Url which has to be encoded
    - /url/version1/{shortUrl} ===> To get the longURl and traffic count.
```` 

Running Spring Boot Project should be all it will take to run application.





