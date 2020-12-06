package com.tubespbp.petshop.UnitTest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.tubespbp.petshop.API.ApiClient;
import com.tubespbp.petshop.API.ApiInterface;
import com.tubespbp.petshop.API.User.UserDAO;
import com.tubespbp.petshop.API.User.UserResponse;
import com.tubespbp.petshop.Constant;
import com.tubespbp.petshop.MainActivity;
import com.tubespbp.petshop.R;
import com.tubespbp.petshop.SignUpActivity;
import com.tubespbp.petshop.ui.profile.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity implements LoginView {
    MaterialTextView clickHere;
    MaterialButton login;
    TextInputLayout emailLayout, passLayout;
    TextInputEditText email, pass;
    List<User> userList;
    int idUser, currentIdUser = -1;
    private LoginPresenter presenter;


    public static final int mode = Activity.MODE_PRIVATE;
    private SharedPreferences shared;

    Constant constant;
    SharedPreferences.Editor editor;
    SharedPreferences app_preferences;
    int appTheme;
    int themeColor;
    int appColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        appColor = app_preferences.getInt("color", 0);
        appTheme = app_preferences.getInt("theme", 0);
        themeColor = appColor;
        constant.color = appColor;
        presenter = new LoginPresenter(this, new LoginService());


        if (themeColor == 0) {
            setTheme(Constant.theme);
        } else if (appTheme == 0) {
            setTheme(Constant.theme);
        } else {
            setTheme(appTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        shared = getSharedPreferences("getId", mode);
        idUser = shared.getInt("idUser", -1);

        Log.d("ID USER BEFORE LOGIN:", String.valueOf(idUser));
        //ID
        clickHere = findViewById(R.id.clickHere);
        login = findViewById(R.id.btn_login);
        email = findViewById(R.id.ti_login_email);
        pass = findViewById(R.id.ti_login_pass);

        emailLayout = findViewById(R.id.til_login_email);
        passLayout = findViewById(R.id.til_login_pass);

        //Click here to sign up text link
        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(moveToSignUp);
            }
        });



        //Button Login clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLoginClicked();
            }
        });
    }


    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @Override
    public void showEmailError(String message) {
        email.setError(message);
    }

    @Override
    public String getPassword() {
        return pass.getText().toString();
    }

    @Override
    public void showPasswordError(String message) {
        pass.setError(message);
    }

    @Override
    public void startMainActivity() {
        new ActivityUtil(this).startMainActivity();
    }

    @Override
    public void showLoginError(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }

    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }
}