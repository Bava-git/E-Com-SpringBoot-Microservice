package com.khapara.orderservice.mappers;

import com.khapara.orderservice.dtos.ShippingAddressDTO;
import com.khapara.orderservice.entities.ShippingAddress;
import org.mapstruct.Mapper;

@Mapper
public interface ShippingAddressMapper {

    ShippingAddress toEntity(ShippingAddressDTO dto);

    ShippingAddressDTO toDto(ShippingAddress shippingAddress);
}
