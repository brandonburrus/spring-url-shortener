package com.brandonburrus.urlshortener.entity.id;

import com.brandonburrus.urlshortener.util.ShortIdGenerator;

public class UrlRoutePathIdGenerator extends ShortIdGenerator {
    public UrlRoutePathIdGenerator() {
        super("url_route_path");
    }
}
