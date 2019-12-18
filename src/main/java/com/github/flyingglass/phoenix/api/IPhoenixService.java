package com.github.flyingglass.phoenix.api;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * @author fly
 * Created date 2019/12/16 16:23
 * desc:
 */
public interface IPhoenixService<T> extends IService<T> {
    /**
     * Upsert一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
     * @return boolean
     */
    @Override
    boolean save(T entity);

    /**
     * Upsert（批量）
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     * @return boolean
     */
    @Override
    boolean saveBatch(Collection<T> entityList, int batchSize);

    /**
     * TableId 注解存在更新记录，否插入一条记录
     *
     * @param entity 实体对象
     * @return boolean
     */
    @Override
    default boolean saveOrUpdate(T entity) {
        return save(entity);
    }

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     * @param batchSize  每次的数量
     * @return boolean
     */
    @Override
    default boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return saveBatch(entityList, batchSize);
    }
}
