package com.github.flyingglass.phoenix.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;

/**
 * @author fly
 * date 2019/12/21 21:42
 * desc:
 */
public class PhoenixDialect implements IDialect {
    @Override
    public DialectModel buildPaginationSql(String originalSql, long offset, long limit) {
        String sql = originalSql + " limit " + FIRST_MARK + " offset " + SECOND_MARK;
        return new DialectModel(sql, limit, offset).setConsumerChain();
    }
}
