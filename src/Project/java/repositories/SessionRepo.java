package repositories;

import database.utils.DbUtil;
import models.Session;

public final class SessionRepo {
    private static final String ADD_SESSION = "INSERT INTO session (session_key, build_number) VALUES ('%s', %d)";

    public static Integer addSession(Session session) {
        String sql = String.format(ADD_SESSION,
                session.getSessionKey(),
                session.getBuildNumber());
        return DbUtil.executeInsert(sql);
    }
}
