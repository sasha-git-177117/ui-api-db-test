package models;

import lombok.Data;

@Data
public class Attachment {
    private Integer id;
    private byte[] content;
    private String contentType;
    private Integer testId;

    public Attachment(byte[] content, String contentType, Integer testId) {
        this.content = content;
        this.contentType = contentType;
        this.testId = testId;
    }
}
