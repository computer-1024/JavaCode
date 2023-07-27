package com.citicbank;

public class Check_Base_Rule {

    private String[] array_data;

    public Check_Base_Rule() {
    }

 /*   public String[] getArray_data() {
        return array_data;
    }
*/
    public void setArray_data(String[] array_data) {
        this.array_data = array_data;
    }

    public  boolean rst_check() {
        return systype() && modeltype() &&changetype();
    }

    public String rst_print() {
        return array_data[2].trim().toUpperCase() + (rst_check()? ":检查基本配置通过":":检查基本配置不通过");

    }

    //检查处理系统
    public  boolean systype() {
        return array_data[1].trim().equalsIgnoreCase("bhif");
    }

    //检查模型类型
    public  boolean modeltype() {
        return array_data[3].trim().equalsIgnoreCase("T")
                || array_data[3].trim().equalsIgnoreCase("V");
    }

    //检查变更类型
    public  boolean changetype() {
        return array_data[4].trim().equalsIgnoreCase("新增实体")
                || array_data[4].trim().equalsIgnoreCase("新增视图")
                || array_data[4].trim().equalsIgnoreCase("逻辑变更")
                || array_data[4].trim().equalsIgnoreCase("实体变更")
                || array_data[4].trim().equalsIgnoreCase("视图变更");
    }




}
