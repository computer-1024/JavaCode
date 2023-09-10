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

    //实体-DDL/XLS/GSQL新增(新增存储策略)
    public boolean process_add_entity()  {
        return  array_data[2].substring(0,7).equalsIgnoreCase("bhif_t_") //匹配实体表名
                && array_data[3].equalsIgnoreCase("T") // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("实体-DDL/XLS/GSQL新增(新增存储策略)")   // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")  // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("Y") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_add_entity();
    }

    public boolean judge_add_entity()  {

        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==3
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql");
    }

    //实体-DDL/XLS/GSQL变更(备份GSQL)
    public boolean process_modify_entity()  {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配实体表名
                && array_data[3].equalsIgnoreCase("T") // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("实体-DDL/XLS/GSQL变更(备份GSQL)")   // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")  // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("Y") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_entity();
    }

    public boolean judge_modify_entity()  {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==3
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql");
    }

    //实体-DDL变更
    public boolean process_modify_entity_ddl()  {

        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配实体表名
                && array_data[3].equalsIgnoreCase("T") // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("实体-DDL变更")   // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")  // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_entity_ddl();
    }

    public boolean judge_modify_entity_ddl()  {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==1
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl");
    }

    //实体-GSQL变更(备份GSQL)
    public boolean process_modify_entity_gsql()  {

        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配实体表名
                && array_data[3].equalsIgnoreCase("T") // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("实体-GSQL变更(备份GSQL)")   // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("N")  // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("Y") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_entity_gsql();
    }

    public boolean judge_modify_entity_gsql()  {
        /* 判断文件名称和类型以及数量 */
        return  new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==1
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql");
    }

    //实体-XLS变更
    public boolean process_modify_entity_xls()
    {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配视图名称
                && array_data[3].equalsIgnoreCase("T")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("实体-XLS变更")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("N")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_entity_xls();
    }

    public boolean judge_modify_entity_xls()
    {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==1;
    }

    //实体-DDL/XLS变更
    public boolean process_modify_entity_ddl_xls()
    {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配视图名称
                && array_data[3].equalsIgnoreCase("T")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("实体-DDL/XLS变更")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_entity_ddl_xls();
    }

    public boolean judge_modify_entity_ddl_xls()
    {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==2
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl");
    }

    //实体-DDL/GSQL变更(备份GSQL)
    public boolean process_modify_entity_ddl_gsql()
    {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配视图名称
                && array_data[3].equalsIgnoreCase("T")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("实体-DDL/GSQL变更(备份GSQL)")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("Y") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_entity_ddl_gsql();
    }

    public boolean judge_modify_entity_ddl_gsql()
    {
        /* 判断文件名称和类型以及数量 */
        return new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==2
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql");
    }

    //实体-GSQL和XLS变更(备份GSQL)
    public boolean process_modify_entity_gsql_xls()
    {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配视图名称
                && array_data[3].equalsIgnoreCase("T")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("实体-GSQL和XLS变更(备份GSQL)")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("N")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("Y")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("Y") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_entity_gsql_xls();
    }

    public boolean judge_modify_entity_gsql_xls()
    {
        /* 判断文件名称和类型以及数量 */
        return  new File(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==2
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\EDW-BHIF-T_"+array_data[2]+".gsql");
    }


    //视图-DDL变更
    public boolean process_modify_view_ddl()
    {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配视图名称
                && array_data[3].equalsIgnoreCase("V")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("视图-DDL变更")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_view_ddl();
    }

    public boolean judge_modify_view_ddl()
    {
        /* 判断文件名称和类型以及数量 */
        return  new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==1
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl");
    }

    //视图-XLS变更
    public boolean process_modify_view_xls()
    {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配视图名称
                && array_data[3].equalsIgnoreCase("V")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("视图-XLS变更")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("N")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_view_xls();
    }

    public boolean judge_modify_view_xls()
    {
        /* 判断文件名称和类型以及数量 */
        return  new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==1;
    }

    //视图-DDL/XLS变更
    public boolean process_modify_view_ddl_xls()
    {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配视图名称
                && array_data[3].equalsIgnoreCase("V")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("视图-DDL/XLS变更")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("Y")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("Y")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("N") // array_data[10] 是否涉及重跑
                && judge_modify_view_ddl_xls();
    }

    public boolean judge_modify_view_ddl_xls()
    {
        /* 判断文件名称和类型以及数量 */
        return   new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl").exists()
                && new File(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".xls").exists()
                && CommonFunction.countFilesInDirectory(this.configPath+"\\TableList\\"+array_data[2])==2
                && CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\TableList\\"+array_data[2]+"\\"+array_data[2]+".ddl");
    }

    //实体-数据重跑
    public boolean process_modify_entity_rerunning()  {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配视图名称
                && array_data[3].equalsIgnoreCase("T")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("实体-数据重跑")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("N")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("Y") // array_data[10] 是否涉及重跑
                && judge_modify_entity_rerunning();
    }

    public boolean judge_modify_entity_rerunning()  {
        /* 判断是否在running-job.conf文件清单里*/
        return CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\running-job.conf")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\running-job.conf")
                && CommonFunction.hasRunningConf(this.configPath+"\\running-job.conf",array_data[2].toUpperCase(),array_data[3].toUpperCase());
    }

    //视图-数据重跑
    public boolean process_modify_view_rerunning()  {
        return  array_data[2].substring(0,5).equalsIgnoreCase("bhif_") //匹配视图名称
                && array_data[3].equalsIgnoreCase("V")  // array_data[3] 模型类型
                && array_data[4].equalsIgnoreCase("视图-数据重跑")    // array_data[4] 变更类型
                && array_data[5].equalsIgnoreCase("N")   // array_data[5] 是否更新DDL
                && array_data[6].equalsIgnoreCase("N")  // array_data[6] 是否更新GSQL
                && array_data[7].equalsIgnoreCase("N")  // array_data[7] 是否更新调度
                && array_data[8].equalsIgnoreCase("N") // array_data[8] 是否备份GSQL
                && array_data[9].equalsIgnoreCase("N") // array_data[9] 是否涉及清数配置
                && array_data[10].equalsIgnoreCase("Y") // array_data[10] 是否涉及重跑
                && judge_modify_view_rerunning();
    }

    public boolean judge_modify_view_rerunning() {
        /* 判断是否在running-job.conf文件清单里*/
        return  CommonFunction.isUTF8WithoutBOMCodeFile(this.configPath+"\\running-job.conf")
                && CommonFunction.hasUnixNewlines(this.configPath+"\\running-job.conf")
                && CommonFunction.hasRunningConf(this.configPath+"\\running-job.conf",array_data[2].toUpperCase(),array_data[3].toUpperCase());
    }

    public boolean process_check()  {

        return process_add_entity()
                ||process_modify_entity()
                ||process_modify_entity_ddl()
                ||process_modify_entity_gsql()
                ||process_modify_entity_xls()
                ||process_modify_entity_ddl_xls()
                ||process_modify_entity_ddl_gsql()
                ||process_modify_entity_gsql_xls()
                ||process_modify_view_ddl()
                ||process_modify_view_xls()
                ||process_modify_view_ddl_xls()
                ||process_modify_entity_rerunning()
                ||process_modify_view_rerunning();

    }


}
