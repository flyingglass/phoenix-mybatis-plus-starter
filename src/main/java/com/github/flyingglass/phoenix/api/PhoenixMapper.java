package com.github.flyingglass.phoenix.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author fly
 * Created date 2019/12/16 16:21
 * desc:
 */
public interface PhoenixMapper<T> extends BaseMapper<T> {

    /**
     * Phoenix HBase插入一条记录
     * @param entity 实体对象
     * @return 插入条数,1成功,0失败
     */
    int upsert(T entity);
}
