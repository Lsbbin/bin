package com.project.bin.controller;

import com.project.bin.dto.UserDto;
import com.project.bin.dto.common.ResponseVo;
import com.project.bin.dto.common.ResponseVoBuilder;
import com.project.bin.dto.enums.ExceptionType;
import com.project.bin.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/user")
    @ApiOperation(value="user")
    public ResponseEntity<Object> getUserList () throws Exception {

        ResponseVo responseVo;
        List<UserDto> responseData = userService.getUserList();

        if (responseData != null) {
            responseVo = ResponseVoBuilder.aResponseVo()
                    .code(ExceptionType.SUCCESS.getErrorCode())
                    .status(ExceptionType.SUCCESS.getHttpStatus())
                    .message(ExceptionType.SUCCESS.getErrorMessage())
                    .body(responseData)
                    .build();
        } else {
            responseVo = ResponseVoBuilder.aResponseVo()
                    .code(ExceptionType.DATA_NOT_FOUND.getErrorCode())
                    .status(ExceptionType.DATA_NOT_FOUND.getHttpStatus())
                    .message(ExceptionType.DATA_NOT_FOUND.getErrorMessage())
                    .build();
        }

        return new ResponseEntity<Object>(responseVo, HttpStatus.OK);
    }

}
