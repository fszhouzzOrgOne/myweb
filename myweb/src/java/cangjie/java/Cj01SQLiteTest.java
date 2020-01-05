package cangjie.java;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cangjie.java.util.IOUtils;

// 去交集後 315493
// 純六代 
// 純六加詞組 
// 倉頡三五 164901 只要ANSI則 105618
public class Cj01SQLiteTest {
    private static String mbsBaseDir = Cj00AllInOneTest.mbsBaseDir;
    private static String mb2allInOne = Cj00AllInOneTest.mb2allInOne;
    private static String mb3allInOne = Cj00AllInOneTest.mb3allInOne;
    private static String mb5allInOne = Cj00AllInOneTest.mb5allInOne;
    private static String mb6allInOne = Cj00AllInOneTest.mb6allInOne;
    private static String karinaallInOne = Cj00AllInOneTest.nihonAllInOne; // 日語假名
    private static String zyfhsallInOne = Cj00AllInOneTest.zyfhsallInOne; // 注音符號
    private static String jyutpingAllInOne = Cj00AllInOneTest.jyutpingAllInOne;// 粵拼
    private static String pinyinallInOne = Cj00AllInOneTest.pinyinallInOne; // 拼音
    private static String sghmallInOne = Cj00AllInOneTest.sghmallInOne; // 四角號碼
    private static String cjyhallInOne = Cj00AllInOneTest.cjyhallInOne; // 雅虎奇摩
    private static String cjmsallInOne = Cj00AllInOneTest.cjmsallInOne; // 微軟倉頡
    private static String koreaallInOne = Cj00AllInOneTest.koreaAllInOne; // 朝鮮諺文
    private static String manjuallInOne = Cj00AllInOneTest.manjuAllInOne; // 圈點滿文
    private static String phoneticAllInOne = Cj00AllInOneTest.phoneticAllInOne; // 國際音標
    private static String koxhanhAllInOne = Cj00AllInOneTest.koxhanhAllInOne; // 中古漢語
    private static String sionTanTsengAllInOne = Cj00AllInOneTest.sionTanTsengAllInOne; // 曾版湘潭話

    private static List<String> lines2 = null;
    private static List<String> lines3 = null;
    private static List<String> lines5 = null;
    private static List<String> lines6 = null;
    private static List<String> lineska = null;
    private static List<String> lineszy = null;
    private static List<String> linesjyutp = null;
    private static List<String> linespy = null;
    private static List<String> linessghm = null;
    private static List<String> linescjyh = null;
    private static List<String> linescjms = null;
    private static List<String> lineskorea = null;
    private static List<String> linesmanju = null;
    private static List<String> linesipa = null;
    private static List<String> linesKoxhanh = null;
    private static List<String> linesSionTanTseng = null;
    // 交集碼表
    private static List<String> linesInter = null;
    // 倉頡三五
    private static List<String> lines35 = null;

    private static final String cjGen2 = "cj2";
    private static final String cjGen3 = "cj3";
    private static final String cjGen35 = "cj35";
    private static final String cjGen5 = "cj5";
    private static final String cjGen6 = "cj6";
    private static final String cjGenJyutp = "jyutp";
    private static final String cjGenpy = "pinyin";
    private static final String cjGenka = "karina";
    private static final String cjGenzy = "zyfh";
    private static final String cjGensghm = "sghm";
    private static final String cjGencjyh = "cjyhqm";
    private static final String cjGencjms = "cjms";
    private static final String cjGenkorea = "korea";
    private static final String cjGenInter = "cjcommon";
    private static final String cjGenManju = "manju";
    private static final String cjGenIpa = "ipa";
    private static final String cjGenKoxhanh = "kohan";
    private static final String cjGenSionTanTseng = "siontts";

    private static Map<String, Map<String, Integer>> mbOrderNoMaps = null; // 文字排序權值
    private static String ORDER_MAP_DEFAULT_KEY = "default";

    private static Set<String> ansichars = null;
    static {
        ansichars = new HashSet<String>();
        List<String> linesAnsi = IOUtils
                .readLines(Cj00AllInOneTest.mbsBaseDir + "ansichar.txt");
        for (String line : linesAnsi) {
            if (line.contains(" ")) {
                String[] keyVal = line.split(" +");
                String val = keyVal[1];

                if (!ansichars.contains(val)) {
                    ansichars.add(val);
                }
            }
        }
    }

