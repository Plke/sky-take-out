package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    /**
     * 根据分类ID查询菜品数量
     */
    @Select("select count(*) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);


    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    @Select("select * from dish where id=#{id}")
    Dish getById(Long id);


    Page<Dish> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 更新员工信息
     *
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    @Select("select * from dish where category_id=#{categoryId}")
    List<Dish> list(Long categoryId);


    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);
}
