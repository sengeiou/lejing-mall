package cn.alphahub.mall.product.dao;

import cn.alphahub.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-01-31 04:31:16
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}