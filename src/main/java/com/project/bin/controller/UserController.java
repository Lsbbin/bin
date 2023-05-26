package com.project.bin.controller;

import com.project.bin.cmmn.exception.ApiException;
import com.project.bin.cmmn.util.CommonUtil;
import com.project.bin.dto.entity.UserEntity;
import com.project.bin.service.UserService;
import com.project.bin.dto.common.ResponseVo;
import com.project.bin.dto.common.ResponseVoBuilder;
import com.project.bin.dto.enums.ExceptionType;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userPwd", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userName", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userEmail", required = true, dataType = "string", paramType = "query")
    })
    @ApiOperation(value="signUp")
    public ResponseEntity<Object> signUp (
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
