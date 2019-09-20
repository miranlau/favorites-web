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
    void save(@RequestBody User user);

    @RequestMapping(value = "/search/findByUserName")
    User findByUserName(@RequestParam("userName") String userName);

    @RequestMapping(value = "/search/findByUserNameOrEmail")
    User findByUserNameOrEmail(@RequestParam("userName") String userName, @RequestParam("email") String email);

    @RequestMapping(value = "/search/findByEmail")
    User findByEmail(@RequestParam("email") String email);

    @RequestMapping(value = "/search/findById")
    User findById(@RequestParam("id") long id);

    @RequestMapping(value = "/search/setOutDateAndValidataCode")
    int setOutDateAndValidataCode(@RequestParam("outDate") String outDate, @RequestParam("validataCode") String validataCode, @RequestParam("email") String email);

    @RequestMapping(value = "/search/setNewPassword")
    int setNewPassword(@RequestParam("passWord") String passWord, @RequestParam("email") String email);

    @RequestMapping(value = "/search/setIntroduction")
    int setIntroduction(@RequestParam("introduction") String introduction, @RequestParam("email") String email);

    @RequestMapping(value = "/search/setUserName")
    int setUserName(@RequestParam("userName") String userName, @RequestParam("email") String email);

    @RequestMapping(value = "/search/setProfilePicture")
    int setProfilePicture(@RequestParam("profilePicture") String profilePicture, @RequestParam("id") Long id);

    @RequestMapping(value = "/search/setBackgroundPicture")
    int setBackgroundPicture(@RequestParam("backgroundPicture") String backgroundPicture, @RequestParam("id") Long id);
}
