package com.joutvhu.training.rest.model.view;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LoginInformation implements Serializable {
    private static final long serialVersionUID = 2838421472174160319L;

    private String username;
    private String password;
}
