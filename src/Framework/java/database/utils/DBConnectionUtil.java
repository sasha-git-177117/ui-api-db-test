package database.utils;

import aquality.selenium.browser.AqualityServices;
import database.models.ConfigModel;
import java.sql.*;
import java.util.Objects;

public class DBConnectionUtil {
    private static Connection connection = null;
    private static final String PATH_CONFIG = "src/resources/configDb.json";

    private DBConnectionUtil() {
        ConfigModel configModel = JsonUtil.createModelFromJSON(PATH_CONFIG,ConfigModel.class);
        try {
            connection = DriverManager.getConnection(Objects.requireNonNull(configModel).getUrl(), configModel.getUser(), configModel.getPassword());
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection==null) {
            new DBConnectionUtil();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static Statement getStatementExecuteQuery(String sql) {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            statement.executeQuery(sql);
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return statement;
    }

    public static Statement getStatementExecuteUpdate(String sql) {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return statement;
    }


}
