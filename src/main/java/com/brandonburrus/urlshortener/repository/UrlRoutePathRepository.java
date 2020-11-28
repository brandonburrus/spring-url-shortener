package com.brandonburrus.urlshortener.repository;

import com.brandonburrus.urlshortener.entity.UrlHost;
import com.brandonburrus.urlshortener.entity.UrlRoutePath;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRoutePathRepository extends CrudRepository<UrlRoutePath, String> {

    Optional<UrlRoutePath> findById(String id);
    Optional<UrlRoutePath> findByPathName(String pathName);
    Optional<UrlRoutePath> findByPathNameAndHost(String pathName, UrlHost urlHost);

    UrlRoutePath save(UrlRoutePath routePath);

}
