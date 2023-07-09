package repositories;

import database.utils.DbUtil;
import models.TestDb;

public class TestRepo {
    private static final String ADD_TEST = "INSERT INTO test (name, method_name, project_id, session_id, env) " +
            "VALUES ('%s', '%s', %d, %d, '%s')";

    public static Integer addTest(TestDb testDb) {
        String sql = String.format(ADD_TEST,
                testDb.getName(),
                testDb.getMethodName(),
                testDb.getProjectId(),
                testDb.getSessionId(),
                testDb.getEnv());
        return DbUtil.executeInsert(sql);
    }
}
