package database.utils;

import aquality.selenium.browser.AqualityServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {

    private DbUtil() { }

    public static Integer executeInsert(String sql) {
        Integer id = null;
        try {
            ResultSet generatedKeys = DBConnectionUtil.getStatementExecuteUpdate(sql).getGeneratedKeys();
            if (generatedKeys.next()) {
                id = Integer.parseInt(String.valueOf(generatedKeys.getLong(1)));
                generatedKeys.close();
            }

        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static List<Map<String, Object>> executeQuery(String sql) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            ResultSet rs = DBConnectionUtil.getStatementExecuteQuery(sql).getResultSet();
            while (rs.next()) {
                Map<String, Object> resMap = new HashMap<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    resMap.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                }
                result.add(resMap);
            }
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return result;
    }
    public static PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = DBConnectionUtil.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            AqualityServices.getLogger().error(throwables.getMessage());
        }
        return ps;
    }

    public static Integer getGeneratedId(PreparedStatement ps) {
        int id = 0;
        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = Integer.parseInt(String.valueOf(generatedKeys.getLong(1))) ;
            }
        } catch (SQLException throwables) {
            AqualityServices.getLogger().error(throwables.getMessage());
        }
        return id;
    }

}
