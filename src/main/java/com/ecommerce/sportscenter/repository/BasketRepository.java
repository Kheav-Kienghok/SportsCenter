package com.ecommerce.sportscenter.repository;

import com.ecommerce.sportscenter.entity.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {

    void deleteBasketById(String id);

    Optional<Basket> getBasketById(String id);
}
