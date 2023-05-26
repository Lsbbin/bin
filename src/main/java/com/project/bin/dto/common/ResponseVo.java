package com.project.bin.dto.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseVo implements Serializable {

    private int code;

    private int status;

    private String message;

    private Object body;

}
