package com.tubespbp.petshop.UnitTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    @Mock
    private LoginView view;
    @Mock
    private LoginService service;
    private LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view, service);
    }

    @Test
    public void invalidEmail() throws Exception {
        when(view.getEmail()).thenReturn("");
        System.out.println("email : "+view.getEmail());

        when(view.getPassword()).thenReturn("muldok");
        System.out.println("password : "+view.getPassword());

        presenter.onLoginClicked();

        verify(view).showEmailError("Email tidak boleh kosong");
    }

    @Test
    public void invalidPassword() throws Exception {
        when(view.getPassword()).thenReturn("muldokque@gmail.com");
        System.out.println("email : "+ view.getEmail());

        when(view.getPassword()).thenReturn("");
        System.out.println("password : "+view.getPassword());

        presenter.onLoginClicked();

        verify(view).showPasswordError("Password Tidak Boleh Kosong");
    }

    @Test
    public void validLogin() throws Exception {
        when(view.getEmail()).thenReturn("muldokque@gmail.com");
        System.out.println("email : "+view.getEmail());

        when(view.getPassword()).thenReturn("muldok");
        System.out.println("password : "+view.getPassword());

        when(service.getValid(view, view.getEmail(), view.getPassword())).thenReturn(true);
        System.out.println("Hasil : "+service.getValid(view,view.getEmail(), view.getPassword()));

        presenter.onLoginClicked();

        //verify(view).startMainActivity();
    }

    @Test
    public void invalidLogin() throws Exception {
        when(view.getEmail()).thenReturn("muldok");
        System.out.println("email : "+view.getEmail());

        when(view.getPassword()).thenReturn("muld");
        System.out.println("password : "+view.getPassword());

        when(service.getValid(view, view.getEmail(), view.getPassword())).thenReturn(false);
        System.out.println("Hasil : "+service.getValid(view,view.getEmail(), view.getPassword()));

        presenter.onLoginClicked();

        //verify(view).showLoginError(R.string.login_failed);
    }

    @Test
    public void invalidLoginNotRegisterer() throws Exception {
        when(view.getEmail()).thenReturn("rastrakidyana@gmail.com");
        System.out.println("email : "+view.getEmail());

        when(view.getPassword()).thenReturn("ras");
        System.out.println("password : "+view.getPassword());

        when(service.getValid(view, view.getEmail(), view.getPassword())).thenReturn(false);
        System.out.println("Hasil : "+service.getValid(view,view.getEmail(), view.getPassword()));

        presenter.onLoginClicked();

        //verify(view).showLoginError(R.string.login_failed);
    }

}