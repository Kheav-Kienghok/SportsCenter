package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.model.BasketResponse;

import java.util.List;

public interface BasketService {

    List<BasketResponse> getBaskets();
    BasketResponse getBasketById(String basketId);

    void deleteBasketById(String basketId);
    BasketResponse addBasket(Basket basket);

}
