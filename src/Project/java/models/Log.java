package models;

import lombok.Data;

@Data
public class Log {
    private Integer id;

    public Log(String content, Integer testId) {
        this.content = content;
        this.testId = testId;
    }

    private String content;
    private String isException;
    private Integer testId;
}
