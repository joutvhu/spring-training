package com.joutvhu.training.rest.model.entity;

import com.joutvhu.training.rest.validation.group.OnCreate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {
    private static final long serialVersionUID = 8323856313824154121L;

    @NotNull(message = "Product ID can't be null.", groups = {OnCreate.class})
    @Id
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @NotNull(message = "Product Name can't be null.")
    @Column(name = "PRODUCT_NAME")
    private String productName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    )
    private List<Category> categories;
}
