package com.khapara.orderservice.dtos;

import lombok.Data;

@Data
public class ShippingAddressDTO {

    private String fullName;
    private String doorNumber;
    private String streetName;
    private String city;
    private String state;
    private String zipcode;
    private String nearByLandmark;
    private String email;
    private String phoneNumber;

}
