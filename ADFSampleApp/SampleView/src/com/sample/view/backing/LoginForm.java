package com.sample.view.backing;

import oracle.adf.view.rich.context.AdfFacesContext;


public class LoginForm {
    private String username;
    private String password;

    private String myValue;


    public void setMyValue(String myValue) {
        this.myValue = myValue;
    }

    public String getMyValue() {
        return myValue;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String loginCommand() {
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.getPageFlowScope().put("username", username);
        return "login";
    }
}
