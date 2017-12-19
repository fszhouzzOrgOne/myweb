package cangjie.java;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cangjie.java.util.IOUtils;
import cangjie.java.util.PhraseUtils;

public class Cj00AllInOneTest {

    public static String mbsBaseDir = "src\\java\\cangjie\\mb\\";
    // 取自倉頡平臺2012所帶碼表
    public static String mb26000 = mbsBaseDir + "cj2-6763.txt";
    // 取自倉頡平臺2012所帶碼表
    public static String mb36000 = mbsBaseDir + "cj3-6763.txt";
    public static String mb310000 = mbsBaseDir + "cj3-13053.txt";
    public static String mb320000 = mbsBaseDir + "cj3-20902.txt";
    public static String mb370000 = mbsBaseDir + "cj3-70000.txt";
    public static String mb3more = mbsBaseDir + "cj3-more.txt";
    // 取自倉頡平臺2012所帶碼表
    public static String mb58000 = mbsBaseDir + "cj5-8300.txt";
    public static String mb510000 = mbsBaseDir + "cj5-13053.txt";
    public static String mb520000 = mbsBaseDir + "cj5-20902.txt";
    public static String mb570000 = mbsBaseDir + "cj5-70000.txt";
    public static String mb5more = mbsBaseDir + "cj5-more.txt";
    // 取用倉頡輸入法羣共享中的cj6-70000碼表等，並整合了雪齋團隊整理的碼表
    public static String mb68000 = mbsBaseDir + "cj6-8300.txt";
    public static String mb610000 = mbsBaseDir + "cj6-13053.txt";
    public static String mb620000 = mbsBaseDir + "cj6-20902.txt";
    public static String mb670000 = mbsBaseDir + "cj6-70000.txt";
    public static String mb6more = mbsBaseDir + "cj6-more.txt";
    // 自製碼表
    public static String mb6compat1000 = mbsBaseDir + "cj6-compat1000.txt";
    public static String mb6simParts400 = mbsBaseDir + "cj6-simPart400.txt";
    public static String mb6japkore200 = mbsBaseDir + "cj6-japkorea.txt";
    public static String mb6unif7473 = mbsBaseDir + "cj6-unicodef7473.txt";
    public static String mb6morePhrase = mbsBaseDir + "cj6-morePhrase.txt";
    // 各代所有碼表合一
    public static String mb2allInOne = mbsBaseDir + "allInOne-cj2.txt";
    public static String mb3allInOne = mbsBaseDir + "allInOne-cj3.txt";
    public static String mb5allInOne = mbsBaseDir + "allInOne-cj5.txt";
    public static String mb6allInOne = mbsBaseDir + "allInOne-cj6.txt";
    // 詞組
    public static String phraseOriginFile = mbsBaseDir + "phrases.txt";
    public static String mb3phrase = mbsBaseDir + "cj3-phrase.txt";
    public static String mb5phrase = mbsBaseDir + "cj5-phrase.txt";
    public static String mb6phrase = mbsBaseDir + "cj6-phrase.txt";

    // 四角號碼，參照911查詢網底四角號碼查詢而製作的碼表
    public static String mbsghm27000 = mbsBaseDir + "sghm-27000.txt";
    public static String mbsghm27000no5 = mbsBaseDir + "sghm-27000-5.txt";
    public static String sghmphrase = mbsBaseDir + "sghm-phrase.txt";
    public static String sghmallInOne = mbsBaseDir + "allInOne-sghm.txt";

    // 日文，自製碼表，並取維基上《日語常用漢字一覧表》中的字和詞，編碼後加入碼表中
    public static String mbnippon200 = mbsBaseDir + "karina-kana200.txt";
    public static String mbnipponKanji2000 = mbsBaseDir + "karina-kanji2000.txt";
    public static String mbnipponMoreSymbol = mbsBaseDir + "karina-moreSymbol.txt";
    public static String nihonAllInOne = mbsBaseDir + "allInOne-karina.txt";

