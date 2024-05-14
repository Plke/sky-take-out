package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    @Transactional
    public void addDish(DishDTO dishDto) {
        Dish dish=new Dish();
        BeanUtils.copyProperties(dishDto,dish);

        dishMapper.insert(dish);
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        if(flavors!=null && flavors.size()>0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);

            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public DishVO getBYId(Long id) {
        Dish dish = dishMapper.getById(id);
        DishVO dishVO=new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(dishFlavorMapper.selectByID(id));
        return dishVO;
    }


    // TODO 处理分页page超过记录数的bug
    @Override
    public PageResult pageDish(DishPageQueryDTO dishPageQueryDTO) {
        //分页查询插件
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        Page<Dish> page = dishMapper.pageQuery(dishPageQueryDTO);

        long total = page.getTotal();
        List<Dish> records = page.getResult();

        return new PageResult(total, records);
    }

    /**
     * 更新菜品状态
     *
     * @param status
     * @param id
     */
    @Override
    public void updateStatus(Integer status, Long id) {
        Dish dish = Dish.builder()
                .status(status)
                .id(id)
                .build();
        dishMapper.update(dish);
    }

    @Override
    @Transactional
    @ApiOperation("更新菜品")
    public void updateDish(DishDTO dishDto) {
        Dish dish=new Dish();
        BeanUtils.copyProperties(dishDto,dish);

        dishMapper.update(dish);
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        if(flavors!=null && flavors.size()>0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
                dishFlavorMapper.update(dishFlavor);
            });

        }
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> list(Long categoryId) {
        List<Dish> dishes=dishMapper.list(categoryId);
        return dishes;
    }

    /**
     * 根据id批量删除菜品
     * @param ids
     */
    @Override
    public void deleteByIds(Long[] ids) {
        for(Long id:ids){
            dishMapper.delete(id);

        }
    }


}
