package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Endpoints {
    TOKEN("/token/get"),
    ADD_TEST("/test/put"),
    ATTACH_LOG("/test/put/log"),
    ATTACH_CONTENT("/test/put/attachment"),
    TEST_INFO_JSON("/test/get/json");

    public final String label;
}
