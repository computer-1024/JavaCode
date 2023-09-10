package com.citicbank;

public class Check_ddl_rule {

    private String[] array_data;

    public Check_ddl_rule() {
    }

    public void setArray_data(String[] array_data) {
        this.array_data = array_data;
    }

    //检查DDL脚本的路径排头
    //检查如果是建表语句，那么要有 drop table ，CREATE TABLE ,CREATE VIEW 和注释
    //检查如果是建视图语句，那么要有drop view, create view  和表注释和字段注释

}
