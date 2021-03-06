package cn.alphahub.mall.product.controller;

import cn.alphahub.common.constant.HttpStatus;
import cn.alphahub.common.core.controller.BaseController;
import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.Attr;
import cn.alphahub.mall.product.service.AttrService;
import cn.alphahub.mall.product.vo.AttrRespVo;
import cn.alphahub.mall.product.vo.AttrVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 商品属性Controller
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-14 19:02:16
 */
@RestController
@RequestMapping("product/attr")
public class AttrController extends BaseController {
    @Autowired
    private AttrService attrService;

    /**
     * 查询属性base list
     *
     * @param page        当前页码,默认第1页
     * @param rows        显示行数,默认10条
     * @param orderColumn 排序排序字段,默认不排序
     * @param isAsc       排序方式,desc或者asc
     * @param key         搜索关键字
     * @param catelogId   三级分类id
     * @return 分页列表
     */
    @GetMapping("/base/list/{catelogId}")
    public BaseResult<PageResult<AttrRespVo>> baseList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "orderColumn", defaultValue = "") String orderColumn,
            @RequestParam(value = "isAsc", defaultValue = "") String isAsc,
            @RequestParam(value = "key", defaultValue = "") String key,
            @PathVariable(value = "catelogId") Long catelogId
    ) {
        PageDomain pageDomain = new PageDomain(page, rows, orderColumn, isAsc);
        PageResult<AttrRespVo> pageResult = attrService.queryPage(pageDomain, new Attr(), key, catelogId);
        return BaseResult.ok(pageResult);
    }

    /**
     * 查询商品属性列表
     *
     * @param page        当前页码,默认第1页
     * @param rows        显示行数,默认10条
     * @param orderColumn 排序排序字段,默认不排序
     * @param isAsc       排序方式,desc或者asc
     * @param attr        商品属性,查询字段选择性传入,默认为等值查询
     * @return 商品属性分页数据
     */
    @GetMapping("/list")
    public BaseResult<PageResult<Attr>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "orderColumn", defaultValue = "") String orderColumn,
            @RequestParam(value = "isAsc", defaultValue = "") String isAsc,
            Attr attr
    ) {
        PageDomain pageDomain = new PageDomain(page, rows, orderColumn, isAsc);
        PageResult<Attr> pageResult = attrService.queryPage(pageDomain, attr);
        if (ObjectUtils.isNotEmpty(pageResult.getItems())) {
            return BaseResult.ok(pageResult);
        }
        return BaseResult.fail(HttpStatus.NOT_FOUND, "查询结果为空");
    }

    /**
     * 获取商品属性详情
     *
     * @param attrId 商品属性主键id
     * @return 商品属性详细信息
     */
    @GetMapping("/info/{attrId}")
    public BaseResult<AttrRespVo> info(@PathVariable("attrId") Long attrId) {
        AttrRespVo attr = attrService.getAttrInfoById(attrId);
        return ObjectUtils.anyNotNull(attr) ? BaseResult.ok(attr) : BaseResult.fail();
    }

    /**
     * 新增商品属性
     *
     * @param attr 商品属性元数据
     * @return 成功返回true, 失败返回false
     */
    @PostMapping("/save")
    public BaseResult<Boolean> save(@RequestBody AttrVo attr) {
        boolean save = attrService.saveAttr(attr);
        return toOperationResult(save);
    }

    /**
     * 修改商品属性
     *
     * @param attr 商品属性,根据id选择性更新
     * @return 成功返回true, 失败返回false
     */
    @PutMapping("/update")
    public BaseResult<Boolean> update(@RequestBody AttrVo attr) {
        boolean update = attrService.updateAttrById(attr);
        return toOperationResult(update);
    }

    /**
     * 批量删除商品属性
     *
     * @param attrIds 商品属性id集合
     * @return 成功返回true, 失败返回false
     */
    @DeleteMapping("/delete/{attrIds}")
    public BaseResult<Boolean> delete(@PathVariable Long[] attrIds) {
        boolean delete = attrService.removeByIds(Arrays.asList(attrIds));
        return toOperationResult(delete);
    }
}
