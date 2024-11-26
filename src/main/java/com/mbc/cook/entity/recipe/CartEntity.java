package com.mbc.cook.entity.recipe;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "cart")
@SequenceGenerator(
    name = "cart_num",//시퀀스 이름X dto 내 아이디랑 같음
    sequenceName = "cart_seq",
    allocationSize = 1,
    initialValue = 1
)

public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cart_num")
    @Column
    long cart_seq;
    @Column
    String id;
    @Column
    String order_item;
    @Column
    int price;
    @Column
    String address;
    @Column
    String status;

    @Builder
    public CartEntity(long cart_seq, String id, String order_item, int price, String address, String status) {
        this.cart_seq = cart_seq;
        this.id = id;
        this.order_item = order_item;
        this.price = price;
        this.address = address;
        this.status = status;
    }
}