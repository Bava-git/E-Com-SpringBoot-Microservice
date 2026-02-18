package com.khapara.userservice.mapper;

import com.khapara.userservice.dto.ShipAddDTO;
import com.khapara.userservice.entity.ShippingAddress;

public class ShipAddMapper {

    public static ShippingAddress toEntity(ShipAddDTO addressDTO) {
        if (addressDTO == null) return null;

        ShippingAddress address = new ShippingAddress();

        address.setId(addressDTO.getId());
        address.setFullName(addressDTO.getFullName());
        address.setDoorNumber(addressDTO.getDoorNumber());
        address.setStreetName(addressDTO.getStreetName());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipcode(addressDTO.getZipcode());
        address.setNearByLandmark(addressDTO.getNearByLandmark());
        address.setAlternateEmail((addressDTO.getAlternateEmail() == null || addressDTO.getAlternateEmail().isEmpty()) ? null : addressDTO.getAlternateEmail());
        address.setAlternatePhoneNumber((addressDTO.getAlternatePhoneNumber() == null || addressDTO.getAlternatePhoneNumber().isEmpty()) ? null : addressDTO.getAlternatePhoneNumber());
        address.setAddressLabel(addressDTO.getAddressLabel());
        address.setUserId(addressDTO.getUserId());

         return address;
    }

    public static ShipAddDTO toDto(ShippingAddress address) {
        if (address == null) return null;

        ShipAddDTO addressDTO = new ShipAddDTO();

        addressDTO.setId(address.getId());
        addressDTO.setFullName(address.getFullName());
        addressDTO.setDoorNumber(address.getDoorNumber());
        addressDTO.setStreetName(address.getStreetName());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setZipcode(address.getZipcode());
        addressDTO.setNearByLandmark(address.getNearByLandmark());
        addressDTO.setAlternateEmail((address.getAlternateEmail() == null || address.getAlternateEmail().isEmpty()) ? null : address.getAlternateEmail());
        addressDTO.setAlternatePhoneNumber((address.getAlternatePhoneNumber() == null || address.getAlternatePhoneNumber().isEmpty()) ? null : address.getAlternatePhoneNumber());
        addressDTO.setAddressLabel(address.getAddressLabel());
        addressDTO.setUserId(address.getUserId());

        return addressDTO;
    }

}
