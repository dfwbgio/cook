package com.mbc.cook.dto.recipe;

import com.mbc.cook.entity.recipe.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO{
    long cart_seq;
    String id, order_item;
    int price;
    String address;
    String status;

    public CartEntity getRecipeEntity(){
        return CartEntity.builder()
            .cart_seq(cart_seq)
            .id(id)
            .order_item(order_item)
            .price(price)
            .address(address)
            .status(status)
            .build();
    }
}