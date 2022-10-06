package edu.f4.controller;

import com.baidu.aip.util.Base64Util;
import edu.f4.dto.EmpLoginDTO;
import edu.f4.enumEntity.ResultCode;
import edu.f4.result.JsonResult;
import edu.f4.result.Result;
import edu.f4.result.ResultTool;
import edu.f4.service.IEmployeeInfoService;
import edu.f4.service.UserService;
import edu.f4.utils.BaiduAiUtil;
import edu.f4.utils.FaceLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author PeiYP
 * @since 2022年10月03日 22:31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BaiduAiUtil baiduAiUtil;


    @GetMapping("/faceLogin/getUrl")
    public JsonResult getUrl() {
        String url = userService.getUrl();
        return ResultTool.success(ResultCode.SUCCESS, url);
    }

    @GetMapping("/faceLogin/checkLogin/{code}")
    public JsonResult checkLogin(@PathVariable String code) {
        FaceLoginResult res = userService.checkLogin(code);
        return ResultTool.success(ResultCode.SUCCESS, res);
    }

    /**
     * 图像检测，判断图片中是否存在面部头像
     */
    @PostMapping("/faceLogin/checkFace")
    public JsonResult checkFace(@RequestParam(name = "file") MultipartFile attachment) throws Exception {
        String image = Base64Util.encode(attachment.getBytes());
        Boolean res = baiduAiUtil.faceCheck(image);
        return res ? ResultTool.success(ResultCode.SUCCESS) : ResultTool.fail(ResultCode.COMMON_FAIL);
    }


    @PostMapping("/faceLogin")
    public JsonResult loginByFace(@RequestParam(name = "file") MultipartFile attachment) {
        EmpLoginDTO empDTO = null;
        try {
            empDTO = userService.loginByFace(attachment);
            return ResultTool.success(ResultCode.SUCCESS_login, empDTO);
        } catch (AccountExpiredException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        } catch (BadCredentialsException e) {
            return ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        } catch (CredentialsExpiredException e) {
            return ResultTool.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
        } catch (DisabledException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
        } catch (LockedException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_LOCKED);
        } catch (InternalAuthenticationServiceException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } catch (Exception e) {
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
    }


    @PostMapping("/login")
    public JsonResult login(@RequestBody Map<String, String> map) {
        EmpLoginDTO empDTO = null;
        try {
            empDTO = userService.login(map);
            return ResultTool.success(ResultCode.SUCCESS_login, empDTO);
        } catch (AccountExpiredException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        } catch (BadCredentialsException e) {
            return ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        } catch (CredentialsExpiredException e) {
            return ResultTool.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
        } catch (DisabledException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
        } catch (LockedException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_LOCKED);
        } catch (InternalAuthenticationServiceException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } catch (Exception e) {
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }

    }

}
