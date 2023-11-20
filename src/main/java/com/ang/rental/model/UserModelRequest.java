package com.ang.rental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
public class UserModelRequest {
    private long userId;
    private Boolean isAdmin;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int contact;
    private String address;
    private boolean active;
    private boolean verify;
    private Set<Roles> roles = new HashSet<>();
    private List<ListingModel> listingModel = new ArrayList<>();
}
