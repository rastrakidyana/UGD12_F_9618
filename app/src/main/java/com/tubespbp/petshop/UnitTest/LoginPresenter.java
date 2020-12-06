package com.tubespbp.petshop.UnitTest;

import com.tubespbp.petshop.API.User.UserDAO;

public class LoginPresenter {

    private LoginView view;
    private LoginService service;
    private LoginCallback callback;

    public LoginPresenter(LoginView view, LoginService service) {
        this.view = view;
        this.service = service;
    }

    public void onLoginClicked() {
        if (view.getEmail().isEmpty()) {
            view.showEmailError("Email tidak boleh kosong");
            return;
        }else if (view.getPassword().isEmpty()) {
            view.showPasswordError("Password tidak boleh kosong");
            return;
        }else{
            service.login(view, view.getEmail(), view.getPassword(), new LoginCallback() {
                @Override
                public void onSuccess(boolean value, UserDAO user) {
                        view.startMainActivity();
                }
                @Override
                public void onError() {

                }
            });

            return;
        }
    }
}
