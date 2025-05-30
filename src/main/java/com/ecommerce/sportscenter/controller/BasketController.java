package com.ecommerce.sportscenter.controller;


import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.entity.BasketItem;
import com.ecommerce.sportscenter.model.BasketItemResponse;
import com.ecommerce.sportscenter.model.BasketResponse;
import com.ecommerce.sportscenter.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public List<BasketResponse> getBaskets() {
        return basketService.getBaskets();
    }

    @GetMapping("/{basketId}")
    public BasketResponse getBasketById(@PathVariable("basketId") String basketId) {
        return basketService.getBasketById(basketId);
    }

    @DeleteMapping("/{basketId}")
    public void deleteBasketById(@PathVariable("basketId") String basketId) {
        basketService.deleteBasketById(basketId);
    }

    @PostMapping
    public ResponseEntity<BasketResponse> addBasket(@RequestBody BasketResponse basketResponse) {

        // Convert this Basket response to basket Entity
        Basket basket = convertToBasketEntity(basketResponse);

        BasketResponse createdBasket = basketService.addBasket(basket);

        // Return the Created basket
        return new ResponseEntity<>(createdBasket, HttpStatus.CREATED);

    }

    private Basket convertToBasketEntity(BasketResponse basketResponse) {

        Basket basket = new Basket();
        basket.setId(basketResponse.getId());
        basket.setItems(mapBasketItemResponsesToEntities(basketResponse.getItems()));

        return basket;

    }

    private List<BasketItem> mapBasketItemResponsesToEntities(List<BasketItemResponse> itemResponses) {
        return itemResponses.stream()
                .map(this::convertToBasketItemEntity)
                .collect(Collectors.toList());
    }

    private BasketItem convertToBasketItemEntity(BasketItemResponse basketItem) {

        BasketItem basketItemEntity = new BasketItem();
        basketItemEntity.setId(basketItem.getId());
        basketItemEntity.setName(basketItem.getName());
        basketItemEntity.setDescription(basketItem.getDescription());
        basketItemEntity.setPrice(basketItem.getPrice());
        basketItemEntity.setPictureUrl(basketItem.getPictureUrl());
        basketItemEntity.setProductBrand(basketItem.getProductBrand());
        basketItemEntity.setProductType(basketItem.getProductType());
        basketItemEntity.setQuantity(basketItem.getQuantity());

        return basketItemEntity;
    }

}
