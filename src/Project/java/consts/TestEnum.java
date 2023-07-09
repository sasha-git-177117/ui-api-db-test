package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestEnum {
    TEST_DATA_PATH("dataTest.json"),
    VARIANT("/variant"),
    DATA_FORMAT("yyyy-MM-dd HH:mm:ss.S"),
    PROJECT_ID("/projectId");

    public final String label;
}
