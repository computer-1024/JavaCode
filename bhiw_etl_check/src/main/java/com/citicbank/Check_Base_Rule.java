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

/*    public String base_rst_print() {

        return array_data[2].toUpperCase() + (base_check()? ":检查基本配置通过":":检查基本配置不通过");

    }*/

    //检查处理系统
    public  boolean base_systype() {
        return array_data[1].equalsIgnoreCase("bhif");
    }

    //检查模型类型
    public  boolean base_modeltype() {
        return array_data[3].equalsIgnoreCase("T")
                || array_data[3].equalsIgnoreCase("V");
    }

    //检查表名/视图名称是否符合规范
    public  boolean base_entityname() {

        return array_data[2].substring(0,6).equalsIgnoreCase("bhif_t")
                ||array_data[2].substring(0,6).equalsIgnoreCase("bhif_v");

    }

    //检查变更类型
    public  boolean base_changetype() {
        return  array_data[4].equalsIgnoreCase("新增实体")
                || array_data[4].equalsIgnoreCase("变更实体")
  //              || array_data[4].equalsIgnoreCase("删除实体")
                || array_data[4].equalsIgnoreCase("新增视图")
                || array_data[4].equalsIgnoreCase("变更视图")
//                || array_data[4].equalsIgnoreCase("删除视图")
                || array_data[4].equalsIgnoreCase("变更逻辑")
                || array_data[4].equalsIgnoreCase("变更依赖");

    }


}
