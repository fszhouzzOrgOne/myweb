package cjdict2356pc.state.en;

import java.util.Map;

public class InputMethodStatusEnaa extends InputMethodStatusEn {

    public static final String SUBTYPE_CODE = "aa";
    public static final String SUBTYPE_NAME = "小寫";

    public InputMethodStatusEnaa() {
        this.setSubType(SUBTYPE_CODE);
        this.setSubTypeName(SUBTYPE_NAME);
    }

    @Override
    public Map<String, Object> getKeysNameMap() {
        Map<String, Object> mbTransMap = super.getKeysNameMap();
        for (String key : mbTransMap.keySet()) {
            mbTransMap.put(key, mbTransMap.get(key).toString().toLowerCase());
        }
        return mbTransMap;
    }

    @Override
    public String getInputMethodName() {
        return "英文" + SUBTYPE_NAME;
    }
}
