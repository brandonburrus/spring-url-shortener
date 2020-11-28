package com.brandonburrus.urlshortener.service;

import com.brandonburrus.urlshortener.dto.ShortenedUrlDTO;

import java.net.MalformedURLException;

public interface UrlShortenerService {

    ShortenedUrlDTO shortenUrl(String url) throws MalformedURLException;

}
