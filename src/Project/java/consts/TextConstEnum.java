package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TextConstEnum {
    FIRST_SYMBOL_CODE(97),
    LAST_SYMBOL_CODE(122),
    COUNT_SYMBOLS(20);
    public final Integer label;
}
