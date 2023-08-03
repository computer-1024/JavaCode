package com.citicbank;

import java.io.File;

public class Check_Process_Rule {

    private  String[] array_data;
    private String configPath;
    public Check_Process_Rule() {

    }
    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public void setArray_data(String[] array_data) {
        this.array_data = array_data;
    }

    //检查新增实体的配置
    public boolean process_add_entity()
    {
        return  array_data[2].substring(0,6).equalsIgnoreCase("bhif_t") //匹配实体表名
                && array_data[3].equalsIgnoreCase("T") // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("新增实体")   // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")  // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[8] 是否备份GSQL
                && array_data[8].equalsIgnoreCase("Y") // array_data[9] 是否更新调度
                && judge_add_entity();
    }

    public boolean judge_add_entity()
    {
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists();
    }

    //检查修改实体的配置
    public boolean process_modify_entity()
    {
        return  array_data[2].substring(0,6).equalsIgnoreCase("bhif_t") //匹配实体表名
                && array_data[3].equalsIgnoreCase("T") // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("变更实体")   // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")  // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[8] 是否备份GSQL
                && array_data[8].equalsIgnoreCase("N") // array_data[9] 是否更新调度
                && judge_modify_entity();
    }

    public boolean judge_modify_entity()
    {
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists();
    }

    //检查新增视图的配置
    public boolean process_add_view()
    {
        return  array_data[2].substring(0,6).equalsIgnoreCase("bhif_v") //匹配视图名称
                && array_data[3].equalsIgnoreCase("V")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("新增视图")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[8] 是否备份GSQL
                && array_data[8].equalsIgnoreCase("Y") // array_data[9] 是否更新调度
                && judge_add_view();
    }

    public boolean judge_add_view()
    {
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists();
    }

    //检查修改视图的配置
    public boolean process_modify_view()
    {
        return  array_data[2].substring(0,6).equalsIgnoreCase("bhif_v") //匹配视图名称
                && array_data[3].equalsIgnoreCase("V")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("变更视图")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[8] 是否备份GSQL
                && array_data[8].equalsIgnoreCase("N") // array_data[9] 是否更新调度
                && judge_modify_view();
    }

    public boolean judge_modify_view()
    {
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists();
    }

    public boolean process_logical_modify()
    {
        return  array_data[2].substring(0,6).equalsIgnoreCase("bhif_t") //匹配视图名称
                && array_data[3].equalsIgnoreCase("T")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("变更逻辑")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("N")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[8] 是否备份GSQL
                && array_data[8].equalsIgnoreCase("N") // array_data[9] 是否更新调度
                && judge_logical_modify();
    }

    public boolean judge_logical_modify()
    {
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists();
    }



    public boolean process_check() {

        return process_add_entity()||process_add_view()||process_modify_entity()||process_modify_view()||process_logical_modify();
    }

/*    public String process_rst_print() {
        return array_data[2].toUpperCase() + (process_check()? ":流程化配置检查通过":":流程化配置检查不通过");
    }*/
}
