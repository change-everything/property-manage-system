package edu.f4.enumEntity;

import com.alibaba.druid.sql.visitor.functions.Char;

public enum ChargeTypeEnum {

    WATER_BILL("水费", 1),
    ELECTRICITY_BILL("电费", 2),
    HYGIENE_BILL("卫生费", 3),
    PROPERTY_BILL("物业费", 4);

    private String ChargeName;
    private Integer ChargeCode;

    ChargeTypeEnum(String chargeName, Integer chargeCode) {
        ChargeName = chargeName;
        ChargeCode = chargeCode;
    }

    /**
     * 根据code获取message
     */
    public static String getMessageByCode(String code) {
        for (ChargeTypeEnum ele : values()) {
            if (ele.getChargeCode().equals(Integer.parseInt(code))) {
                return ele.getChargeName();
            }
        }
        return null;
    }

    public String getChargeName() {
        return ChargeName;
    }

    public void setChargeName(String chargeName) {
        ChargeName = chargeName;
    }

    public Integer getChargeCode() {
        return ChargeCode;
    }

    public void setChargeCode(Integer chargeCode) {
        ChargeCode = chargeCode;
    }
}
