package com.khapara.userservice.service;

import com.khapara.userservice.dto.ReqShippingAddressDTO;
import com.khapara.userservice.dto.ResShippingAddressDTO;
import com.khapara.userservice.entity.ShippingAddress;
import com.khapara.userservice.exception.ResourceNotFoundException;
import com.khapara.userservice.mapper.ShippingAddressMapper;
import com.khapara.userservice.repository.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shipAddRep;

    public List<ResShippingAddressDTO> listAddress(Long userId) {
        return shipAddRep.findByUserId(userId)
                .stream()
                .map(ShippingAddressMapper::toDto)
                .toList();
    }

    @Transactional
    public ResShippingAddressDTO saveAddress(ReqShippingAddressDTO addressDTO) {
        ShippingAddress savedAddress = shipAddRep.save(ShippingAddressMapper.toEntity(addressDTO));
        return ShippingAddressMapper.toDto(savedAddress);
    }

    public ResShippingAddressDTO updateAddress(ReqShippingAddressDTO addressDTO) {
        shipAddRep.findById(addressDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        ShippingAddress address = ShippingAddressMapper.toEntity(addressDTO);
        shipAddRep.save(address);
        return ShippingAddressMapper.toDto(address);
    }

    public void deleteAddress(Long id, Long userId) {
        ShippingAddress address = shipAddRep.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        shipAddRep.delete(address);
    }


}
