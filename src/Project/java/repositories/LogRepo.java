package repositories;

import database.utils.DbUtil;
import models.Log;

public final class LogRepo {
    private static final String ADD_LOG = "INSERT INTO log (content, test_id) VALUES ('%s', %d)";

    public static Integer addLog(Log log) {
        String sql = String.format(ADD_LOG,
                log.getContent(),
                log.getTestId());
        return DbUtil.executeInsert(sql);
    }
}
