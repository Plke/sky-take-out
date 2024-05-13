package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CategoryMapper {
    /**
     * 更新信息
     * @param category
     */
    void update(Category category);
    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
     Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

     @Insert("insert into category " +
             "(name,type,sort,status,create_time,update_time,create_user,update_user)" +
             "values" +
             "(#{name},#{type},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Category category);

     @Delete("delete from category where id=#{id}")
    void deleteById(Long id);

     @Select("select * from category where type=#{type}  order by sort  , create_time desc")
    List<Category> getByType(Integer type);
}
