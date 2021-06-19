package com.joutvhu.training.rest.model.entity;

import com.joutvhu.training.rest.validation.group.OnCreate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
}
