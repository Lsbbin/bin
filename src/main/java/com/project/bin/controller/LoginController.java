package com.project.bin.controller;

import com.project.bin.cmmn.exception.ApiException;
import com.project.bin.dto.UserDto;
import com.project.bin.dto.common.ResponseVo;
import com.project.bin.dto.common.ResponseVoBuilder;
import com.project.bin.dto.enums.ExceptionType;
import com.project.bin.service.LoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userPwd", required = true, dataType = "string", paramType = "query")
    })
    @ApiOperation(value="login")
    public ResponseEntity<Object> login (
            @ApiIgnore @RequestParam(required = false) String userId
            , @ApiIgnore @RequestParam(required = false) String userPwd ) throws Exception {

        if (!StringUtils.hasText(userId) || !StringUtils.hasText(userPwd)) {
            throw new ApiException(ExceptionType.BAD_REQUEST);
        }

        ResponseVo responseVo;

        UserDto responseData = loginService.verify(userId, userPwd);

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
