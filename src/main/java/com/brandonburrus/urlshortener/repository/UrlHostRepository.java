package com.brandonburrus.urlshortener.repository;

import com.brandonburrus.urlshortener.entity.UrlHost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlHostRepository extends CrudRepository<UrlHost, String> {

    Optional<UrlHost> findById(String id);
    Optional<UrlHost> findByHostName(String hostName);
    UrlHost save(UrlHost host);

}
