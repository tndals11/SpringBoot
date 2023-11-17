package com.example.crud.mappers;


import com.example.crud.dto.ItemsDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {

    @Insert("INSERT INTO items VALUES(NULL, #{itemName}, #{itemPrice});")
    public void setAddItem(ItemsDto itemsDto);

    @Select("SELECT COUNT(*) FROM items")
    public int getCount();

    @Select("SELECT * FROM items ORDER BY item_id DESC")
    public List<ItemsDto> getItemList();

    @Delete("DELETE FROM items WHERE item_id = #{id}")
    public void deleteItem(int id);

    
    // where => item_id = pk 타입이므로 값이 하나만 출력되므로 배열 List가 아니라 객체로 뽑아낼수 있다
    @Select("SELECT * FROM items WHERE item_id = #{id}")
    public ItemsDto viewItem(int id);

    @Update("UPDATE items SET item_name = #{itemName}, item_price = #{itemPrice} WHERE item_id = #{itemId}")
    public  void setUpdateItem(ItemsDto itemsDto);



}
