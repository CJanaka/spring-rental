package com.ang.rental.jwtutill;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SecurityDto {
    private Date expDate;
    private String userName;
}
