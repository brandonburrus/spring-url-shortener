package com.brandonburrus.urlshortener.service.impl;

import com.brandonburrus.urlshortener.dto.UrlDTO;
import com.brandonburrus.urlshortener.entity.UrlHost;
import com.brandonburrus.urlshortener.entity.UrlRoutePath;
import com.brandonburrus.urlshortener.repository.UrlHostRepository;
import com.brandonburrus.urlshortener.repository.UrlRoutePathRepository;
import com.brandonburrus.urlshortener.service.UrlRetrieverService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlRetrieverServiceImpl implements UrlRetrieverService {

    private final UrlHostRepository urlHostRepository;
    private final UrlRoutePathRepository urlRoutePathRepository;

    @Autowired
    public UrlRetrieverServiceImpl(UrlHostRepository urlHostRepository, UrlRoutePathRepository urlRoutePathRepository) {
        this.urlHostRepository = urlHostRepository;
        this.urlRoutePathRepository = urlRoutePathRepository;
    }

    @Override
    public UrlDTO retrieveUrl(String hostId, String pathId) throws NotFoundException {
        Optional<UrlHost> urlHost = urlHostRepository.findById(hostId);
        Optional<UrlRoutePath> urlRoutePath = urlRoutePathRepository.findById(pathId);

        if (urlHost.isPresent() && urlRoutePath.isPresent()) {
            String retrievedUrl = urlHost.get().getHostName() + urlRoutePath.get().getPathName();
            return new UrlDTO(retrievedUrl);
        } else {
            throw new NotFoundException(String.format("Unable to find short url %s.%s", hostId, pathId));
        }
    }
}
