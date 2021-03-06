package cn.alphahub.mall.coupon.controller;

import cn.alphahub.common.constant.HttpStatus;
import cn.alphahub.common.core.controller.BaseController;
import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.coupon.domain.MemberPrice;
import cn.alphahub.mall.coupon.service.MemberPriceService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 商品会员价格Controller
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-14 18:57:50
 */
@RestController
@RequestMapping("coupon/memberprice")
public class MemberPriceController extends BaseController {
    @Autowired
    private MemberPriceService memberPriceService;

    /**
     * 查询商品会员价格列表
     *
     * @param page        当前页码,默认第1页
     * @param rows        显示行数,默认10条
     * @param orderColumn 排序排序字段,默认不排序
     * @param isAsc       排序方式,desc或者asc
     * @param memberPrice 商品会员价格,查询字段选择性传入,默认为等值查询
     * @return 商品会员价格分页数据
     */
    @GetMapping("/list")
    public BaseResult<PageResult<MemberPrice>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "orderColumn", defaultValue = "") String orderColumn,
            @RequestParam(value = "isAsc", defaultValue = "") String isAsc,
            MemberPrice memberPrice
    ) {
        PageDomain pageDomain = new PageDomain(page, rows, orderColumn, isAsc);
        PageResult<MemberPrice> pageResult = memberPriceService.queryPage(pageDomain, memberPrice);
        if (ObjectUtils.isNotEmpty(pageResult.getItems())) {
            return BaseResult.ok(pageResult);
        }
        return BaseResult.fail(HttpStatus.NOT_FOUND, "查询结果为空");
    }

    /**
     * 获取商品会员价格详情
     *
     * @param id 商品会员价格主键id
     * @return 商品会员价格详细信息
     */
    @GetMapping("/info/{id}")
    public BaseResult<MemberPrice> info(@PathVariable("id") Long id) {
        MemberPrice memberPrice = memberPriceService.getById(id);
        return ObjectUtils.anyNotNull(memberPrice) ? BaseResult.ok(memberPrice) : BaseResult.fail();
    }

    /**
     * 新增商品会员价格
     *
     * @param memberPrice 商品会员价格元数据
     * @return 成功返回true, 失败返回false
     */
    @PostMapping("/save")
    public BaseResult<Boolean> save(@RequestBody MemberPrice memberPrice) {
        boolean save = memberPriceService.save(memberPrice);
        return toOperationResult(save);
    }

    /**
     * 修改商品会员价格
     *
     * @param memberPrice 商品会员价格,根据id选择性更新
     * @return 成功返回true, 失败返回false
     */
    @PutMapping("/update")
    public BaseResult<Boolean> update(@RequestBody MemberPrice memberPrice) {
        boolean update = memberPriceService.updateById(memberPrice);
        return toOperationResult(update);
    }

    /**
     * 批量删除商品会员价格
     *
     * @param ids 商品会员价格id集合
     * @return 成功返回true, 失败返回false
     */
    @DeleteMapping("/delete/{ids}")
    public BaseResult<Boolean> delete(@PathVariable Long[] ids) {
        boolean delete = memberPriceService.removeByIds(Arrays.asList(ids));
        return toOperationResult(delete);
    }
}
