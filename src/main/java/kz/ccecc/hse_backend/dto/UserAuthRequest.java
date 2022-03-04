package kz.ccecc.hse_backend.dto;

public class UserAuthRequest {
    private String login;
    private String password;

    public UserAuthRequest(String login, String password, String error) {
        this.login = login;
        this.password = password;
    }

    public UserAuthRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
