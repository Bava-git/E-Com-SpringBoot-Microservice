package com.khapara.orderservice.services;

import com.khapara.orderservice.dtos.ItemTrackingDTO;
import com.khapara.orderservice.entities.ItemTracking;
import com.khapara.orderservice.mappers.ItemTrackingMapper;
import com.khapara.orderservice.repositories.ItemTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemTrackingService {

    @Autowired
    private ItemTrackingRepository itemTrackingRep;
    @Autowired
    private ItemTrackingMapper itemTrackingMapper;

    public ItemTrackingDTO saveItemTracking(ItemTrackingDTO dto) {
        ItemTracking itemTracking = itemTrackingMapper.toEntity(dto);
        itemTracking = itemTrackingRep.save(itemTracking);
        return itemTrackingMapper.toDto(itemTracking);
    }

}
