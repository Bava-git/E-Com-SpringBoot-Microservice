package com.khapara.orderservice.mappers;

import com.khapara.orderservice.dtos.CardDetailsDTO;
import com.khapara.orderservice.entities.CardDetails;
import org.mapstruct.Mapper;

@Mapper
public interface CardDetailsMapper {

    CardDetails toEntity(CardDetailsDTO dto);

    CardDetailsDTO toDto(CardDetails cardDetails);


}
