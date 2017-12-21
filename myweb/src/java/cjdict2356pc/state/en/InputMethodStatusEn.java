package cjdict2356pc.state.en;

import cjdict2356pc.state.InputMethodStatus;

/**
 * 英文輸入狀態
 * 
 * @author t
 * @time 2017-1-7下午9:54:59
 */
public abstract class InputMethodStatusEn extends InputMethodStatus {

    public static final String TYPE_CODE = "en";
    public static final String TYPE_NAME = "英";

    InputMethodStatusEn() {
        this.setType(TYPE_CODE);
        this.setTypeName(TYPE_NAME);
    }

    @Override
    public boolean isShouldTranslate() {
        return false;
    }
}
