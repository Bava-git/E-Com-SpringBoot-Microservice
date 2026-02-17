package com.khapara.userservice.mapper;

import com.khapara.userservice.dto.ReqShippingAddressDTO;
import com.khapara.userservice.dto.ResShippingAddressDTO;
import com.khapara.userservice.entity.ShippingAddress;

public class ShippingAddressMapper {

    public static ShippingAddress toEntity(ReqShippingAddressDTO addressDTO) {
        if (addressDTO == null) return null;

        ShippingAddress address = new ShippingAddress();

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

    public static ResShippingAddressDTO toDto(ShippingAddress address) {
        if (address == null) return null;

        ResShippingAddressDTO addressDTO = new ResShippingAddressDTO();

        addressDTO.setId(address.getId());
        addressDTO.setConsigneeName(address.getFullName());
        StringBuilder builder = new StringBuilder();
        builder.append(address.getDoorNumber())
                .append(", ").append(address.getStreetName())
                .append(", ").append(address.getCity())
                .append(", ").append(address.getState())
                .append(" - ").append(address.getZipcode());
        addressDTO.setFullAddress(builder.toString());
        addressDTO.setNearByLandmark(address.getNearByLandmark());
        addressDTO.setAlternateEmail((address.getAlternateEmail() == null || address.getAlternateEmail().isEmpty()) ? null : address.getAlternateEmail());
        addressDTO.setAlternatePhoneNumber((address.getAlternatePhoneNumber() == null || address.getAlternatePhoneNumber().isEmpty()) ? null : address.getAlternatePhoneNumber());
        addressDTO.setAddressLabel(address.getAddressLabel());
        addressDTO.setUserId(address.getUserId());

        return addressDTO;
    }

}
