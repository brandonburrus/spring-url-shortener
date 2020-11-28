package com.brandonburrus.urlshortener.service.impl;

import com.brandonburrus.urlshortener.dto.ShortenedUrlDTO;
import com.brandonburrus.urlshortener.entity.UrlHost;
import com.brandonburrus.urlshortener.entity.UrlRoutePath;
import com.brandonburrus.urlshortener.repository.UrlHostRepository;
import com.brandonburrus.urlshortener.repository.UrlRoutePathRepository;
import com.brandonburrus.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final UrlHostRepository urlHostRepository;
    private final UrlRoutePathRepository urlRoutePathRepository;

    @Autowired
    public UrlShortenerServiceImpl(UrlHostRepository urlHostRepository, UrlRoutePathRepository urlRoutePathRepository) {
        this.urlHostRepository = urlHostRepository;
        this.urlRoutePathRepository = urlRoutePathRepository;
    }

    @Override
    public ShortenedUrlDTO shortenUrl(String rawUrl) throws MalformedURLException {
        URL url = new URL(rawUrl);
        String hostName = String.format("%s://%s", url.getProtocol(), url.getAuthority());
        String pathName = url.getFile();

        UrlHost urlHost = urlHostRepository
            .findByHostName(hostName)
            .orElseGet(() -> urlHostRepository.save(new UrlHost(hostName)));
        UrlRoutePath urlRoutePath = urlRoutePathRepository
            .findByPathNameAndHost(pathName, urlHost)
            .orElseGet(() -> {
                UrlRoutePath routePath = new UrlRoutePath(pathName);
                routePath.setHost(urlHost);
                return urlRoutePathRepository.save(routePath);
            });

        String shortenedUrl = String.format("/u/%s.%s", urlHost.getId(), urlRoutePath.getId());
        return new ShortenedUrlDTO(shortenedUrl, hostName + pathName);
    }
}
