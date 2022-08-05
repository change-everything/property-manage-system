package edu.f4.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.f4.pojo.Permissions;
import edu.f4.pojo.RoleGroup;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author PeiYP
 * @since 2022年06月07日 23:27
 */
@Data
public class EmployeeDTO implements UserDetails {

    private Integer empId;
    private Integer empNum;
    private String empPwd;
    //private String roleName;
    private List<Permissions> permCode;
    private String token;
    private String empName;

    private List<RoleGroup> roles = new ArrayList<>();

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return empPwd;
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return empNum.toString();
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
