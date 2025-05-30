package com.ecommerce.sportscenter.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketResponse {

    private String id;
    private List<BasketItemResponse> items;


}