    // 韓文，自製碼表，主要由榕樹葉編碼
    public static String mbkorea10000 = mbsBaseDir + "korea-12000.txt";
    public static String koreaMoreSymbol = mbsBaseDir + "korea-moreSymbol.txt";
    public static String koreaAllInOne = mbsBaseDir + "allInOne-korea.txt";

    // 注音符號，自製碼表，只能打符號，不能打漢字
    public static String zyfhs5000 = mbsBaseDir + "zyfh-5000.txt";
    // 粵語拼音，整合兩個碼表GitHub上rime-aca的粵拼⁺正寫字碼表、
    // MDict字典粤语发声字典081217.mdx簡化字碼表，對簡化字更友好。
    // 聲調用英文字母v底個數表示
    public static String jyutping20000 = mbsBaseDir + "jyutping-allInOne.txt";
    // 普語拼音，用網上找的碼表，聲調改用英文字母m底個數表示
    public static String pinyin26000 = mbsBaseDir + "pinyin-26000.txt";
    // 兩個是伊卂Ejsoon推薦的碼表。
    public static String cjyh80000 = mbsBaseDir + "cjyahoo-80000.txt"; // 雅虎奇摩
    public static String cjms59000 = mbsBaseDir + "cjms-59000.txt"; // 微軟倉頡
    // 合一
    public static String zyfhsallInOne = mbsBaseDir + "allInOne-zyfh.txt"; // 注音符號
    public static String pinyinallInOne = mbsBaseDir + "allInOne-pinyin.txt"; // 普語拼音
    public static String cjyhallInOne = mbsBaseDir + "allInOne-cjyahoo.txt"; // 雅虎奇摩
    public static String cjmsallInOne = mbsBaseDir + "allInOne-cjms.txt"; // 微軟倉頡
    // 粵語拼音，整合兩個碼表github上rime-aca的粵拼⁺正寫字碼表、
    // mdict字典粤语发声字典081217.mdx簡化字碼表，對簡化字更友好。
    public static String jyutpingAllInOne = mbsBaseDir + "allInOne-jyutping.txt";

    // 滿文，自製碼表，參考網上下載的《滿文滿語學習教程完整版.pdf》内容。
    public static String manju100 = mbsBaseDir + "manju-100.txt";
    public static String manjuMore = mbsBaseDir + "manju-more.txt";
    public static String manjuAllInOne = mbsBaseDir + "allInOne-manju.txt";

    public static void main(String[] args) throws Exception {
        // 電腦輸入法碼表
        generateAllInOnes(false, false, true, false);
    }

