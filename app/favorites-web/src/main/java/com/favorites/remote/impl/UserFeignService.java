package com.favorites.remote.impl;

import com.favorites.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${favorites.services.user.address}", name = "user-service")
public interface UserFeignService {

    @RequestMapping(value = "/user", method = { RequestMethod.POST })
    User save(@RequestBody User user);

    @RequestMapping(value = "/user/search/findByUserName")
    User findByUserName(@RequestParam("userName") String userName);

    @RequestMapping(value = "/user/search/findByUserNameOrEmail")
    User findByUserNameOrEmail(@RequestParam("userName") String userName, @RequestParam("email") String email);

    @RequestMapping(value = "/user/search/findByEmail")
    User findByEmail(@RequestParam("email") String email);

    @RequestMapping(value = "/user/search/findById")
    User findById(@RequestParam("id") long id);

    @RequestMapping(value = "/user/search/setOutDateAndValidataCode")
    int setOutDateAndValidataCode(@RequestParam("outDate") String outDate, @RequestParam("validataCode") String validataCode, @RequestParam("email") String email);

    @RequestMapping(value = "/user/search/setNewPassword")
    int setNewPassword(@RequestParam("passWord") String passWord, @RequestParam("email") String email);

    @RequestMapping(value = "/user/search/setIntroduction")
    int setIntroduction(@RequestParam("introduction") String introduction, @RequestParam("email") String email);

    @RequestMapping(value = "/user/search/setUserName")
    int setUserName(@RequestParam("userName") String userName, @RequestParam("email") String email);

    @RequestMapping(value = "/user/search/setProfilePicture")
    int setProfilePicture(@RequestParam("profilePicture") String profilePicture, @RequestParam("id") Long id);

    @RequestMapping(value = "/user/search/setBackgroundPicture")
    int setBackgroundPicture(@RequestParam("backgroundPicture") String backgroundPicture, @RequestParam("id") Long id);
}
