package com.example.crud.dto;

public class ItemsDto {
//    items 안에 데이터 베이스 list
//    item_id int not null auto_increment,
//    item_name varchar(100) not null,
//    item_price int,
//    primary key(item_id)
    private int itemId;
    private String itemName;
    private int itemPrice;

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
