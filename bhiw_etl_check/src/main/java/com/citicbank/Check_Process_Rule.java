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
                && array_data[7].equalsIgnoreCase("Y")  // array_data[8] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[9] 是否备份GSQL
                && judge_add_entity();
    }

    public boolean judge_add_entity()
    {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==3;
    }

    //检查修改实体的配置
    public boolean process_modify_entity()
    {

        return  array_data[2].substring(0,6).equalsIgnoreCase("bhif_t") //匹配实体表名
                && array_data[3].equalsIgnoreCase("T") // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("变更实体")   // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")  // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[8] 是否更新调度
                && array_data[8].equalsIgnoreCase("Y") // array_data[9] 是否备份GSQL
                && judge_modify_entity();
    }

    public boolean judge_modify_entity()
    {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==2;
    }

    //检查新增视图的配置
    public boolean process_add_view()
    {
        return  array_data[2].substring(0,6).equalsIgnoreCase("bhif_v") //匹配视图名称
                && array_data[3].equalsIgnoreCase("V")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("新增视图")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[8] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[9] 是否备份GSQL
                && judge_add_view();
    }

    public boolean judge_add_view()
    {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==2;
    }

    //检查修改视图的配置
    public boolean process_modify_view()
    {
        return  array_data[2].substring(0,6).equalsIgnoreCase("bhif_v") //匹配视图名称
                && array_data[3].equalsIgnoreCase("V")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("变更视图")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[8] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[9] 是否备份GSQL
                && judge_modify_view();
    }

    public boolean judge_modify_view()
    {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==1;
    }

    public boolean process_logical_modify()
    {
        return  array_data[2].substring(0,6).equalsIgnoreCase("bhif_t") //匹配视图名称
                && array_data[3].equalsIgnoreCase("T")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("变更逻辑")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("N")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[8] 是否更新调度
                && array_data[8].equalsIgnoreCase("Y") // array_data[9] 是否备份GSQL
                && judge_logical_modify();
    }

    public boolean judge_logical_modify()
    {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==1;
    }

    public boolean process_depend_modify()
    {
        return  /*array_data[2].substring(0,6).equalsIgnoreCase("bhif_t") //匹配视图名称
                && array_data[3].equalsIgnoreCase("T")  // array_data[3] 模型类型
                &&*/ array_data[4].equalsIgnoreCase("变更依赖")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("N")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[7] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[8] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[9] 是否备份GSQL
                && judge_depend_modify();
    }

    public boolean judge_depend_modify()
    {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==1;
    }


    public boolean process_check() {

        return process_add_entity()||process_add_view()||process_modify_entity()||process_modify_view()||process_logical_modify()||process_depend_modify();
    }


}
