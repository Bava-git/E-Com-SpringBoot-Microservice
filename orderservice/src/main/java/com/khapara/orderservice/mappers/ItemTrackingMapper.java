package com.khapara.orderservice.mappers;

import com.khapara.orderservice.dtos.ItemTrackingDTO;
import com.khapara.orderservice.entities.ItemTracking;
import org.mapstruct.Mapper;

@Mapper
public interface ItemTrackingMapper {

    ItemTracking toEntity(ItemTrackingDTO dto);

    ItemTrackingDTO toDto(ItemTracking itemTracking);

}
