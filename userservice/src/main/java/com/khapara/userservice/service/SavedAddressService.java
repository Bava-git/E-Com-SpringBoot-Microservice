package com.khapara.userservice.service;

import com.khapara.userservice.dto.SavedAddressDTO;
import com.khapara.userservice.entity.SavedAddress;
import com.khapara.userservice.exception.ResourceNotFoundException;
import com.khapara.userservice.mapper.SavedAddressMapper;
import com.khapara.userservice.repository.SavedAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SavedAddressService {

    @Autowired
    private SavedAddressRepository shipAddRep;

    public List<SavedAddressDTO> listAddress(Long userId) {
        return shipAddRep.findByUserId(userId)
                .stream()
                .map(SavedAddressMapper::toDto)
                .toList();
    }

    @Transactional
    public SavedAddressDTO saveAddress(SavedAddressDTO addressDTO) {
        SavedAddress savedAddress = shipAddRep.save(SavedAddressMapper.toEntity(addressDTO));
        return SavedAddressMapper.toDto(savedAddress);
    }

    public SavedAddressDTO updateAddress(SavedAddressDTO addressDTO) {
        shipAddRep.findById(addressDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        SavedAddress address = SavedAddressMapper.toEntity(addressDTO);
        shipAddRep.save(address);
        return SavedAddressMapper.toDto(address);
    }

    public void deleteAddress(Long id, Long userId) {
        SavedAddress address = shipAddRep.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        shipAddRep.delete(address);
    }


}
