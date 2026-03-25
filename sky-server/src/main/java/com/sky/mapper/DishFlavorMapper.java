package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 新增菜品口味
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品ID删除口味
     * @param DishId
     */
    @Delete("delete from dish_flavor where dish_id = #{id}")
    void deleteByDishId(Long DishId);

    /**
     * 根据菜品ID批量删除口味
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根据菜品ID查询口味数据
     * @param DishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{DishId}")
    List<DishFlavor> getByDishId(Long DishId);
}
