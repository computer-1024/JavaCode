package com.citicbank;

public class Check_Base_Rule {

    private String[] array_data;

    public Check_Base_Rule() {

    }

    public void setArray_data(String[] array_data) {
        this.array_data = array_data;
    }

    public  boolean base_check() {
        return base_systype() && base_modeltype() &&base_entityname()&&base_changetype();
    }

    //检查处理系统
    public  boolean base_systype() {
        return array_data[1].equalsIgnoreCase("bhif");
    }

    //检查模型类型
    public  boolean base_modeltype() {
        return array_data[11].equalsIgnoreCase("T")
                || array_data[11].equalsIgnoreCase("V");
    }

    //检查表名/视图名称是否符合规范
    public  boolean base_entityname() {

        return array_data[2].substring(0,5).equalsIgnoreCase("bhif_");

    }

    //检查变更类型
    public  boolean base_changetype() {
        return  array_data[24].equalsIgnoreCase("实体-DDL变更")  //1
                || array_data[24].equalsIgnoreCase("实体-GSQL变更(备份GSQL)") //2
                || array_data[24].equalsIgnoreCase("实体-XLS变更") //3
                || array_data[24].equalsIgnoreCase("实体-DDL/XLS变更") //4
                || array_data[24].equalsIgnoreCase("实体-DDL/GSQL变更(备份GSQL)") //5
                || array_data[24].equalsIgnoreCase("实体-GSQL和XLS变更(备份GSQL)") //6
                || array_data[24].equalsIgnoreCase("实体-DDL/XLS/GSQL新增(新增存储策略)")  //7
                || array_data[24].equalsIgnoreCase("实体-DDL/XLS/GSQL变更(备份GSQL)") //8
                || array_data[24].equalsIgnoreCase("视图-DDL变更") //9
                || array_data[24].equalsIgnoreCase("视图-XLS变更") //10
                || array_data[24].equalsIgnoreCase("视图-DDL/XLS变更") //11
                || array_data[24].equalsIgnoreCase("实体-数据重跑") //12
                || array_data[24].equalsIgnoreCase("视图-数据重跑") //13
                || array_data[24].equalsIgnoreCase("实体-卸数字符集变更") //14
                || array_data[24].equalsIgnoreCase("视图-卸数字符集变更"); //15
    }


}
