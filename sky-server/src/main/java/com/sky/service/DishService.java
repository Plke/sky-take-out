package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DishService {
    /**
     * 添加菜品
     * @param dishDto
     */
    void addDish(DishDTO dishDto);

    DishVO getBYId(Long id);

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageDish(DishPageQueryDTO dishPageQueryDTO);

    void updateStatus(Integer status, Long id);

    void updateDish(DishDTO dishDto);

    List<Dish> list(Long categoryId);

    void deleteByIds(List<Long> ids);
}
