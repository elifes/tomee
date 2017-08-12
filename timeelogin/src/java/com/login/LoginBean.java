package com.login;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class LoginBean implements Serializable {
    @Inject
    private AccountManager accountManager;

    private String user;
    private String password;

    public void setUser(String user) {
        this.user = user;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUser() {
        return this.user;
    }
    public String getPassword() {
        return this.password;
    }

    public String login() {
        if("user".equals(this.user) && "pass".equals(this.password)) {
            // ログイン成功した場合
            this.sessionUpdate();
            this.accountManager.setUser(this.user);
            this.accountManager.setPassword(this.password);
            return "/view/login/secure/top.xhtml?faces-redirect=true";
        } else {
            // ログイン失敗した場合
            return null;
        }
    }
    // セッションハイジャック対策
    // セッションを一度破棄して新規セッションを作成する。
    private void sessionUpdate() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();
        facesContext.getExternalContext().getSession(true);
    }
}
