package com.tubespbp.petshop.UnitTest;

import android.content.Context;
import android.content.Intent;

import com.tubespbp.petshop.API.User.UserDAO;
import com.tubespbp.petshop.MainActivity;

public class ActivityUtil {

    private Context context;

    public ActivityUtil(Context context) {
        this.context = context;
    }

    public void startMainActivity() {
        context.startActivity(new Intent(context, MainActivity.class));
    }

}
