package cn.alphahub.mall.site.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.site.domain.SiteOrderRule;
import cn.alphahub.mall.site.mapper.SiteOrderRuleMapper;
import cn.alphahub.mall.site.service.SiteOrderRuleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 场地预约订单规则表Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-16 18:16:14
 */
@Service("siteOrderRuleService")
public class SiteOrderRuleServiceImpl extends ServiceImpl<SiteOrderRuleMapper, SiteOrderRule> implements SiteOrderRuleService {

    /**
     * 查询场地预约订单规则表分页列表
     *
     * @param pageDomain    分页数据
     * @param siteOrderRule 分页对象
     * @return 场地预约订单规则表分页数据
     */
    @Override
    public PageResult<SiteOrderRule> queryPage(PageDomain pageDomain, SiteOrderRule siteOrderRule) {
        pageDomain.startPage();
        QueryWrapper<SiteOrderRule> wrapper = new QueryWrapper<>(siteOrderRule);

        // 这里可编写自己的业务查询wrapper，如果需要。
        // ...

        return getPageResult(wrapper);
    }

    /**
     * 根据查询构造器条件查询分页查询结果
     *
     * @param wrapper <b>场地预约订单规则表<b/>实体对象封装操作类
     * @return 实体对象分页查询结果
     */
    private PageResult<SiteOrderRule> getPageResult(QueryWrapper<SiteOrderRule> wrapper) {
        List<SiteOrderRule> list = this.list(wrapper);
        PageInfo<SiteOrderRule> pageInfo = new PageInfo<>(list);
        PageResult<SiteOrderRule> pageResult = PageResult.<SiteOrderRule>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
        return pageResult;
    }

}
