package com.brandonburrus.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortenedUrlDTO {

    private String url;
    private String originalUrl;

}
