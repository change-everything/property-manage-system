package edu.f4.enumEntity;

public enum JobEnum {

    FORMAL_EMPLOYEE("正式工", 1),
    TRAINEE("实习生", 2),
    MANAGER("经理", 3),
    GROUP_LEADER("组长", 4);

    private String jobName;
    private Integer jobCode;

    JobEnum(String jobName, Integer jobCode) {
        this.jobName = jobName;
        this.jobCode = jobCode;
    }

    /**
     * 根据code获取message
     */
    public static String getMessageByCode(String code) {
        for (JobEnum ele : values()) {
            if (ele.getJobCode().equals(Integer.parseInt(code))) {
                return ele.getJobName();
            }
        }
        return null;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getJobCode() {
        return jobCode;
    }

    public void setJobCode(Integer jobCode) {
        this.jobCode = jobCode;
    }
}
