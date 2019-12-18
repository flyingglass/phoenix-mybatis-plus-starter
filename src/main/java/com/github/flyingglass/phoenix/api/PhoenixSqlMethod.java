package com.github.flyingglass.phoenix.api;

/**
 * @author fly
 * date 2019/5/28 16:01
 * desc: 自定义的Phoenix Sql方法
 */
enum PhoenixSqlMethod {

    /**
     * Phoenix upsert method
     */
    UPSERT_ONE("upsert", "Phoenix插入一条数据（选择字段插入）", "<script>\nUPSERT INTO %s %s VALUES %s\n</script>");

    private final String method;
    private final String desc;
    private final String sql;

    PhoenixSqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }
}