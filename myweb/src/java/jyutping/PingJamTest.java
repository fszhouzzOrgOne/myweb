package jyutping;

import java.util.ArrayList;
import java.util.List;

public class PingJamTest {
    private static List<String> list = new ArrayList<String>();
    private static List<String> list2 = new ArrayList<String>();
    private static List<String> list3 = new ArrayList<String>();

    public static void main(String[] args) {
        List<String> res = getPingjamList();
        for (String re : res) {
            System.out.println(re);
        }
    }

    public static List<String> getPingjamList() {
        List<String> res = new ArrayList<String>();
        res.addAll(list);
        res.addAll(list2);
        res.addAll(list3);
        for (String one : list) {
            String[] part1 = one.split(" +");
            for (String two : list2) {
                String[] part2 = two.split(" +");
                String pingjam = part1[0] + part2[0];
                String ipa = "/" + part1[1].replaceAll("/", "")
                        + part2[1].replaceAll("/", "") + "/";
                res.add(pingjam + " " + ipa);
            }
        }
        return res;
    }

    static {
        list.add("b /p/");
        list.add("p /pʰ/");
        list.add("m /m/");
        list.add("f /f/");
        list.add("d /t/");
        list.add("t /tʰ/");
        list.add("n /n/");
        list.add("l /l/");
        list.add("g /k/");
        list.add("k /kʰ/");
        list.add("ng /ŋ/");
        list.add("h /h/");
        list.add("gw /kʷ/");
        list.add("kw /kʷʰ/");
        list.add("w /w/");
        list.add("z /ts/");
        list.add("c /tsʰ/");
        list.add("s /s/");
        list.add("j /j/");

        list2.add("aa /aː/");
        list2.add("aai /aːi̯/");
        list2.add("aau /aːu̯/");
        list2.add("aam /aːm/");
        list2.add("aan /aːn/");
        list2.add("aang /aːŋ/");
        list2.add("aap /aːp̚/");
        list2.add("aat /aːt̚/");
        list2.add("aak /aːk̚/");
        list2.add("ai /ɐi̯/");
        list2.add("au /ɐu̯/");
        list2.add("am /ɐm/");
        list2.add("an /ɐn/");
        list2.add("ang /ɐŋ/");
        list2.add("ap /ɐp̚/");
        list2.add("at /ɐt̚/");
        list2.add("ak /ɐk̚/");
        list2.add("e /ɛː/");
        list2.add("ei /ei̯/");
        list2.add("eu /ɛːu̯/");
        list2.add("em /ɛːm/");
        list2.add("eng /ɛːŋ/");
        list2.add("ep /ɛːp̚/");
        list2.add("ek /ɛːk̚/");
        list2.add("i /iː/");
        list2.add("iu /iːu̯/");
        list2.add("im /iːm/");
        list2.add("in /iːn/");
        list2.add("ing /eŋ/");
        list2.add("ip /iːp̚/");
        list2.add("it /iːt̚/");
        list2.add("ik /ek̚/");
        list2.add("o /ɔː/");
        list2.add("oi /ɔːy̯/");
        list2.add("ou /ou̯/");
        list2.add("on /ɔːn/");
        list2.add("ong /ɔːŋ/");
        list2.add("ot /ɔːt̚/");
        list2.add("ok /ɔːk̚/");
        list2.add("u /uː/");
        list2.add("ui /uːy̯/");
        list2.add("un /uːn/");
        list2.add("ung /oŋ/");
        list2.add("ut /uːt̚/");
        list2.add("uk /ok̚/");
        list2.add("oe /œː/");
        list2.add("eoi /ɵy̯/");
        list2.add("eon /ɵn/");
        list2.add("oeng /œːŋ/");
        list2.add("eot /ɵt̚/");
        list2.add("oek /œːk̚/");
        list2.add("yu /yː/");
        list2.add("yun /yːn/");
        list2.add("yut /yːt̚/");

        list3.add("m /m̩/");
        list3.add("ng /ŋ̩/");
    }
}
