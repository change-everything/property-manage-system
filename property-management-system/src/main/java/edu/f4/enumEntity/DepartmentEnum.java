package edu.f4.enumEntity;


/**
 * 部门表对应枚举类
 * @author PeiYP
 * @since 2022/6/11 21:31
 */
public enum DepartmentEnum {

    ADMINISTRATIVE("行政部", 195),

    PERSONNEL("人事部", 196),

    LOGISTICS("后勤部", 197),

    SECURITY("安保部", 198),

    SALES("销售部", 199),

    PENDING("待分配", 900);

    /**
     * 根据code获取message
     */
    /**
     * 根据code获取message
     */
    public static String getMessageByCode(String code) {
        for (DepartmentEnum ele : values()) {
            if (ele.getDepartmentCode().equals(Integer.parseInt(code))) {
                return ele.getDepartmentName();
            }
        }
        return null;
    }


    private String departmentName;
    private Integer departmentCode;

    DepartmentEnum(String departmentName, Integer departmentCode) {
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer departmentCode) {
        this.departmentCode = departmentCode;
    }
}
