package com.joutvhu.training.rest.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {
    @Id
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private List<Product> products;
}
