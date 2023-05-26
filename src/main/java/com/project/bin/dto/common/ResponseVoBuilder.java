package com.project.bin.dto.common;

import org.springframework.util.StringUtils;

public final class ResponseVoBuilder {
    private int code;
    private int status;
    private String message;
    private Object body;

    private ResponseVoBuilder() {
    }

    public static ResponseVoBuilder aResponseVo() {
        return new ResponseVoBuilder();
    }

    public ResponseVoBuilder code(int code) {
        this.code = code;
        return this;
    }

    public ResponseVoBuilder status(int status) {
        this.status = status;
        return this;
    }

    public ResponseVoBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ResponseVoBuilder body(Object body) {
        if (!StringUtils.isEmpty(body)) {
            this.body = body;
        }
        return this;
    }

    public ResponseVo build() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(code);
        responseVo.setStatus(status);
        responseVo.setMessage(message);
        if (!StringUtils.isEmpty(body)) {
            responseVo.setBody(body);
        }

        return responseVo;
    }
}
