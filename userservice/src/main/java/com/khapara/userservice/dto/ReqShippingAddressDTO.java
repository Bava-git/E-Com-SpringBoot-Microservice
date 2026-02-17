package com.khapara.userservice.dto;

import lombok.Data;

@Data
public class ReqShippingAddressDTO {

    private Long id;
    private String fullName;
    private String doorNumber;
    private String streetName;
    private String city;
    private String state;
    private String zipcode;
    private String nearByLandmark;
    private String alternateEmail;
    private String alternatePhoneNumber;
    private String addressLabel;

    private Long userId;

}
