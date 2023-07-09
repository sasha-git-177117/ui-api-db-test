package repositories;

import database.utils.DbUtil;
import models.Project;

import java.util.Map;

public class ProjectRepo {
    private static final String GET_BY_NAME = "select * from project where name ='%s'";

    public static Project getProjectByName(String name) {
        String sql = String.format(GET_BY_NAME,name);
        Map<String, Object> result = DbUtil.executeQuery(sql).get(0);


        return new Project(Integer.parseInt(result.get("id").toString()),result.get("name").toString());
    }
}
