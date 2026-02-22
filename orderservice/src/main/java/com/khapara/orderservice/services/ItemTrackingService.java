package com.khapara.orderservice.services;

import com.khapara.orderservice.dtos.ItemTrackingDTO;
import com.khapara.orderservice.entities.ItemTracking;
import com.khapara.orderservice.mappers.ItemTrackingMapper;
import com.khapara.orderservice.repositories.ItemTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTrackingService {

    @Autowired
    private ItemTrackingRepository itemTrackingRep;
    @Autowired
    private ItemTrackingMapper itemTrackingMapper;

    public List<ItemTrackingDTO> saveItemTracking(List<ItemTrackingDTO> dto) {
        List<ItemTracking> itemTracking = dto.stream().map(itemTrackingMapper::toEntity).toList();
        itemTracking = itemTrackingRep.saveAll(itemTracking);
        return itemTracking.stream().map(itemTrackingMapper::toDto).toList();
    }

}
