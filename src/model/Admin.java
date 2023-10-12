package model;

import lombok.Data;
@Data
public class Admin {
    private int id;
    private String name;
    private String post;
    private String login;
    private String password;

    public Admin(int id, String name, String post, String login, String password) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.login = login;
        this.password = password;
    }
}