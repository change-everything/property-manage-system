package edu.f4.dto;

import lombok.Data;

/**
 * @author PeiYP
 * @since 2022年06月10日 16:37
 */
@Data
public class ChargeDTO {

    private Integer chaId;
    private Integer roomNum;
    private String ownerName;
    private String chaType;
    private Integer chaQuantity;
    private Integer empNum;

    private Double chaSum;
    private String chaDate;

}
