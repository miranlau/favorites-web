package com.favorites.remote;

import com.favorites.domain.User;

public interface UserService {

    void save(User user);

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);

    User findByEmail(String email);

    User findById(long  id);

    int setOutDateAndValidataCode(String outDate, String validataCode, String email);

    int setNewPassword(String passWord, String email);

    int setIntroduction(String introduction, String email);

    int setUserName(String userName, String email);

    int setProfilePicture(String profilePicture, Long id);

    int setBackgroundPicture(String backgroundPicture, Long id);
}
