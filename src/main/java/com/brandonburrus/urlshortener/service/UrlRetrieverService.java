package com.brandonburrus.urlshortener.service;

import com.brandonburrus.urlshortener.dto.UrlDTO;
import javassist.NotFoundException;

public interface UrlRetrieverService {

    UrlDTO retrieveUrl(String hostId, String pathId) throws NotFoundException;
}
