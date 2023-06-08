package com.project.bin.controller;

import com.project.bin.cmmn.exception.ApiException;
import com.project.bin.cmmn.util.CommonUtil;
import com.project.bin.dto.UserDto;
import com.project.bin.dto.common.ResponseVo;
import com.project.bin.dto.common.ResponseVoBuilder;
import com.project.bin.dto.entity.UserEntity;
import com.project.bin.dto.enums.ExceptionType;
import com.project.bin.service.UserService;
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

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userPwd", required = true, dataType = "string", paramType = "query")
    })
    @ApiOperation(value="login")
    public ResponseEntity<Object> loginProc (
            @ApiIgnore @RequestParam(required = false) String userId
            , @ApiIgnore @RequestParam(required = false) String userPwd ) throws Exception {

        if (!StringUtils.hasText(userId) || !StringUtils.hasText(userPwd)) {
            throw new ApiException(ExceptionType.BAD_REQUEST);
        }

        ResponseVo responseVo;

        UserDto responseData = userService.loginProc(userId, userPwd);

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

    @PostMapping("/signup")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userPwd", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userName", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userEmail", required = true, dataType = "string", paramType = "query")
    })
    @ApiOperation(value="signUp")
    public ResponseEntity<Object> signUpProc (
            @ApiIgnore @RequestParam(required = false) String userId
            , @ApiIgnore @RequestParam(required = false) String userPwd
            , @ApiIgnore @RequestParam(required = false) String userName
            , @ApiIgnore @RequestParam(required = false) String userEmail
            , HttpServletRequest request ) throws Exception {

        if (!StringUtils.hasText(userId) || !StringUtils.hasText(userPwd) || !StringUtils.hasText(userName) || !StringUtils.hasText(userEmail)) {
            throw new ApiException(ExceptionType.BAD_REQUEST);
        }

        ResponseVo responseVo;
        UserEntity userEntity = new UserEntity().create(userId, userPwd, userName, userEmail, CommonUtil.getClientIP(request));

        try {
            userService.signUp(userEntity);
            responseVo = ResponseVoBuilder.aResponseVo()
                    .code(ExceptionType.SUCCESS.getErrorCode())
                    .status(ExceptionType.SUCCESS.getHttpStatus())
                    .message(ExceptionType.SUCCESS.getErrorMessage())
                    .build();
        }
        catch (Exception e) {
            throw new ApiException(ExceptionType.SERVER_ERROR);
        }

        return new ResponseEntity<Object>(responseVo, HttpStatus.OK);
    }

}
