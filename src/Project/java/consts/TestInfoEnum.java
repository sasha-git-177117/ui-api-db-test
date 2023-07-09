package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestInfoEnum {
    PROJECT_NAME("Project name"),
    TEST_NAME("Test name"),
    TEST_METHOD_NAME("Test method name"),
    ENVIRONMENT("Environment");

    public final String label;
}
