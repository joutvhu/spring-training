package com.joutvhu.training.rest.model.view;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductKey implements Serializable {
    private static final long serialVersionUID = -7306172880627295337L;

    @NotNull(message = "Product ID can't be null.")
    private Long productId;
}
