package com.tubespbp.petshop.UnitTest;

import com.tubespbp.petshop.API.User.UserDAO;

public interface LoginCallback {

    void onSuccess(boolean value, UserDAO user);
    void onError();
}

