package models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Session {
    private Integer id;
    private String sessionKey;
    private LocalDateTime createdTime;
    private Integer buildNumber = 1;

    public Session(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
