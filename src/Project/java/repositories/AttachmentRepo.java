package repositories;

import database.utils.DbUtil;
import models.Attachment;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class AttachmentRepo {
    private static final String ADD_ATTACHMENT = "INSERT INTO attachment (content, content_type, test_id) VALUES (?,?,?)";

    public static Integer addAttachment(Attachment attachment) {
        PreparedStatement ps = DbUtil.getPreparedStatement(ADD_ATTACHMENT);
        try {
            ps.setBlob(1,new SerialBlob(attachment.getContent()));
            ps.setString(2,attachment.getContentType());
            ps.setInt(3,attachment.getTestId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return DbUtil.getGeneratedId(ps);
    }
}
