package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ConfigEnum {
    CONFIG_DATA_PATH("configData.json"),
    BASE_URL_WEB("/baseURLWeb"),
    BASE_URL_API("/baseURLApi"),
    AUTH_URL("/authURL"),
    LOGIN_WEB("/loginWeb"),
    PASSWORD_WEB("/passwordWeb"),
    LOGIN_DB("/loginDb"),
    PASSWORD_DB("/passwordDb"),
    URL_DB("urlDb");

    public final String label;
}