    /**
     * 生成整合碼表
     * 
     * @param allWithHeaders
     *            是否加入文件頭
     * @param cjwithPhrases
     *            是否加入詞組
     * @param cj6withPhrases6
     *            六代是否加入詞組
     * @param otherImWithPhrases
     *            其他輸入法是否加入詞組
     * @throws Exception
     */
    public static void generateAllInOnes(boolean allWithHeaders, boolean cjwithPhrases, boolean cj6withPhrases6,
            boolean otherImWithPhrases) throws Exception {
        boolean otherWithPhrases = otherImWithPhrases; // 其他輸入法是否加入詞組
        boolean withPhrases = cjwithPhrases; // 是否加入詞組
        boolean withPhrases6 = cj6withPhrases6; // 六代是否加入詞組

        boolean withHeaders = allWithHeaders; // 是否加入文件頭

        // 整理词组
        if (withPhrases || withPhrases6) {
            readPhraseFile();
        }

        // 滿文
        String[] mbmanju = new String[] { manju100, manjuMore };
        genAllInOne("圈點滿文", mbmanju, manjuAllInOne, withHeaders);

        // 注音符號
        String[] mbzyfh = new String[] { zyfhs5000 };
        genAllInOne("注音符號", mbzyfh, zyfhsallInOne, withHeaders);

        // 粵語拼音
        String[] mbjyutping = new String[] { jyutping20000 };
        genAllInOne("粵語拼音", mbjyutping, jyutpingAllInOne, withHeaders);

        // 普語拼音
        String[] mbpinyin = new String[] { pinyin26000 };
        genAllInOne("普語拼音", mbpinyin, pinyinallInOne, withHeaders);

        // 日文
        String[] mbjapan = new String[] { mbnippon200, mbnipponKanji2000, mbnipponMoreSymbol };
        genAllInOne("日文假名", mbjapan, nihonAllInOne, withHeaders);

        // 韓文
        String[] mbkorea = new String[] { mbkorea10000, koreaMoreSymbol };
        genAllInOne("朝鮮諺文", mbkorea, koreaAllInOne, withHeaders);

        // 四角號碼
        if (otherWithPhrases) {
            List<String> mbfilesghm = new ArrayList<String>();
            mbfilesghm.add(mbsghm27000no5);
            genRawCjPhrases(phraseOriginFile, sghmphrase, mbfilesghm);
        }
        // 生成allInOne文件
        String[] mbsghm = new String[] { mbsghm27000, sghmphrase };
        if (!otherWithPhrases) {
            mbsghm[mbsghm.length - 1] = null;
        }
        genAllInOne("四角號碼", mbsghm, sghmallInOne, withHeaders);

        // 雅虎奇摩
        String[] mbyahoo = new String[] { cjyh80000 };
        genAllInOne("雅虎奇摩", mbyahoo, cjyhallInOne, withHeaders);

        // 微軟倉頡
        String[] mbcjms = new String[] { cjms59000 };
        genAllInOne("微軟倉頡", mbcjms, cjmsallInOne, withHeaders);

        // 詞組編碼6
        if (withPhrases6) {
            List<String> mbfiles6 = new ArrayList<String>();
            mbfiles6.add(mb670000);
            mbfiles6.add(mb6more);
            genRawCjPhrases(phraseOriginFile, mb6phrase, mbfiles6);
        }
        // 注意碼表的順序，一般爲：二萬常用字、一萬繁體、蕳化字、七萬字的、詞組
        // 生成allInOne文件6
        String[] mbs6 = new String[] { mb620000, mb610000, mb68000, mb670000, mb6more, mb6compat1000, mb6simParts400,
                mb6japkore200, mb6unif7473, mb6morePhrase, mb6phrase };
        if (!withPhrases6) {
            mbs6[mbs6.length - 1] = null;
            mbs6[mbs6.length - 2] = null;
        }
        genAllInOne("倉頡六代", mbs6, mb6allInOne, withHeaders);

        // 詞組編碼5
        if (withPhrases) {
            List<String> mbfiles5 = new ArrayList<String>();
            mbfiles5.add(mb570000);
            mbfiles5.add(mb5more);
            genRawCjPhrases(phraseOriginFile, mb5phrase, mbfiles5);
        }
        // 生成allInOne文件5
        String[] mbs5 = new String[] { mb520000, mb510000, mb58000, mb570000, mb5more, mb5phrase };
        if (!withPhrases) {
            mbs5[mbs5.length - 1] = null;
        }
        genAllInOne("倉頡五代", mbs5, mb5allInOne, withHeaders);

        // 詞組編碼3
        if (withPhrases) {
            List<String> mbfiles3 = new ArrayList<String>();
            mbfiles3.add(mb370000);
            genRawCjPhrases(phraseOriginFile, mb3phrase, mbfiles3);
        }
        // 生成allInOne文件3
        String[] mbs3 = new String[] { mb320000, mb310000, mb36000, mb370000, mb3more, mb3phrase };
        if (!withPhrases) {
            mbs3[mbs3.length - 1] = null;
        }
        genAllInOne("倉頡三代", mbs3, mb3allInOne, withHeaders);

        // 生成allInOne文件2
        String[] mbs2 = new String[] { mb26000 };
        genAllInOne("倉頡二代", mbs2, mb2allInOne, withHeaders);
    }

