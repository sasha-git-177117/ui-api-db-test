package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Params {
    VARIANT("variant"),
    PROJECT_ID("projectId"),
    SID("SID"),
    PROJECT_NAME("projectName"),
    TEST_NAME("testName"),
    METHOD_NAME("methodName"),
    ENV("env"),
    TEST_ID("testId"),
    CONTENT("content"),
    CONTENT_TYPE("contentType"),
    TOKEN("token");

    public final String label;
}
