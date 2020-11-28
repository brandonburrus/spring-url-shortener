package com.brandonburrus.urlshortener.controller;

import com.brandonburrus.urlshortener.dto.ShortenedUrlDTO;
import com.brandonburrus.urlshortener.dto.UrlDTO;
import com.brandonburrus.urlshortener.service.UrlRetrieverService;
import com.brandonburrus.urlshortener.service.UrlShortenerService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class UrlShortenerController {

    UrlShortenerService urlShortener;
    UrlRetrieverService urlRetriever;

    @Autowired
    public UrlShortenerController(UrlShortenerService urlShortener, UrlRetrieverService urlRetriever) {
        this.urlShortener = urlShortener;
        this.urlRetriever = urlRetriever;
    }

    @PostMapping("/api/v1/shorten")
    public ResponseEntity<ShortenedUrlDTO> shorten(@RequestBody UrlDTO urlInput) throws MalformedURLException {
        ShortenedUrlDTO shortenedUrl = urlShortener.shortenUrl(urlInput.getUrl());
        log.info("Shortened URL {} to {}", urlInput.getUrl(), shortenedUrl.getUrl());
        return ResponseEntity.ok(shortenedUrl);
    }

    @PostMapping("/api/v1/shorten/multiple")
    public ResponseEntity<List<ShortenedUrlDTO>> shorten(@RequestBody List<String> urls) {
        List<ShortenedUrlDTO> shortenedUrls = urls
            .stream()
            .map(url -> {
                try {
                    return urlShortener.shortenUrl(url);
                } catch (MalformedURLException e) {
                    log.error(e.getMessage());
                    return null;
                }
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(shortenedUrls);
    }

    @GetMapping("/api/v1/url/{hostId}.{pathId}")
    public ResponseEntity<UrlDTO> retrieveUrl(@PathVariable String hostId, @PathVariable String pathId) throws NotFoundException {
        log.info("Looking up URL at ({}.{})", hostId, pathId);
        UrlDTO retrievedUrl = urlRetriever.retrieveUrl(hostId, pathId);
        return ResponseEntity.ok(retrievedUrl);
    }

    @GetMapping("/u/{hostId}.{pathId}")
    public void redirectToUrl(@PathVariable String hostId, @PathVariable String pathId, HttpServletResponse httpResponse) throws NotFoundException {
        UrlDTO retrievedUrl = urlRetriever.retrieveUrl(hostId, pathId);
        httpResponse.setHeader("Location", retrievedUrl.getUrl());
        httpResponse.setStatus(302);
    }
}
