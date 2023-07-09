package models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestDb {
    private Integer id;
    private String name;
    private Integer statusId;
    private String methodName;
    private Integer projectId;
    private Integer sessionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String env;
    private String browser;
    private Integer authorId;

    public TestDb(String name, String methodName, Integer projectId, Integer sessionId, String env) {
        this.name = name;
        this.methodName = methodName;
        this.projectId = projectId;
        this.sessionId = sessionId;
        this.env = env;
    }
}
