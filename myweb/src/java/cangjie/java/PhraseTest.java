package cangjie.java;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cangjie.java.util.IOUtils;

public class PhraseTest {

    public static String mbsBaseDir = Cj00AllInOneTest.mbsBaseDir;
    // 詞組
    public static String phraseOriginFile = Cj00AllInOneTest.phraseOriginFile;

    public static void main(String[] args) throws Exception {
        Set<String> set = new LinkedHashSet<String>();
        set.add("亮    ".trim());
        
        Cj00AllInOneTest.readPhraseFile();
        
        List<String> allphrases = IOUtils.readLines(phraseOriginFile);
        List<String> goods = new ArrayList<String>();
        for (String str : allphrases) {
            if (!set.contains(str)) {
                goods.add(str);
            }
        }
        IOUtils.writeFile(phraseOriginFile, goods);
    }

}
