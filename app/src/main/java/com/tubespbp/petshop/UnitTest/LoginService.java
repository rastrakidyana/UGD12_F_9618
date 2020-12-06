package com.tubespbp.petshop.UnitTest;

import com.tubespbp.petshop.API.ApiClient;
import com.tubespbp.petshop.API.ApiInterface;
import com.tubespbp.petshop.API.User.UserDAO;
import com.tubespbp.petshop.API.User.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

    public void login(final LoginView view, String email, String password, final
    LoginCallback callback){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> userDAOCall = apiService.login(email, password);
        userDAOCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.body().getMessage().equalsIgnoreCase("Authenticated" )){
                    callback.onSuccess(true, response.body().getUsers().get(0));
                } else{
                    callback.onError();
                    view.showLoginError(response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.showErrorResponse(t.getMessage());
                callback.onError();
            }
        });
    }

    public Boolean getValid(final LoginView view, String email, String password){
        final Boolean[] bool = new Boolean[1];
        login(view, email, password, new LoginCallback() {
            @Override
            public void onSuccess(boolean value, UserDAO user) {
                bool[0] = true;
            }
            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }
}
