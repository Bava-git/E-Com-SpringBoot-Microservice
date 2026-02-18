package com.khapara.userservice.service;

import com.khapara.userservice.dto.ShipAddDTO;
import com.khapara.userservice.entity.ShippingAddress;
import com.khapara.userservice.exception.ResourceNotFoundException;
import com.khapara.userservice.mapper.ShipAddMapper;
import com.khapara.userservice.repository.ShipAddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipAddService {

    @Autowired
    private ShipAddRepository shipAddRep;

    public List<ShipAddDTO> listAddress(Long userId) {
        return shipAddRep.findByUserId(userId)
                .stream()
                .map(ShipAddMapper::toDto)
                .toList();
    }

    @Transactional
    public ShipAddDTO saveAddress(ShipAddDTO addressDTO) {
        ShippingAddress savedAddress = shipAddRep.save(ShipAddMapper.toEntity(addressDTO));
        return ShipAddMapper.toDto(savedAddress);
    }

    public ShipAddDTO updateAddress(ShipAddDTO addressDTO) {
        shipAddRep.findById(addressDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        ShippingAddress address = ShipAddMapper.toEntity(addressDTO);
        shipAddRep.save(address);
        return ShipAddMapper.toDto(address);
    }

    public void deleteAddress(Long id, Long userId) {
        ShippingAddress address = shipAddRep.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        shipAddRep.delete(address);
    }


}
