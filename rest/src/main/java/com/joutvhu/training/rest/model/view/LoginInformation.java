package com.joutvhu.training.rest.model.view;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LoginInformation implements Serializable {
    private static final long serialVersionUID = 2838421472174160319L;

    @NotBlank(message = "Username can't be blank.")
    private String username;

    @NotBlank(message = "Password can't be blank.")
    private String password;
}
