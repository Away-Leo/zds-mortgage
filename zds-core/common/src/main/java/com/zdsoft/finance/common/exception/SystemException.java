package com.zdsoft.finance.common.exception;

import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 系统错误
 * 系统自身无法处理的错误
 * Created by Maple on 2016/11/17.
 * @author Maple
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SystemException extends AppException {
    /**
     * 错误编码
     */
    private String code;
    /**
     * 错误描述信息
     */
    private String msg;

    /**
     * 系统系统构造方法
     * @param code 错误编码
     * @param msg 错误描述信息
     */
    public SystemException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getErrCode() {
        return null;
    }

    @Override
    public Object[] getArguments() {
        return new Object[0];
    }

    public String getMessage() {
        return "exceptionCode=" + code + "\t exceptionMessage="
                + (ObjectHelper.isNotEmpty(msg) ? msg : super.getMessage());
    }
}
