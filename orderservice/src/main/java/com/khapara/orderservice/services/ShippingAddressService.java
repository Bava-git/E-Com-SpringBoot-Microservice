package com.khapara.orderservice.services;

import com.khapara.orderservice.dtos.ShippingAddressDTO;
import com.khapara.orderservice.entities.ShippingAddress;
import com.khapara.orderservice.mappers.ShippingAddressMapper;
import com.khapara.orderservice.repositories.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRep;
    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    public ShippingAddressDTO saveShippingAddress(ShippingAddressDTO dto) {
        ShippingAddress shippingAddress = shippingAddressMapper.toEntity(dto);
        shippingAddress = shippingAddressRep.save(shippingAddress);
        return shippingAddressMapper.toDto(shippingAddress);
    }

}
