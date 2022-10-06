package edu.f4.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.MD5;
import com.baidu.aip.util.Base64Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.f4.dto.EmpLoginDTO;
import edu.f4.dto.EmployeeDTO;
import edu.f4.mapper.EmployeeInfoMapper;
import edu.f4.pojo.EmployeeInfo;
import edu.f4.utils.BaiduAiUtil;
import edu.f4.utils.FaceLoginResult;
import edu.f4.utils.IdWorker;
import edu.f4.utils.JwtUtil;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author PeiYP
 * @since 2022年10月03日 22:31
 */
@Service
public class UserService {

    @Value("${face.url}")
    private String url;

    @Autowired
    private EmployeeInfoMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BaiduAiUtil baiduAiUtil;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String FACE_LOGIN_KEY = "faceLogin:";


    public EmpLoginDTO login(Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");

        EmployeeInfo emp = mapper.selectOne(new QueryWrapper<EmployeeInfo>().eq("emp_num", username));

        if (!passwordEncoder.matches(password, emp.getEmpPwd())) {
            throw new BadCredentialsException("密码错误");
        }

        return getEmpLoginDTO(username, emp.getEmpPwd());
    }



    /**
     * 获取扫脸登录url地址
     */
    public String getUrl() {

        String code = idWorker.nextId() + "";
        String content = url + "?code=" + code;
        System.out.println(content);
        String key = FACE_LOGIN_KEY + code;

        // 构造json
        FaceLoginResult result = new FaceLoginResult("-1");
        // 把状态保存到redis
        redisTemplate.boundValueOps(key).set(result, 10, TimeUnit.MINUTES);

        return content;
    }

    /**
     * 验证是否登陆成功
     */
    public FaceLoginResult checkLogin(String code) {

        String key = FACE_LOGIN_KEY + code;

        return  (FaceLoginResult) redisTemplate.opsForValue().get(key);

    }

    public EmpLoginDTO loginByFace(MultipartFile attachment) throws IOException {

        // 1.调用百度云ai查询当前对比的用户
        String userId = baiduAiUtil.faceSearch(Base64Util.encode(attachment.getBytes()));
        // 2.自动登录
        //String key = FACE_LOGIN_KEY + code;
        //FaceLoginResult res = new FaceLoginResult("0");
        EmpLoginDTO empLoginDTO = null;
        if (userId != null) {
            EmployeeInfo emp = mapper.selectOne(new QueryWrapper<EmployeeInfo>().eq("emp_num", userId));

            if (emp != null) {
                empLoginDTO = getEmpLoginDTO(emp.getEmpNum() + "", emp.getEmpPwd());
                //res = new FaceLoginResult("1");
            }
        } else {
            throw new InternalAuthenticationServiceException("用户不存在");
        }
        //redisTemplate.boundValueOps(key).set(res, 10, TimeUnit.MINUTES);
        return empLoginDTO;
    }


    private EmpLoginDTO getEmpLoginDTO(String username, String encodePwd) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, encodePwd);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        User userDetails = (User) authenticate.getPrincipal();
        EmployeeInfo employeeInfo = mapper.selectOne(
                new QueryWrapper<EmployeeInfo>().eq("emp_num", userDetails.getUsername()));


        EmployeeInfo emp = mapper.getEmpAndRoleAndPermsByEmpNum(employeeInfo.getEmpNum());

        EmpLoginDTO empDTO = BeanUtil.copyProperties(emp, EmpLoginDTO.class);

        // 根据用户的id和account生成token并返回
        String jwtToken = JwtUtil.getJwtToken(employeeInfo.getEmpId().toString(), employeeInfo.getEmpNum().toString());
        List<String> permList = new ArrayList<>();
        for (int i = 0; i < emp.getPerms().size(); i++) {
            permList.add(emp.getPerms().get(i).getPermCode());
        }

        empDTO.setToken(jwtToken);
        if (emp.getRole() != null) {
            empDTO.setRole(emp.getRole().getRoleName());
        }

        empDTO.setPermCode(permList);
        return empDTO;
    }
}
