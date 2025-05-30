package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.entity.BasketItem;
import com.ecommerce.sportscenter.model.BasketItemResponse;
import com.ecommerce.sportscenter.model.BasketResponse;
import com.ecommerce.sportscenter.repository.BasketRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }


    @Override
    public List<BasketResponse> getBaskets() {

        log.info("Fetching All Baskets");

        List<Basket> basketList = (List<Basket>) basketRepository.findAll();

        // Now we will use stream operator to map with response
        List<BasketResponse> basketResponseList = basketList.stream()
                .map(this::convertToBasketResponse)
                .collect(Collectors.toList());

        log.info("Fetched Baskets");
        return basketResponseList;
    }

    private BasketResponse convertToBasketResponse(Basket basket) {

        if (basket == null) {
            return null;
        }

        List<BasketItemResponse> itemResponseList = basket.getItems().stream()
                .map(this::convertToBasketItemResponse)
                .collect(Collectors.toList());

        return BasketResponse.builder()
                .id(basket.getId())
                .items(itemResponseList)
                .build();

    }

    private BasketItemResponse convertToBasketItemResponse(BasketItem basketItem) {

        return BasketItemResponse.builder()
                .id(basketItem.getId())
                .name(basketItem.getName())
                .description(basketItem.getDescription())
                .price(basketItem.getPrice())
                .pictureUrl(basketItem.getPictureUrl())
                .productBrand(basketItem.getProductBrand())
                .productType(basketItem.getProductType())
                .quantity(basketItem.getQuantity())
                .build();
    }

    @Override
    public BasketResponse getBasketById(String basketId) {

        log.info("Fetching Basket by ID: {}", basketId);
        Optional<Basket> basketOptional = basketRepository.getBasketById(basketId);

        if (basketOptional.isPresent()) {
            Basket basket = basketOptional.get();
            log.info("Fetched Basket");
            return convertToBasketResponse(basket);
        } else {
            log.info("Basket not found");
            return null;
        }

    }

    @Override
    public void deleteBasketById(String basketId) {

        log.info("Deleting Basket by ID: {}", basketId);
        basketRepository.deleteBasketById(basketId);
        log.info("Deleted Basket");

    }

    @Override
    public BasketResponse addBasket(Basket basket) {

        log.info("Create new Basket");
        Basket newBasket = basketRepository.save(basket);
        log.info("Created new Basket");

        return convertToBasketResponse(newBasket);

    }
}
