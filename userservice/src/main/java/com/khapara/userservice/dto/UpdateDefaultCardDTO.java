package com.khapara.userservice.dto;

import lombok.Data;

@Data
public class UpdateDefaultCardDTO {

    private Long id;
    private boolean isDefault;
    private Long userId;

}
