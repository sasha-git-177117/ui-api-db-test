package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestTableEnum {
    TEST_NAME(0),
    TEST_METHOD(1),
    LATEST_TEST_RESULT(2),
    LATEST_TEST_START_TIME(3),
    LATEST_TEST_END_TIME(4),
    LATEST_TEST_DURATION(5);

    public final Integer label;
}