    /**
     * 词組原始表，去重、排序
     */
    public static void readPhraseFile() throws Exception {
        IOUtils.uniqueCodeFile(phraseOriginFile);
        IOUtils.orderCodeFile(phraseOriginFile);
    }

    /**
     * 生成詞組碼表<br />
     * srcPhraseFile 詞組源文件<br />
     * destPhrasesFile 詞組編碼後的目標文件<br />
     * mbfile 常用版字集碼表
     */
    private static void genRawCjPhrases(String srcPhraseFile, String destPhrasesFile, List<String> mbfile)
            throws Exception {
        System.out.println("生成：" + destPhrasesFile);
        // 常用版所有字符
        Set<String> cj6Set = new LinkedHashSet<String>(IOUtils.readLines(mbfile.get(0)));
        if (mbfile.size() > 1) {
            for (int index = 1; index < mbfile.size(); index++) {
                cj6Set.addAll(IOUtils.readLines(mbfile.get(index)));
            }
        }
        // 做成映射，字符為鍵，編碼列表爲值
        Map<String, List<String>> charCodesMap = PhraseUtils.getMbSetMapByChar(cj6Set);
        // 所有詞組
        List<String> allphrases = new ArrayList<String>(IOUtils.readLines(srcPhraseFile));
        // 六代詞組
        List<String> resPhrases = new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + "开始。");

        int count = 0;
        for (String phr : allphrases) {
            // 词組的編碼列表
            List<String> phrcods = PhraseUtils.getPhraseCode(phr, charCodesMap);
            if (null != phrcods && !phrcods.isEmpty()) {
                for (String cod : phrcods) {
                    resPhrases.add(cod + " " + phr);
                    count++;
                }
            } else {
                System.out.println("没有编碼：" + phr);
            }
            if (count >= 10000) {
                System.out.println(sdf.format(new Date()) + ": " + resPhrases.size());
                IOUtils.writeFile(destPhrasesFile, resPhrases);
                count = 0;
            }
        }
        System.out.println(sdf.format(new Date()) + "结束：" + resPhrases.size());
        IOUtils.writeFile(destPhrasesFile, resPhrases);

        // IOUtils.orderCodeFile(destPhrasesFile);
    }

    /**
     * 生成allInOne文件
     * 
     * @param name
     *            目標碼表名字
     * @param mbs
     *            要合併的碼表
     * @param 生成的目標文件
     * @author t
     * @time 2016-12-15下午11:36:15
     */
    private static void genAllInOne(String name, String[] mbs, String destFile, boolean withHeaders) throws Exception {
        System.out.println(destFile);
        Set<String> codeLines = new LinkedHashSet<String>();

        List<String> destFileHeads = new ArrayList<String>();
        destFileHeads.add("encode=UTF-8");
        destFileHeads.add("name=" + name);
        destFileHeads.add("key=abcdefghijklmnopqrstuvwxyz");
        destFileHeads.add("len=6");
        destFileHeads.add("wildcard=*");
        destFileHeads.add("commit=1 6 0");
        destFileHeads.add("");
        destFileHeads.add("[DATA]");
        if (withHeaders) {
            codeLines.addAll(destFileHeads);
        }

        for (int i = 0; i < mbs.length; i++) {
            if (null == mbs[i]) {
                continue;
            }
            List<String> temp = IOUtils.readLines(mbs[i]);
            for (String code : temp) {
                if (!codeLines.contains(code)) {
                    codeLines.add(code);
                }
            }
        }
        System.out.println("所有行數：" + codeLines.size());
        IOUtils.writeFile(destFile, new ArrayList<String>(codeLines));
    }

}
