package models;

import consts.TestEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestInfoModel implements Comparable<TestInfoModel> {
    private String duration="";
    private String method="";
    private String name="";
    private String startTime="";
    private String endTime="";
    private String status="";


    @Override
    public int compareTo(TestInfoModel o) {
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(TestEnum.DATA_FORMAT.label);
        return LocalDateTime.parse(this.getStartTime(),DATE_FORMATTER)
                .compareTo(LocalDateTime.parse(o.getStartTime(),DATE_FORMATTER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestInfoModel that = (TestInfoModel) o;
        return Objects.equals(duration, that.duration)
                && Objects.equals(method, that.method)
                && Objects.equals(name, that.name)
                && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime)
                && Objects.equals(status.toUpperCase(), that.status.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, method, name, startTime, endTime, status);
    }
}
