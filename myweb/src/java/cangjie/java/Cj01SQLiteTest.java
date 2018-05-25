package cangjie.java;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private static Map<String, Integer> mbOrderNoMap = null; // 文字排序權值

    private static Set<String> ansichars = null;
    static {
        ansichars = new HashSet<String>();
        List<String> linesAnsi = IOUtils.readLines(Cj00AllInOneTest.mbsBaseDir + "ansichar.txt");
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

    public static void main(String args[]) throws Exception {
        boolean withManju = true; // 滿文
        boolean withKorea = true; // 韓文
        boolean withJyutp = true; // 粤拼
        boolean withKarina = true; // 日文
        boolean withSghm = true; // 四角號碼
        boolean withPy = true; // 拼音
        boolean withZy = true; // 注音
        boolean withKoxhanh = true; // 中古漢語
        boolean withCangjieOthers = false; // 加入其他倉頡？
        boolean withCangjie6 = false; // 加入蒼頡六？
        boolean withCangjie5 = false; // 加入蒼頡五？
        boolean withCangjie35 = false; // 加入倉頡三五？
        boolean withCangjie3 = false; // 加入倉頡三

        // 互斥的版本選擇
        boolean edition1 = false; // 1版本默認字體 同2
        boolean edition2 = true; // 2版本自定義字體 540455 韓日单字 380889
        boolean edition3 = false; // 版本倉頡三 177361
        boolean edition35 = false; // 版本倉頡三五 183716 ANSI 105618
        boolean edition35only5 = false; // 版本倉頡三五只要五代 178083 ansi 103934
        boolean edition5 = false; // 版本五代 211352 ansi 103934
        boolean edition6 = false; // 版本六 201084
        boolean edition62 = false; // 版本六，帶詞組 676903 其中詞475817

        // 驗證多個版本，edition35only5除外
        List<Boolean> edits = new ArrayList<Boolean>();
        edits.add(edition1);
        edits.add(edition2);
        edits.add(edition3);
        edits.add(edition35);
        edits.add(edition5);
        edits.add(edition6);
        edits.add(edition62);
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

        // 生成碼表
        if (edition62) {
            Cj00AllInOneTest.generateAllInOnes(false, false, true, false, true);
        } else {
            Cj00AllInOneTest.generateAllInOnes(false, false, false, false, true);
        }

        // 生成交集碼表
        Cj01MbFormatTest.getCjMbsIntersection();

        Connection c = null;
        Statement stmt = null;
        try {
            String mbdbFile = mbsBaseDir + "mbdb" + File.separator + "cjmbdb.db";
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
                    + " type_code varchar(20), " + " mb_code varchar(20), " + " mb_char varchar(20), "
                    + " mb_order_no integer DEFAULT 0)";
            stmt.executeUpdate(sql);

            // 索引在前面建，才會有效
            String indexSql = " CREATE INDEX index_mb_content_code ON t_mb_content (type_code, mb_code); ";
            stmt.executeUpdate(indexSql);
            indexSql = " CREATE INDEX index_mb_content_char ON t_mb_content (type_code, mb_char); ";
            stmt.executeUpdate(indexSql);

            List<String> lines2 = IOUtils.readLines(mb2allInOne);
            List<String> lines3 = IOUtils.readLines(mb3allInOne);
            List<String> lines5 = IOUtils.readLines(mb5allInOne);
            List<String> lines6 = IOUtils.readLines(mb6allInOne);
            List<String> lineska = IOUtils.readLines(karinaallInOne);
            List<String> lineszy = IOUtils.readLines(zyfhsallInOne);
            List<String> linesjyutp = IOUtils.readLines(jyutpingAllInOne);
            List<String> linespy = IOUtils.readLines(pinyinallInOne);
            List<String> linessghm = IOUtils.readLines(sghmallInOne);
            List<String> linescjyh = IOUtils.readLines(cjyhallInOne);
            List<String> linescjms = IOUtils.readLines(cjmsallInOne);
            List<String> lineskorea = IOUtils.readLines(koreaallInOne);
            List<String> linesmanju = IOUtils.readLines(manjuallInOne);
            List<String> linesipa = IOUtils.readLines(phoneticAllInOne);
            List<String> linesKoxhanh = IOUtils.readLines(koxhanhAllInOne);
            // 交集碼表
            List<String> linesInter = IOUtils.readLines(Cj01MbFormatTest.cj356hyms_allInOne);
            // 倉頡三五
            List<String> lines35 = null;

            initMbOrderNoMap(lines6);

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

            String cjGen2 = "cj2";
            String cjGen3 = "cj3";
            String cjGen35 = "cj35";
            String cjGen5 = "cj5";
            String cjGen6 = "cj6";
            String cjGenJyutp = "jyutp";
            String cjGenpy = "pinyin";
            String cjGenka = "karina";
            String cjGenzy = "zyfh";
            String cjGensghm = "sghm";
            String cjGencjyh = "cjyhqm";
            String cjGencjms = "cjms";
            String cjGenkorea = "korea";
            String cjGenInter = "cjcommon";
            String cjGenManju = "manju";
            String cjGenIpa = "ipa";
            String cjGenKoxhanh = "kohan";

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

            // 不自動提交
            c.setAutoCommit(false);

            // 倉頡交集碼表
            insertMbdb(stmt, cjGenInter, linesInter, true);
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
    private static void initMbOrderNoMap(List<String> lines) {
        mbOrderNoMap = new HashMap<String, Integer>();
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
    }

    private static void selectCountAll(Statement stmt) throws SQLException {
        String sqlSelect = "SELECT count(1) as cnt FROM t_mb_content;";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ResultSet rs = stmt.executeQuery(sqlSelect);
            while (rs.next()) {
                int cnt = rs.getInt("cnt");
                System.out.println(sdf.format(new Date()) + " count: " + cnt);
            }
            rs.close();
        } catch (Exception e) {
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
    private static void insertMbdb(Statement stmt, String gen, List<String> lines, boolean ordered) throws Exception {
        int count = 0;
        for (String line : lines) {
            if (line.contains(" ")) {
                String[] keyVal = line.split(" +");
                String cod = keyVal[0];
                String val = keyVal[1];

                if (gen.startsWith("cj") && isOnlyAnsi && !ansichars.contains(val)) {
                    continue;
                }

                Integer order = 0;
                if (ordered) {
                    order = (null != mbOrderNoMap.get(val) ? mbOrderNoMap.get(val) : 0);
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

    private static String getInsertGenSql(String cjGen, String cjGenName) {
        String sql;
        sql = "INSERT INTO t_mb_type (_id,type_code,type_name) " + "VALUES (null, '" + cjGen + "', '" + cjGenName
                + "');";
        return sql;
    }

    private static String getInsertSql(String gen, String code, String name, Integer order) {
        if (null == order) {
            order = 0;
        }
        String sql;
        sql = "INSERT INTO t_mb_content (_id,type_code,mb_code,mb_char, mb_order_no) " + "VALUES (null, '" + gen
                + "', '" + code + "', '" + name + "', " + order + ");";
        return sql;
    }

}
