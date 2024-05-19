package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    void addSetmeal(SetmealDTO setmealDTO);

    SetmealVO getBYId(Long id);

    PageResult pageSetmeal(SetmealPageQueryDTO setmealPageQueryDTO);

    void updateStatus(Integer status, Long id);

    void updateSetmeal(SetmealDTO setmealDTO);

    void deleteByIds(List<Long> ids);

    List<Setmeal> getByCategoryId(Long categoryId);

    List<DishItemVO> getDishItemById(Long id);
}