    /** 只要ANSI的字 */
    private static boolean isOnlyAnsi = false;
    // 是否加入庫中
    private static boolean withManju = true; // 滿文
    private static boolean withKorea = true; // 韓文
    private static boolean withJyutp = true; // 粤拼
    private static boolean withKarina = true; // 日文
    private static boolean withSghm = true; // 四角號碼
    private static boolean withPy = true; // 拼音
    private static boolean withZy = true; // 注音
    private static boolean withKoxhanh = true; // 中古漢語
    private static boolean withSionTanTseng = true; // 曾版湘潭話

    private static boolean withCangjieOthers = false; // 加入其他倉頡？
    private static boolean withCangjie6 = false; // 加入蒼頡六？
    private static boolean withCangjie5 = false; // 加入蒼頡五？
    private static boolean withCangjie35 = false; // 加入倉頡三五？
    private static boolean withCangjie3 = false; // 加入倉頡三

    public static void main(String args[]) throws Exception {
        // 互斥的版本選擇
        boolean edition1 = false; // 1版本默認字體 同2
        boolean edition2 = true; // 2版本自定義字體 560140 韓日单字 431154
        boolean edition3 = false; // 版本倉頡三 150962
        boolean edition35 = false; // 版本倉頡三五 183716 ANSI 105618
        boolean edition35only5 = false; // 版本倉頡三五只要五代 178083 ansi 103934
        boolean edition5 = false; // 版本五代 242227 ansi 103934 其它只留下拼音注音 170217
        boolean edition6 = false; // 版本六 201084
        boolean edition62 = false; // 版本六，帶詞組 676903 其中詞475817
        // 倉頡字典
        boolean editionDict = false; // 倉頡字典 324754
        // 其他輸入法是否去掉，純三代要設置這個爲true
        boolean withNotAllOthers = false;
        if (edition3 || edition5) {
            withNotAllOthers = true;
        }

        // 驗證多個版本，edition35only5除外
        List<Boolean> edits = new ArrayList<Boolean>();
        edits.add(edition1);
        edits.add(edition2);
        edits.add(edition3);
        edits.add(edition35);
        edits.add(edition5);
        edits.add(edition6);
        edits.add(edition62);
        edits.add(editionDict);
        int trues = 0;
        for (Boolean b : edits) {
            if (b) {
                trues++;
            }
        }
        if (trues > 1) {
            System.out.println("不能有多個版本號！");
            return;
        } else if (trues < 1) {
            System.out.println("不能沒有版本號！");
            return;
        }

        if (withNotAllOthers) {
            withManju = false; // 滿文
            withKorea = false; // 韓文
            withJyutp = true; // 粤拼
            withKarina = false; // 日文
            withSghm = false; // 四角號碼
            withPy = true; // 拼音
            withZy = true; // 注音
            withKoxhanh = false; // 中古漢語
            withSionTanTseng = false;
        }
        if (edition1 || edition2) {
            withCangjie6 = true;
            withCangjie5 = true;
            withCangjieOthers = true;
            withCangjie35 = false;
            withCangjie3 = true;
        }
        if (edition3) {
            withCangjie6 = false;
            withCangjie5 = false;
            withCangjieOthers = false;
            withCangjie35 = false;
            withCangjie3 = true;
        }
        if (edition35) {
            withCangjie6 = false;
            withCangjie5 = false;
            withCangjieOthers = false;
            withCangjie35 = true;
            withCangjie3 = false;
        }
        if (edition5) {
            withCangjie6 = false;
            withCangjie5 = true;
            withCangjieOthers = false;
            withCangjie35 = false;
            withCangjie3 = false;
        }
        if (edition6 || edition62) {
            withCangjie6 = true;
            withCangjie5 = false;
            withCangjieOthers = false;
            withCangjie35 = false;
            withCangjie3 = false;
        }
        if (editionDict) {
            withManju = false; // 滿文
            withKorea = false; // 韓文
            withJyutp = false; // 粤拼
            withKarina = false; // 日文
            // withSghm = true; // 四角號碼
            withPy = false; // 拼音
            withZy = false; // 注音
            withKoxhanh = false; // 中古漢語
            withSionTanTseng = false;

            withCangjie6 = true;
            withCangjie5 = true;
            withCangjieOthers = true;
            withCangjie35 = false;
            withCangjie3 = true;
        }

        // 生成碼表
        if (edition62) {
            Cj00AllInOneTest.generateAllInOnes(false, false, true, false, true);
        } else {
            Cj00AllInOneTest.generateAllInOnes(false, false, false, false,
                    true);
        }
        // 生成交集碼表
        Cj01MbFormatTest.getCjMbsIntersection();

        insertIntoDb(edition35only5);
    }

