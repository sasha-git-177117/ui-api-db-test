package database.models;

import lombok.Data;

@Data
public class ConfigModel {
    private String user;
    private String password;
    private String url;
}
