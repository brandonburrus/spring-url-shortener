package com.brandonburrus.urlshortener.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UrlHost {

    @Id
    @GenericGenerator(name = "id", strategy = "com.brandonburrus.urlshortener.entity.id.UrlHostIdGenerator")
    @GeneratedValue(generator = "id")
    @Column(columnDefinition = "varchar(16) binary")
    private String id;

    @Column
    @NonNull
    private String hostName;

    @OneToMany
    private List<UrlRoutePath> routePaths;

}
