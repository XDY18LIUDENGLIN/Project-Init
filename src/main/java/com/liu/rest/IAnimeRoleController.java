package com.liu.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/10/8 22:20
 * describe: XXXXXXXX
 */
@Api("动漫-角色相关接口")
@RestController("bgm/v0")
public interface IAnimeRoleController {

    @ApiOperation(value = "")
    @RequestMapping(value = "",method = RequestMethod.GET)
    void getPersons();

    @ApiOperation(value = "")
    @RequestMapping(value = "",method = RequestMethod.GET)
    void getPersonBySubject();

    @ApiOperation(value = "")
    @RequestMapping(value = "",method = RequestMethod.GET)
    void getPersonByCharacters();
}
