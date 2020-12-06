package com.tubespbp.petshop.UnitTest;

import com.tubespbp.petshop.API.User.UserDAO;

public interface LoginView {

    String getEmail();
    void showEmailError(String message);
    String getPassword();
    void showPasswordError(String message);
    void startMainActivity();
    void showLoginError(String message);
    void showErrorResponse(String message);
}
