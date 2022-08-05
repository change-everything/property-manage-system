package edu.f4.enumEntity;

public enum GenderEnum {

    MALE("男", 1),
    FEMALE("女", 2);

    private String genderName;
    private Integer genderCode;

    GenderEnum(String genderName, Integer genderCode) {
        this.genderName = genderName;
        this.genderCode = genderCode;
    }

    /**
     * 根据code获取message
     */
    public static String getMessageByCode(String code) {
        for (GenderEnum ele : values()) {
            if (ele.getGenderCode().equals(Integer.parseInt(code))) {
                return ele.getGenderName();
            }
        }
        return null;
    }

    /**
     * 根据message获取code
     */
    public static Integer getMessageByName(String name) {
        for (GenderEnum ele : values()) {
            if (ele.getGenderName().equals(name)) {
                return ele.getGenderCode();
            }
        }
        return null;
    }
    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public Integer getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(Integer genderCode) {
        this.genderCode = genderCode;
    }
}
