package edu.f4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月12日 7:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdListDTO {

    private List<Integer> permIds;

}
