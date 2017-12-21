package cjdict2356pc.state.en;

public class InputMethodStatusEnAb extends InputMethodStatusEn {

    public static final String SUBTYPE_CODE = "Aa";
    public static final String SUBTYPE_NAME = "大小寫";
    
    public InputMethodStatusEnAb() {
        this.setSubType(SUBTYPE_CODE);
        this.setSubTypeName(SUBTYPE_NAME);
    }
    
    @Override
    public String getInputMethodName() {
        return "英文大小寫";
    }
}
