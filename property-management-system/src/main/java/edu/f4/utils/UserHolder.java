package edu.f4.utils;

import edu.f4.dto.EmployeeDTO;

/**
 * @author PeiYP
 * @since 2022年06月08日 8:34
 */
public class UserHolder {

    private static final ThreadLocal<EmployeeDTO> tl = new ThreadLocal<>();

    public static void saveEmp(EmployeeDTO employee){
        tl.set(employee);
    }

    public static EmployeeDTO getEmp(){
        return tl.get();
    }

    public static void removeEmp(){
        tl.remove();
    }

}
