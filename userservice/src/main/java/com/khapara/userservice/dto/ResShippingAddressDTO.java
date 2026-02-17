package com.khapara.userservice.dto;

import lombok.Data;

@Data
public class ResShippingAddressDTO {

    private Long id;
    private String consigneeName;
    private String fullAddress;
    private String nearByLandmark;
    private String alternateEmail;
    private String alternatePhoneNumber;
    private String addressLabel;

    private Long userId;
}
