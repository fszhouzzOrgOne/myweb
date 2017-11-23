package emoji;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cangjie.java.util.IOUtils;

/**
 * 統一碼的表情符號<br />
 * 參見：https://apps.timwhitlock.info/emoji/tables/unicode#<br />
 * 5. Uncategorized沒有取出來
 * 
 * @author fszhouzz@qq.com
 * @time 2017年11月23日下午5:42:13
 */
public class EmojiUnicodeTest {
    private static String baseDir = "src\\java\\emoji\\";
    private static String fileName = baseDir + "emoji.txt";
    
    public static void main(String[] args) throws Exception {
        Set<String> emoticons = getEmojiStringSet(0x1F601, 0x1F64F);
        Set<String> additionalEmoticons = getEmojiStringSet(0x1F600, 0x1F636);
        for (String emo : additionalEmoticons) {
            if (!emoticons.contains(emo)) {
                emoticons.add(emo);
            }
        }

        Set<String> dingbats = getEmojiStringSet(0x2702, 0x27B0);

        Set<String> transportAndMapSymbols = getEmojiStringSet(0x1F680, 0x1F6C0);
        Set<String> additionalTransportAndMapSymbols = getEmojiStringSet(0x1F681, 0x1F6C5);
        for (String emo : additionalTransportAndMapSymbols) {
            if (!transportAndMapSymbols.contains(emo)) {
                transportAndMapSymbols.add(emo);
            }
        }

        Set<String> enclosedCharacters = getEmojiStringSet(0x24C2, 0x1F251);

        Set<String> otherAdditionalSymbols = getEmojiStringSet(0x1F30D, 0x1F567);

        List<String> allEmoji = new ArrayList<String>();
        allEmoji.add(getEmojiStringFromSet(emoticons));
        allEmoji.add(getEmojiStringFromSet(dingbats));
        allEmoji.add(getEmojiStringFromSet(transportAndMapSymbols));
        allEmoji.add(getEmojiStringFromSet(enclosedCharacters));
        allEmoji.add(getEmojiStringFromSet(otherAdditionalSymbols));
        
        IOUtils.writeFile(fileName, allEmoji);
    }
    
    public static String getEmojiStringFromSet(Set<String> set) {
        String res = "";
        for (String one : set) {
            res += one;
        }
        return res;
    }

    public static Set<String> getEmojiStringSet(int start, int end) {
        Set<String> set = new LinkedHashSet<String>();
        for (int i = start; i <= end; i++) {
            String res = getEmojiStringByUnicode(i);
            if (!set.contains(res)) {
                set.add(res);
            }
        }
        return set;
    }

    public static String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}
