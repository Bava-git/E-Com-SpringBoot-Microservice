package com.khapara.userservice.dto;

import lombok.Data;

@Data
public class UpdateShipAddDTO {

    private Long id;
    private ReqShippingAddressDTO updateAddress;

}
