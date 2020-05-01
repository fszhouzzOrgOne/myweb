package siontan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cangjie.java.Cj00AllInOneTest;
import cangjie.java.util.IOUtils;

/**
 * 基於曾毓美湘潭話拼音。i¹²臺字改正爲台。<br/>
 * ʦ用ts代替，ʨ用tɕ代替。 <br/>
 * ȵ作聲母都接i，今都改成ŋi；<br/>
 * 重音符號只用於m，不要；<br/>
 * 鼻化符號只用於e音，用n代替；<br/>
 * 按聲調分淸濁聲母的b d g ɦ ʣ dʐ ʥ七個，都取消不用。<br/>
 * 有人強調：要突出高化鼻化，不然成了四不像官話。比如張ɔ̃、望的uɔ̃、湘和潭ã、鹽ẽ等。<br/>
 * 其實基本上所有n結尾的，除了in、ən、uən、yn，都鼻化；也有說n全鼻化的。
 */
public class SionTanMbConvertTest {
    private static String mbsBaseDir = "src\\java\\siontan\\";
    private static String mbFileSrc = "[湘語]湘潭音節表-漢語多功能字庫20181215.txt";
    private static String mbFileDest = Cj00AllInOneTest.sionTanTseng3000;
    private static final Map<String, String> toneMap = new HashMap<String, String>();

    public static void main(String[] args) throws Exception {
        List<String> res = getResultLines();
        for (String line : res) {
            System.out.println(line);
        }
        IOUtils.writeFile(mbFileDest, res);
    }

    private static List<String> getResultLines() throws Exception {
        List<String> list = IOUtils.readLines(mbsBaseDir + mbFileSrc, true);
        Set<String> res = new LinkedHashSet<String>();
        for (String line : list) {
            String[] parts = line.split(" +");
            String pinyin = doConvertPhonetic(parts[0]);
            String tone = toneMap.get(parts[1]);
            if (null == tone) {
                throw new Exception("No tone matches " + parts[1]);
            }
            String[] chars = parts[2].split(",");
            for (String cha : chars) {
                String resOne = pinyin + tone + " " + cha;
                if (!res.contains(resOne)) {
                    res.add(resOne);
                }
            }
        }
        return new ArrayList<String>(res);
    }

    private static String doConvertPhonetic(String input) {
        if (null == input) {
            return null;
        }
        String res = input;
        res = res.replaceAll("ʦ", "ts");
        res = res.replaceAll("ʨ", "tɕ");
        res = res.replaceAll("ȵ", "ŋ");
        res = res.replaceAll(" ̍ ".trim(), "");
        res = res.replaceAll(" ̃ ".trim(), "n");
        res = res.replaceAll("b", "p");
        res = res.replaceAll("d", "t");
        res = res.replaceAll("g", "k");
        res = res.replaceAll("ɦ", "h");
        res = res.replaceAll("ʣ", "ts");
        res = res.replaceAll("ʥ", "tɕ");
        res = res.replaceAll("dʐ", "tʂ");
        return res;
    }

    static {
        toneMap.put("33", "q");
        toneMap.put("12", "qq");
        toneMap.put("42", "qqq");
        toneMap.put("55", "qqqq");
        toneMap.put("21", "qqqqq");
        toneMap.put("24", "qqqqqq");
    }
}
