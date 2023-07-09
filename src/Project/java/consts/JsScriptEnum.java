package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum JsScriptEnum {
    JS_SCRIPT_PATH("jsScript.json"),
    JS_SCRIPT_CLOSE_POP_UP("/jsScriptClosePopUp");
    public final String label;
}
