package com.jaweher.offreemploi;

import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;

public class user {
    private  String login;
    private  String password;
   private String num;
private  String email;

    public String getNum() {
        return num;
    }

    public String getEmail() {
        return email;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public user(String login, String password, String num, String email) {
        this.login = login;
        this.password = password;
        this.num = num;
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
