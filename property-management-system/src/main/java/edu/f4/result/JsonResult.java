package edu.f4.result;

import edu.f4.enumEntity.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author PeiYP
 * @since 2022年06月09日 15:57
 */
@Data
public class JsonResult<T> implements Serializable {
    private Boolean success;
    private Integer statusCode;
    private String statusMsg;
    private T data;

    public JsonResult() {
    }

    // 成功或者失败都能走这个
    public JsonResult(boolean success) {
        this.success = success;
        this.statusMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.statusCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
    }

    // 成功或者失败都能走这个，并且可以传一个枚举来改变默认枚举的值
    public JsonResult(boolean success, ResultCode resultEnum) {
        this.success = success;
        // 传来的枚举为null就用默认的，不为null就用传来的枚举
        this.statusCode = success ? (resultEnum==null?ResultCode.SUCCESS.getCode():resultEnum.getCode()) : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.statusMsg = success ? (resultEnum==null?ResultCode.SUCCESS.getMessage():resultEnum.getMessage()): (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
    }

    // 成功或者失败都能用
    // 用户可以传一个任意对象过来，用默认的成功或者失败的枚举
    public JsonResult(boolean success, T data) {
        this.success = success;
        this.statusCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.statusMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.data = data;
    }

    // 成功或者失败都能用
    // 用户可以传一个任意对象和自定义枚举过来
    public JsonResult(boolean success, ResultCode resultEnum, T data) {
        this.success = success;
        this.statusCode = success ? (resultEnum==null ? ResultCode.SUCCESS.getCode() : resultEnum.getCode()): (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.statusMsg = success ? (resultEnum==null ? ResultCode.SUCCESS.getMessage() : resultEnum.getMessage()) : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
        this.data = data;
    }
}
