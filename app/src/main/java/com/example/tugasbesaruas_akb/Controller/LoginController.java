package com.example.tugasbesaruas_akb.Controller;

import com.example.tugasbesaruas_akb.Model.User;
import com.example.tugasbesaruas_akb.View.ILoginView;

public class LoginController implements ILoginController {

    ILoginView loginView;

    public LoginController(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void OnLogin(String username, String password) {

        User user = new User(username, password);
        int logincode = user.isValid();

        if(logincode == 0)
        {
            loginView.OnloginError("Masukkan Email dan Password Anda");
        }
        else
        {
            loginView.OnLoginSuccess("Login Berhasil");
        }
    }
}