    /**
     * 數據庫操作
     * 
     * @author fszhouzz@qq.com
     * @time 2018年7月28日 下午4:18:02
     * @param edition35only5
     *            是版本倉頡三五代合一，且又只要五代
     */
    private static void insertIntoDb(boolean edition35only5) {
        Connection c = null;
        Statement stmt = null;
        try {
            String mbdbFile = mbsBaseDir + "mbdb" + File.separator
                    + "cjmbdb.db";
            File file = new File(mbdbFile);
            if (file.exists()) {
                file.delete();
            }

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + mbdbFile);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql_gen = "create table t_mb_type (_id integer primary key autoincrement, "
                    + " type_code varchar(20), " + " type_name varchar(20));";
            stmt.executeUpdate(sql_gen);
            String sql = "create table t_mb_content (_id integer primary key autoincrement, "
                    + " type_code varchar(20), " + " mb_code varchar(20), "
                    + " mb_char varchar(20), "
                    + " mb_order_no integer DEFAULT 0)";
            stmt.executeUpdate(sql);
            String sqlInter = "create table t_mb_content_intersect (_id integer primary key autoincrement, "
                    + " mb_code varchar(20), mb_char varchar(20), mb_order_6 integer DEFAULT 0, "
                    + " mb_order_5 integer DEFAULT 0, mb_order_3 integer DEFAULT 0, "
                    + " mb_order_yh integer DEFAULT 0, mb_order_ms integer DEFAULT 0)";
            stmt.executeUpdate(sqlInter);

            // 索引在前面建，才會有效
            String indexSql = " CREATE INDEX index_mb_content_code ON t_mb_content (type_code, mb_code); ";
            stmt.executeUpdate(indexSql);
            indexSql = " CREATE INDEX index_mb_content_char ON t_mb_content (type_code, mb_char); ";
            stmt.executeUpdate(indexSql);
            indexSql = " CREATE INDEX index_mb_content_intrst_code ON t_mb_content_intersect (mb_code); ";
            stmt.executeUpdate(indexSql);
            indexSql = " CREATE INDEX index_mb_content_intrst_char ON t_mb_content_intersect (mb_char); ";
            stmt.executeUpdate(indexSql);

            lines2 = IOUtils.readLines(mb2allInOne);
            lines3 = IOUtils.readLines(mb3allInOne);
            lines5 = IOUtils.readLines(mb5allInOne);
            lines6 = IOUtils.readLines(mb6allInOne);
            lineska = IOUtils.readLines(karinaallInOne);
            lineszy = IOUtils.readLines(zyfhsallInOne);
            linesjyutp = IOUtils.readLines(jyutpingAllInOne);
            linespy = IOUtils.readLines(pinyinallInOne);
            linessghm = IOUtils.readLines(sghmallInOne);
            linescjyh = IOUtils.readLines(cjyhallInOne);
            linescjms = IOUtils.readLines(cjmsallInOne);
            lineskorea = IOUtils.readLines(koreaallInOne);
            linesmanju = IOUtils.readLines(manjuallInOne);
            linesipa = IOUtils.readLines(phoneticAllInOne);
            linesKoxhanh = IOUtils.readLines(koxhanhAllInOne);
            linesSionTanTseng = IOUtils.readLines(sionTanTsengAllInOne);
            // 交集碼表
            linesInter = IOUtils.readLines(Cj01MbFormatTest.cj356hyms_allInOne);
            // 倉頡三五
            lines35 = null;

            initMbOrderNoMap(lines6, cjGen6);
            initMbOrderNoMap(lines5, cjGen5);
            initMbOrderNoMap(lines3, cjGen3);
            initMbOrderNoMap(linescjyh, cjGencjyh);
            initMbOrderNoMap(linescjms, cjGencjms);
            initMbOrderNoMap(lines2, cjGen2);
            initMbOrderNoMap(lines6, ORDER_MAP_DEFAULT_KEY);

            // 去掉交集，注意和Cj01MbFormatTest.getCjMbsIntersection();方法对应
            Set<String> interset = new HashSet<String>(linesInter); // 轉成Set刪除，快些
            lines3.removeAll(interset);
            lines5.removeAll(interset);
            lines6.removeAll(interset);
            linescjyh.removeAll(interset);
            linescjms.removeAll(interset);
            if (withCangjie35) {
                Set<String> set35 = new HashSet<String>(lines5);
                if (!edition35only5) {
                    for (String str : lines3) {
                        if (!set35.contains(str)) {
                            set35.add(str);
                        }
                    }
                }
                lines35 = new ArrayList<String>(set35);
            }

            sql_gen = getInsertGenSql(cjGen2, "倉頡二代");
            stmt.executeUpdate(sql_gen);
            sql_gen = getInsertGenSql(cjGen3, "倉頡三代");
            stmt.executeUpdate(sql_gen);
            sql_gen = getInsertGenSql(cjGen5, "倉頡五代");
            stmt.executeUpdate(sql_gen);
            sql_gen = getInsertGenSql(cjGen6, "蒼頡六代");
            stmt.executeUpdate(sql_gen);

            String cj35name = "倉頡三五";
            if (edition35only5) {
                cj35name = "倉頡五代";
            }
            sql_gen = getInsertGenSql(cjGen35, cj35name);
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGenJyutp, "粵語拼音");
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGenpy, "普語拼音");
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGenzy, "注音符號");
            stmt.executeUpdate(sql_gen);
            sql_gen = getInsertGenSql(cjGenka, "日文假名");
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGensghm, "四角號碼");
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGencjyh, "雅虎奇摩");
            stmt.executeUpdate(sql_gen);
            sql_gen = getInsertGenSql(cjGencjms, "微軟倉頡");
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGenkorea, "朝鮮諺文");
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGenManju, "圈點滿文");
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGenIpa, "國際音標");
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGenKoxhanh, "中古漢語");
            stmt.executeUpdate(sql_gen);

            sql_gen = getInsertGenSql(cjGenSionTanTseng, "曾版湘潭話");
            stmt.executeUpdate(sql_gen);

            // 不自動提交
            c.setAutoCommit(false);

            // 倉頡交集碼表
            insertMbdbIntersect(stmt, linesInter, true);
            c.commit();
            System.out.println("insert " + cjGenInter + " successfully");
            selectCountAll(stmt);

            if (withCangjieOthers) {
                // 倉頡二代
                insertMbdb(stmt, cjGen2, lines2, true);
                c.commit();
                System.out.println("insert " + cjGen2 + " successfully");
                selectCountAll(stmt);

                // 雅虎奇摩
                insertMbdb(stmt, cjGencjyh, linescjyh, true);
                c.commit();
                System.out.println("insert " + cjGencjyh + " successfully");
                selectCountAll(stmt);

                // 微軟倉頡
                insertMbdb(stmt, cjGencjms, linescjms, true);
                c.commit();
                System.out.println("insert " + cjGencjms + " successfully");
                selectCountAll(stmt);
            }
            if (withCangjie3) {
                // 倉頡三代
                insertMbdb(stmt, cjGen3, lines3, true);
                c.commit();
                System.out.println("insert " + cjGen3 + " successfully");
                selectCountAll(stmt);
            }
            if (withCangjie5) {
                // 倉頡五代
                insertMbdb(stmt, cjGen5, lines5, true);
                c.commit();
                System.out.println("insert " + cjGen5 + " successfully");
                selectCountAll(stmt);
            }
            // 蒼頡六代
            if (withCangjie6) {
                insertMbdb(stmt, cjGen6, lines6, true);
                c.commit();
                System.out.println("insert " + cjGen6 + " successfully");
                selectCountAll(stmt);
            }
            // 倉頡三五
            if (withCangjie35) {
                insertMbdb(stmt, cjGen35, lines35, true);
                c.commit();
                System.out.println("insert " + cjGen35 + " successfully");
                selectCountAll(stmt);
            }
            // 四角號碼
            if (withSghm) {
                insertMbdb(stmt, cjGensghm, linessghm, true);
                c.commit();
                System.out.println("insert " + cjGensghm + " successfully");
                selectCountAll(stmt);
            }
            // 粵語拼音
            if (withJyutp) {
                insertMbdb(stmt, cjGenJyutp, linesjyutp, true);
                c.commit();
                System.out.println("insert " + cjGenJyutp + " successfully");
                selectCountAll(stmt);
            }
            // 拼音
            if (withPy) {
                insertMbdb(stmt, cjGenpy, linespy, true);
                c.commit();
                System.out.println("insert " + cjGenpy + " successfully");
                selectCountAll(stmt);
            }
            // 注音符號
            if (withZy) {
                insertMbdb(stmt, cjGenzy, lineszy, false);
                c.commit();
                System.out.println("insert " + cjGenzy + " successfully");
                selectCountAll(stmt);
            }
            // 日語假名
            if (withKarina) {
                insertMbdb(stmt, cjGenka, lineska, true);
                c.commit();
                System.out.println("insert " + cjGenka + " successfully");
                selectCountAll(stmt);
            }
            // 朝鮮諺文
            if (withKorea) {
                insertMbdb(stmt, cjGenkorea, lineskorea, true);
                c.commit();
                System.out.println("insert " + cjGenkorea + " successfully");
                selectCountAll(stmt);
            }
            // 圈點滿文 cjGenManju
            if (withManju) {
                insertMbdb(stmt, cjGenManju, linesmanju, true);
                c.commit();
                System.out.println("insert " + cjGenManju + " successfully");
                selectCountAll(stmt);
            }
            // 國際音標
            insertMbdb(stmt, cjGenIpa, linesipa, false);
            c.commit();
            System.out.println("insert " + cjGenIpa + " successfully");
            selectCountAll(stmt);
            // 中古漢語
            if (withKoxhanh) {
                insertMbdb(stmt, cjGenKoxhanh, linesKoxhanh, false);
                c.commit();
                System.out.println("insert " + cjGenKoxhanh + " successfully");
                selectCountAll(stmt);
            }
            // 曾版湘潭話
            if (withSionTanTseng) {
                insertMbdb(stmt, cjGenSionTanTseng, linesSionTanTseng, false);
                c.commit();
                System.out.println(
                        "insert " + cjGenSionTanTseng + " successfully");
                selectCountAll(stmt);
            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Inited database successfully");
    }

    /**
     * 初始化文字排序權值
     * 
     * @param lines6
     */
    private static void initMbOrderNoMap(List<String> lines, String key) {
        if (null == mbOrderNoMaps) {
            mbOrderNoMaps = new HashMap<String, Map<String, Integer>>();
        }
        Map<String, Integer> mbOrderNoMap = new HashMap<String, Integer>();
        if (ORDER_MAP_DEFAULT_KEY.equals(key)) {
            int size = lines.size();
            List<String> rate = IOUtils.readLines(mbsBaseDir + "zi-order.txt");
            int rateSize = rate.size();
            for (int i = 0; i < rateSize; i++) {
                String one = rate.get(i);
                if (!mbOrderNoMap.keySet().contains(one)) {
                    mbOrderNoMap.put(one, size + rateSize - i);
                }
            }
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.contains(" ")) {
                    String[] keyVal = line.split(" +");
                    String val = keyVal[1];

                    if (!mbOrderNoMap.keySet().contains(val)) {
                        mbOrderNoMap.put(val, size - i);
                    }
                }
            }
        } else {
            Map<String, List<String>> codeCharsList = new HashMap<String, List<String>>();
            for (String line : lines) {
                if (line.contains(" ")) {
                    String[] keyVal = line.split(" +");
                    String code = keyVal[0];
                    String val = keyVal[1];
                    List<String> chars = codeCharsList.get(code);
                    if (null == chars) {
                        codeCharsList.put(code, new ArrayList<String>());
                        chars = codeCharsList.get(code);
                    }
                    chars.add(val);
                }
            }
            List<String> codes = new ArrayList<String>(codeCharsList.keySet());
            Collections.sort(codes);
            for (String code : codes) {
                List<String> chars = codeCharsList.get(code);
                int i = chars.size();
                for (String val : chars) {
                    mbOrderNoMap.put(code + " " + val, i);
                    i--;
                }
            }
        }
        mbOrderNoMaps.put(key, mbOrderNoMap);
    }

    private static void selectCountAll(Statement stmt) throws Exception {
        String sqlSelect = " SELECT count(1) as cnt FROM t_mb_content union all  ";
        sqlSelect += " select count(1) as cnt from t_mb_content_intersect ";
        sqlSelect = "select sum(cnt) as cnt from (" + sqlSelect + ") t ";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ResultSet rs = stmt.executeQuery(sqlSelect);
            while (rs.next()) {
                int cnt = rs.getInt("cnt");
                System.out.println(sdf.format(new Date()) + " count: " + cnt);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 插入碼表
     * 
     * @author fszhouzz@qq.com
     * @time 2018年3月20日下午2:55:19
     * @param stmt
     * @param gen
     * @param lines
     * @param ordered
     *            是否取mbOrderNoMap中的排序
     * @throws Exception
     */
    private static void insertMbdb(Statement stmt, String gen,
            List<String> lines, boolean ordered) throws Exception {
        int count = 0;
        for (String line : lines) {
            if (line.contains(" ")) {
                String[] keyVal = line.split(" +");
                String cod = keyVal[0];
                String val = keyVal[1];

                if (gen.startsWith("cj") && isOnlyAnsi
                        && !ansichars.contains(val)) {
                    continue;
                }

                Integer order = 0;
                if (ordered) {
                    if (null != mbOrderNoMaps) {
                        Map<String, Integer> mbOrderNoMap = mbOrderNoMaps
                                .get(gen);
                        if (null != mbOrderNoMap) {
                            String orderKey = cod + " " + val;
                            order = (null != mbOrderNoMap.get(orderKey)
                                    ? mbOrderNoMap.get(orderKey) : 0);
                        } else {
                            mbOrderNoMap = mbOrderNoMaps
                                    .get(ORDER_MAP_DEFAULT_KEY);
                            if (null != mbOrderNoMap) {
                                order = (null != mbOrderNoMap.get(val)
                                        ? mbOrderNoMap.get(val) : 0);
                            }
                        }
                    }
                }

                String sql = getInsertSql(gen, cod, val, order);
                stmt.executeUpdate(sql);
                count++;

                if (count % 1000 == 0) {
                    selectCountAll(stmt);
                }
            }
        }
        selectCountAll(stmt);
    }

    private static void insertMbdbIntersect(Statement stmt, List<String> lines,
            boolean ordered) throws Exception {
        int count = 0;
        for (String line : lines) {
            if (line.contains(" ")) {
                String[] keyVal = line.split(" +");
                String cod = keyVal[0];
                String val = keyVal[1];

                if (isOnlyAnsi && !ansichars.contains(val)) {
                    continue;
                }

                int order6 = 0, order5 = 0, order3 = 0, orderyh = 0,
                        orderms = 0;
                if (ordered) {
                    if (null != mbOrderNoMaps) {
                        String allKey = cod + " " + val;
                        order6 = mbOrderNoMaps.get(cjGen6).get(allKey);
                        order5 = mbOrderNoMaps.get(cjGen5).get(allKey);
                        order3 = mbOrderNoMaps.get(cjGen3).get(allKey);
                        orderyh = mbOrderNoMaps.get(cjGencjyh).get(allKey);
                        orderms = mbOrderNoMaps.get(cjGencjms).get(allKey);
                    }
                }

                String sql = " insert into t_mb_content_intersect (_id,mb_code,mb_char, ";
                sql += " mb_order_6, mb_order_5, mb_order_3, mb_order_yh, mb_order_ms ) ";
                sql += " values (null, '" + cod + "', '" + val + "', ";
                sql += order6 + "," + order5 + "," + order3 + "," + orderyh
                        + "," + orderms + "); ";
                stmt.executeUpdate(sql);
                count++;

                if (count % 1000 == 0) {
                    selectCountAll(stmt);
                }
            }
        }
        selectCountAll(stmt);
    }

    private static String getInsertGenSql(String cjGen, String cjGenName) {
        String sql;
        sql = "INSERT INTO t_mb_type (_id,type_code,type_name) "
                + "VALUES (null, '" + cjGen + "', '" + cjGenName + "');";
        return sql;
    }

    private static String getInsertSql(String gen, String code, String name,
            Integer order) {
        if (null == order) {
            order = 0;
        }
        String sql;
        sql = "INSERT INTO t_mb_content (_id,type_code,mb_code,mb_char, mb_order_no) "
                + "VALUES (null, '" + gen + "', '" + code + "', '" + name
                + "', " + order + ");";
        return sql;
    }

}
