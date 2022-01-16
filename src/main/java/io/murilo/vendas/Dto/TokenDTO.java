package io.murilo.vendas.Dto;

public class TokenDTO {

    public TokenDTO() {
    }

    public TokenDTO(String login, String token) {
        this.login = login;
        this.token = token;
    }

    private String login;
    private String token;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
