package com.brandonburrus.urlshortener.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UrlRoutePath {

    @Id
    @GenericGenerator(name = "id", strategy = "com.brandonburrus.urlshortener.entity.id.UrlRoutePathIdGenerator")
    @GeneratedValue(generator = "id")
    @Column(columnDefinition = "varchar(16) binary")
    private String id;

    @Column
    @NonNull
    private String pathName;

    @ManyToOne
    private UrlHost host;

}
