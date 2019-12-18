package com.github.flyingglass.phoenix.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author fly
 * date 2019/12/16 16:25
 * desc:
 */
public class PhoenixServiceImpl<M extends PhoenixMapper<T>, T> extends ServiceImpl<M, T> implements IPhoenixService<T>{

    /**
     * Upsert一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
     * @return boolean
     */
    @Override
    public boolean save(T entity) {
        return retBool(baseMapper.upsert(entity));
    }


    /**
     * Upsert（批量）
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = SqlHelper.table(currentModelClass()).getSqlStatement(PhoenixSqlMethod.UPSERT_ONE.getMethod());

        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int i = 0;
            for (T anEntityList: entityList) {
                batchSqlSession.insert(sqlStatement, anEntityList);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                ++i;
            }
            batchSqlSession.flushStatements();
        }

        return true;
    }

}
