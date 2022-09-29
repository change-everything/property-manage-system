package edu.f4.dto;

import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.NoPayChargeInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月15日 12:10
 */
@Data
public class ChargeInfoVo {

    private Integer chaId;
    private Integer roomNum;
    private Integer ownerNum;
    private String chaDate;
    private Integer empNum;

    private List<NoPayChargeInfo> children = new ArrayList<>();

}
