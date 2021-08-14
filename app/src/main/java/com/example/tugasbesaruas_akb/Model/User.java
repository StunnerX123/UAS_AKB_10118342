package com.example.tugasbesaruas_akb.Model;

import android.text.TextUtils;

import java.util.regex.Pattern;

public class User implements IUser{
    private String username, password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int isValid() {
        if(TextUtils.isEmpty(getUsername()))
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
