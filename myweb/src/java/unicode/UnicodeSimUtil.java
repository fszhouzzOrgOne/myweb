package unicode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 統一碼符號
 * 
 * 
 * @author fszhouzz@qq.com
 * @time 2018年10月17日 下午7:17:36
 */
public class UnicodeSimUtil {
    private static List<String> biaoqingList = new ArrayList<String>(Arrays.asList(":-)", ":-D", ":->", ":-p", ";-)",
            ":-O", ":-(", ":-<", ":-*", ":-×", "Orz", "orz", "(^_^)", "(^-^)", "(^ ^)", "(^.^)", "(^o^)", "(^q^)",
            "(^ɜ^)", "(>_<)", "(>c<)", "(>﹏<)", "(>ɛ<)", "(ˉ﹁ˉ)", "(ˉˇˉ)", "(ˉ.ˉ)", "(ˉ~ˉ)", "(ˉoˉ)", "(ˉʒˉ)", "(ˉ_ˉ)",
            "(ˉɜˉ)", "(ˉɛˉ)", "(ˉωˉ)", "(_ _)", "(-.-)", "(-_-)", "(=ɛ=)", "(= =b)", "(= =#)", "(+﹏+)", "(*_*)",
            "(×_×)", "(ˋ﹏ˊ)", "('﹏')", "(ˋωˊ)", "(ˋ^ˊ)", "(ˇ_ˇ)", "( • - •)", "(;_;)", "(;o;)", "(˙ɷ˙)", "(˙Ꙫ˙)",
            "(·ꙫ·)", "(˙︿˙)", "(˙ω˙)", "(˙０˙)", "(°ο°)", "(°○°)", "(°△°)", "(°_°)", "(º_º)", "(o_o)", "(o_O)", "⊙_⊙",
            "⊙▲⊙", "●_⊙", "●_●", "●0●", "●︿●", "●ω●", "●▽●", "●△●", "●^●", "●-●", "(ＱＱ)", "($_$)", "(@_@)", "(~O~)",
            "(T-T)", "(TT)", "(T_T)", "(ToT)", "(〒_〒)", "π_π", "(◞_◟)", "◆⊙◆", "(?﹏?)", "(?o?)", "∩ω∩", "∩_∩", "∪︿∪",
            "╯▽╰", "￣▽￣", "－▽－", "≧◇≦", "≧︿≦", "(≥▂≤)", "(≥o≤)", "︶︿︶", "︶O︶", "╯︿╰", "╯ㅁ╰", "╰_╯", "→_→", "=^ ^=",
            "ε==3"));

    public static void main(String[] args) {

    }

    /**
     * 中文
     * 
     * @author fszhouzz@qq.com
     * @time 2018年9月25日 下午8:19:26
     * @return
     */
    public static List<String> getCnListString() {
        String cnstr = "， 、 。 ？ ！ ： ； … ‘’ “” ＇＇ ＂＂ 〃〃 〝〞 ❛❜ ❝❞ ❟ ❠ （） 〔〕 〈〉 《》 «» ［］ ｛｝ 「」 『』 〖〗 【】 — ＋ ± － × ÷ ＝ ＜ ＞ ～ ｀ ＠ ＃ ¥ ￥ Ұ ұ ＄ ％ ＆ ＊ ｜ ‖ ／ ＼ ˉ ˊ ˇ ˋ ˙ ． · • 々 ⿰ ⿱ ⿲ ⿳ ⿴ ⿵ ⿶ ⿷ ⿸ ⿹ ⿺ ⿻";
        String[] facesArr = cnstr.split(" +");
        List<String> list = new ArrayList<String>();
        for (String str : facesArr) {
            list.add(str);
        }
        return list;
    }

    /**
     * 中文部首
     * 
     * @author fszhouzz@qq.com
     * @time 2018年9月25日 下午8:19:26
     * @return
     */
    public static List<String> getCnPartListString() {
        return getListByString(
                "々丨亅丿乛一乙乚丶八勹匕冫刂儿二匚阝丷卩冂冖凵亻厶亠匸讠廴又艹屮彳巛辶廾彐彑口宀犭彡饣扌氵纟囗忄幺弋尢夂灬歹卝旡耂肀牜爿攴攵礻殳尣爻曰爫癶歺钅疒罒衤疋业艸虍覀糸糹镸辵豸釒靣飠髟鬲黽黹夊禸舛襾釆〇α乁乀巜乂丄丆丅龴丩刄亇丌丬乇卂孒乊卄夨乆龶丰冄兂冘龷丯円龵毌卬卅罓朩匁予戋龸甴氺冎丗仺氶叏丱戉両乑龹朿帇亙丣囪乕幷戼丳栆単眔埀宻豙睂臦");
    }

    /**
     * 获取英文符號
     */
    public static List<String> getEnListString() {
        List<String> list = Arrays.asList(",", ".", "_", "?", "!", ":", ";", "' '", "\" \"", "@", "#", "$", "%", "℅",
                "‰", "‱", "^", "&", "~", "`", "+", "-", "*", "=", "\\", "/", "|", "<", ">", "( )", "[ ]", "{ }", "⁅ ⁆",
                "¿ ?", "¡ !", "‽", "‼", "⁇", "⁈", "⁉", "Tab");
        String str2 = "ᴀ ʙ ᴄ ᴅ ᴇ ғ ɢ ʜ ɪ ᴊ ᴋ ʟ ᴍ ɴ ᴏ ᴘ ǫ ʀ s ᴛ ᴜ ᴠ ᴡ x ʏ ᴢ";
        String str22 = " 𝒜 ℬ 𝒞 𝒟 ℰ ℱ 𝒢 ℋ ℐ 𝒥 𝒦 ℒ ℳ 𝒩 𝒪 𝒫 𝒬 ℛ 𝒮 𝒯 𝒰 𝒱 𝒲 𝒳 𝒴 𝒵  ";
        String str23 = " 𝒶 𝒷 𝒸 𝒹 ℯ 𝒻 ℊ 𝒽 𝒾 𝒿 𝓀 𝓁 𝓂 𝓃 ℴ 𝓅 𝓆 𝓇 𝓈 𝓉 𝓊 𝓋 𝓌 𝓍 𝓎 𝓏  ";
        String str3 = "⒜ ⒝ ⒞ ⒟ ⒠ ⒡ ⒢ ⒣ ⒤ ⒥ ⒦ ⒧ ⒨ ⒩ ⒪ ⒫ ⒬ ⒭ ⒮ ⒯ ⒰ ⒱ ⒲ ⒳ ⒴ ⒵";
        String str4 = "ⓐ ⓑ ⓒ ⓓ ⓔ ⓕ ⓖ ⓗ ⓘ ⓙ ⓚ ⓛ ⓜ ⓝ ⓞ ⓟ ⓠ ⓡ ⓢ ⓣ ⓤ ⓥ ⓦ ⓧ ⓨ ⓩ";
        String str5 = "Ⓐ Ⓑ Ⓒ Ⓓ Ⓔ Ⓕ Ⓖ Ⓗ Ⓘ Ⓙ Ⓚ Ⓛ Ⓜ Ⓝ Ⓞ Ⓟ Ⓠ Ⓡ Ⓢ Ⓣ Ⓤ Ⓥ Ⓦ Ⓧ Ⓨ Ⓩ";
        String str6 = "🇦 🇧 🇨 🇩 🇪 🇫 🇬 🇭 🇮 🇯 🇰 🇱 🇲 🇳 🇴 🇵 🇶 🇷 🇸 🇹 🇺 🇻 🇼 🇽 🇾 🇿";
        String str7 = "© ® ™ 🔠 🔡 🔢 🔣 🔤 🅰 🆎 🅱 🆑 🆒 🆓 ℹ 🆔 Ⓜ 🆕 🆖 🅾 🆗 🅿 🆘 🆙 🆚 🔙 🔚 🔛 🔜 🔝 📴 🏧 🚾";
        list = mergeFaceString2List(list, str7);
        list = mergeFaceString2List(list, str3);
        list = mergeFaceString2List(list, str4);
        list = mergeFaceString2List(list, str5);
        list = mergeFaceString2List(list, str2 + str22 + str23);
        list = mergeFaceString2List(list, str6);
        return list;
    }

    /**
     * 特殊符號
     * 
     * @author fszhouzz@qq.com
     * @time 2018年10月17日 下午7:24:09
     * @return
     */
    public static List<String> getSpecialListString() {
        return getListByString(
                "╳︿﹀︵︶︹︺︷︸︻︼︽︾﹁﹂﹃﹄/\\︴﹌﹉﹊￣¨‥―﹍﹎﹏＿╭╮╰╯◟◞◜◝ˉˊˇˋ˙℠℡™©®♩♪♫♬♭♮♯¶‡†♂♀§№☆★♡♥○●⊙◎Θ◇◆□■△▲▽▼※〒▪⁜⁕⁎⁑⁂⁚∶∵∴⁖⁝⋰⋱∷⁘⁛⁞⁙〓°Ψ⊕卍卐囍㈱￡⇒⇔↖↑↗←↹→↙↓↘҈҉̶⃢⏎⇧⇪⌂⌘☢☣⌥⎋⌫⌦⌨");
    }

    /**
     * 數學
     * 
     * @author fszhouzz@qq.com
     * @time 2018年9月25日 下午11:01:44
     * @return
     */
    public static List<String> getMathListByString() {
        Set<String> set = new LinkedHashSet<String>();
        for (Character i = '\u2200'; i <= '\u22FF'; i++) {
            set.add(i.toString());
        }
        String math1 = "＋－±×÷＝≠≈≡╱╲✘✔√≤≥＜＞≮≯≪≫∫∬∭∮∯∰∱∲∳∝∞∈∋⊆⊇⊂⊃∪∩∧∨∵∴∷⊥∥∠⊿⌒⊙≌∽≒≦≧′″º℃Ｆ℉Å∑∏∀∃∂ℓΩ";
        String math2 = "⅟½↉⅓⅔¼¾⅕⅖⅗⅘⅙⅚⅐⅛⅜⅝⅞⅑⅒％℅‰‱";
        String math3 = "⁰ ¹ ² ³ ⁴ ⁵ ⁶ ⁷ ⁸ ⁹ ⁺ ⁻ ⁼ ⁽⁾ ⁿ ₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉ ₊ ₋ ₌ ₍₎ ₐ ₑ ₒ ₓ ₔ";
        String math4 = "㉐㋌㋍㋎㋏㍱㍲㍳㍴㍵㍶㍷㍸㍹㍺㎀㎁㎂㎃㎄㎅㎆㎇㎈㎉㎊㎋㎌㎍㎎㎏㎐㎑㎒㎓㎔㎕㎖㎗㎘㎙㎚㎛㎜㎝㎞㎟㎠㎡㎢㎣㎤㎥㎦㎧㎨㎩㎪㎫㎬㎭㎮㎯㎰㎱㎲㎳㎴㎵㎶㎷㎸㎹㎺㎻㎼㎽㎾㎿㏀㏁㏂㏃㏄㏅㏆㏇㏈㏉㏊㏋㏌㏍㏎㏏㏐㏑㏒㏓㏔㏕㏖㏗㏘㏙㏚㏛㏜㏝㏞㏟㏿";
        List<String> list = new ArrayList<String>();
        list.addAll(getListByString(math1));
        list.addAll(getListByString(math2));
        list.addAll(mergeFaceString2List(null, math3));
        list.addAll(getListByString(math4));
        for (String one : list) {
            if (!set.contains(one)) {
                set.add(one);
            }
        }
        return new ArrayList<String>(set);
    }

    /**
     * 序號
     * 
     * @author fszhouzz@qq.com
     * @time 2018年10月17日 下午7:26:07
     * @return
     */
    public static List<String> getOrderListByString() {
        return getListByString(
                "⓪①②③④⑤⑥⑦⑧⑨⑩⑪⑫⑬⑭⑮⑯⑰⑱⑲⑳㉑㉒㉓㉔㉕㉖㉗㉘㉙㉚㉛㉜㉝㉞㉟㊱㊲㊳㊴㊵㊶㊷㊸㊹㊺㊻㊼㊽㊾㊿❶❷❸❹❺❻❼❽❾❿⓫⓬⓭⓮⓯⓰⓱⓲⓳⓴㉈㉉㉊㉋㉌㉍㉎㉏１２３４５６７８９０⒈⒉⒊⒋⒌⒍⒎⒏⒐⒑⒒⒓⒔⒕⒖⒗⒘⒙⒚⒛⑴⑵⑶⑷⑸⑹⑺⑻⑼⑽⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇〡〢〣〤〥〦〧〨〩十㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩㊀㊁㊂㊃㊄㊅㊆㊇㊈㊉㊣〇一二三四五六七八九十零壹贰貳叁叄肆伍陆陸柒捌玖拾佰仟万萬亿億吉太拍艾ⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫ");
    }

    // 貨幣符號
    // http://www.runoob.com/charsets/ref-utf-currency.html
    // https://unicode-table.com/cn/20B6/
    public static List<String> getMoneyListString() {
        String faceStr = "圓 圆 元 円 ¥ ￥ Ұ ұ $ ＄ ₳ ฿ ₿ € ₡ ₵ ￠ ¢ ₠ ₢ ₾ ₫ ₯ ￡ £ ₤ ₣ ƒ ₲ ₭ Kčs ₺ ℳ ₥ ₦ ¤ ₱ ₽ ₧ ₨ ₴ ₷ ₮ ₸ ₶ ៛ ₩ ރ zł ₻ ₼ ֏ ₹ ₰ ₪ ؋ ﷼ ৲ ৳ ૱ ௹ ៛ 💰 💴 💵 💶 💷 💸 💳 💹 💱 💲 🏦 🏧";
        List<String> list = mergeFaceString2List(null, faceStr);
        return list;
    }

    /**
     * 獲取文化符號
     */
    public static List<String> getWenhuaListString() {
        String str1 = "☯⚊⚋⚌⚍⚎⚏☰☷☳☶☲☵☱☴卍卐❂☭☠☤☥☦☧☨☩☪☫☬☮☸☽☾♕♚♛✙✚✛✜✝✞✟✠✡✢";
        List<String> list = getListByString(str1);
        String faceStr = "🛐 ⚛ 🕉 ✡ ☸ ☯ ✝ ☦ ☪ ☮ 🕎 🔯 ♈ ♉ ♊ ♋ ♌ ♍ ♎ ♏ ♐ ♑ ♒ ♓ ⛎ ⛪ 🕌 🕍 ⛩ 🕋";
        list = mergeFaceString2List(list, faceStr);
        return list;
    }

    /**
     * 獲取時節符號
     */
    public static List<String> getTimeEventListString() {
        String str1 = "㋀㋁㋂㋃㋄㋅㋆㋇㋈㋉㋊㋋㏠㏡㏢㏣㏤㏥㏦㏧㏨㏩㏪㏫㏬㏭㏮㏯㏰㏱㏲㏳㏴㏵㏶㏷㏸㏹㏺㏻㏼㏽㏾㍙㍚㍛㍜㍝㍞㍟㍠㍡㍢㍣㍤㍥㍦㍧㍨㍩㍪㍫㍬㍭㍮㍯㍰㍘";
        List<String> list = getListByString(str1);
        String faceStr = "⌛ ⏳ ⌚ ⏰ ⏱ ⏲ 🕰 🕛 🕧 🕐 🕜 🕑 🕝 🕒 🕞 🕓 🕟 🕔 🕠 🕕 🕡 🕖 🕢 🕗 🕣 🕘 🕤 🕙 🕥 🕚 🕦 🌑 🌒 🌓 🌔 🌕 🌖 🌗 🌘 🌙 🌚 🌛 🌜 🌡 ☀ 🌝 🌞 ⭐ 🌟 🌠 🌁 🌃 🌄 🌅 🌆 🌇 🌉 ♨ 🌌 ☁ ⛅ ⛈ 🌤 🌥 🌦 🌧 🌨 🌩 🌪 🌫 🌬 🌀 🌈 🌂 ☂ ☔ ⛱ ⚡ ❄ ☃ ⛄ ☄ 🔥 💧 🌊 🎃 🎄 🎆 🎇 ✨ 🎈 🎉 🎊 🎋 🎍 🎐 🎑 🎀 🎁 🎗 🎟 🎫";
        list = mergeFaceString2List(list, faceStr);
        return list;
    }

    /**
     * 獲取文字表情
     * 
     * @author fszhouzz@qq.com
     * @time 2018年10月17日 下午7:17:11
     * @return
     */
    public static List<String> getBiaoqingListString() {
        return biaoqingList;
    }

    /**
     * 表情列表1 2 3
     */
    public static List<String> getFacesListString1() {
        String faces = "😀 😁 😂 😃 😄 😅 😆 😉 😊 😋 😎 😍 😘 😗 😙 😚 ☺ 🙂 🤗 🤔 😐 😑 😶 🙄 😏 😣 😥 😮 🤐 😯 😪 😫 😴 😌 😛 😜 😝 😒 😓 😔 😕 🙃 🤑 😲 ☹ 🙁 😖 😞 😟 😤 😢 😭 😦 😧 😨 😩 😬 😰 😱 😳 😵 😡 😠 😷 🤒 🤕 😇 🤓 😈 👿 👹 👺 💀 ☠ 👻 👽 👾 🤖 💩 👶 👦 👧 👨 👩 👴 👵 ";
        faces += " 💪 👈 👉 ☝ 👆 🖕 👇 ✌ 🖖 🤘 🖐 ✋ 👌 👍 👎 ✊ 👊 👋 ✍ 👏 👐 🙌 🙏 💅 👂 👃 👣 👀 👁 🗨 👅 👄 💋 💘 ❤ 💓 💔 💕 💖 💗 💙 💚 💛 💜 💝 💞 💟 ❣ 💌 💤 💢 💣 💥 💦 💨 💫 💬 🗯 💭 🕳 ";
        faces += " 👓 🕶 👔 👕 👖 👗 👙 👚 👛 👜 👝 🛍 🎒 👞 👟 👠 👡 👢 👑 👒 🎩 🎓 ⛑ 📿 💄 💍 💎 ";
        faces += " 🐭 🐁 🐀 🐹 🐿 🐮 🐂 🐃 🐄 🐯 🐅 🐆 🦁 🐱 🐈 😺 😸 😹 😻 😼 😽 🙀 😿 😾 🐰 🐇 🐲 🐉 🐍 🐢 🐊 🐸 🐴 🐎 🦄 🐏 🐑 🐐 🐪 🐫 🐵 🐒 🙈 🙉 🙊 🦃 🐔 🐓 🐣 🐤 🐥 🐦 🐧 🕊 🐶 🐕 🐩 🐺 🐷 🐖 🐗 🐽 🐘 🐻 🐨 🐼 🐾 🐳 🐋 🐬 🐟 🐠 🐡 🐙 🐚 🦀 🐌 🐛 🐜 🐝 🐞 🕷 🕸 🦂 ";
        faces += " 💐 🌸 💮 🏵 🌹 🌺 🌻 🌼 🌷 🌱 🌲 🌳 🌴 🌵 🌾 🌿 ☘ 🍀 🍁 🍂 🍃 🍇 🍈 🍉 🍊 🍋 🍌 🍍 🍎 🍏 🍐 🍑 🍒 🍓 🍅 🍆 🌽 🌶 🍄 🌰 ";
        faces += " 🍞 🧀 🍖 🍗 🍔 🍟 🍕 🌭 🌮 🌯 🍳 🍲 🍿 🍱 🍘 🍙 🍚 🍛 🍜 🍝 🍠 🍢 🍣 🍤 🍥 🍡 🍦 🍧 🍨 🍩 🍪 🎂 🍰 🍫 🍬 🍭 🍮 🍯 🍼 ☕ 🍵 🍶 🍾 🍷 🍸 🍹 🍺 🍻 🍽 🍴 🔪 🏺";
        List<String> list = mergeFaceString2List(null, faces);
        return list;
    }

    public static List<String> getFacesListString2() {
        String faces = "🌍 🌎 🌏 🌐 🗺 🏔 ⛰ 🌋 🗻 🏕 🏖 🏜 🏝 🏞 🏟 🏛 🏗 🏘 🏙 🏚 🏠 🏡 🏢 🏣 🏤 🏥 🏦 🏨 🏩 🏪 🏫 🏬 🏭 🏰 💒 🗼 🗽 ⛲ ⛺ 🎠 🎡 🎢 💈 🎪 🎭 🖼 🎨 🎰 🚂 🚃 🚄 🚅 🚆 🚇 🚈 🚉 🚊 🚝 🚞 🚋 🚌 🚍 🚎 🚐 🚑 🚒 🚓 🚔 🚕 🚖 🚗 🚘 🚙 🚚 🚛 🚜 🚲 🚏 🛣 🛤 ⛽ 🚨 🚥 🚦 🚧 ⚓ ⛵ 🚤 🛳 ⛴ 🛥 🚢 ✈ 🛩 🛫 🛬 💺 🚁 🚟 🚠 🚡 🛰 🚀 🛎 🚪 🛏 🛋 🚽 🚿 🛁 🎖 🏆 🏅 ⚽ ⚾ 🏀 🏐 🏈 🏉 🎾 🎱 🎳 🏏 🏑 🏒 🏓 🏸 🎯 ⛳ ⛸ 🎣 🎽 🎿 🎮 🕹 🎲 ♠ ♥ ♦ ♣ 🃏 🀄 🎴 🔇 🔈 🔉 🔊 📢 📣 📯 🔔 🔕 🎼 🎵 🎶 🎙 🎚 🎛 🎤 🎧 📻 🎷 🎸 🎹 🎺 🎻 📱 📲 ☎ 📞 📟 📠 🔋 🔌 💻 🖥 🖨 ⌨ 🖱 🖲 💽 💾 💿 📀 🎥 🎞 📽 🎬 📺 📷 📸 📹 📼 🔍 🔎 🔬 🔭 📡 🕯 💡 🔦 🏮 📔 📕 📖 📗 📘 📙 📚 📓 📒 📃 📜 📄 📰 🗞 📑 🔖 🏷 ✉ 📧 📨 📩 📤 📥 📦 📫 📪 📬 📭 📮 🗳 ✏ ✒ 🖋 🖊 🖌 🖍 📝 💼 📁 📂 🗂 📅 📆 🗒 🗓 📇 📈 📉 📊 📋 📌 📍 📎 🖇 📏 📐 ✂ 🗃 🗄 🗑 🔒 🔓 🔏 🔐 🔑 🗝 🔨 ⛏ ⚒ 🛠 🗡 ⚔ 🔫 🏹 🛡 🔧 🔩 ⚙ 🗜 ⚗ ⚖ 🔗 ⛓ 💉 💊 🚬 ⚰ ⚱ 🗿 🛢 🔮 ";
        List<String> list = mergeFaceString2List(null, faces);
        return list;
    }

    public static List<String> getFacesListString3() {
        String faces = "🏧 🚮 🚰 ♿ 🚹 🚺 🚻 🚼 🚾 🛂 🛃 🛄 🛅 ⚠ 🚸 ⛔ 🚫 🚳 🚭 🚯 🚱 🚷 📵 🔞 ☢ ☣ ";
        faces += " ⬆ ↗ ➡ ↘ ⬇ ↙ ⬅ ↖ ↕ ↔ ↩ ↪ ⤴ ⤵ 🔃 🔄 🔀 🔁 🔂 ▶ ⏩ ⏭ ⏯ ◀ ⏪ ⏮ 🔼 ⏫ 🔽 ⏬ ⏸ ⏹ ⏺ ⏏ 🎦 🔅 🔆 📶 📳 📴 ♀ ♂ ⚕ ♻ ⚜ 🔱 📛 🔰 ⭕ ✅ ☑ ✔ ✖ ❌ ❎ ➕ ➖ ➗ ➰ ➿ 〽 ✳ ✴ ❇ ‼ ⁉ ❓ ❔ ❕ ❗ 〰 💯 ▪ ▫ ◻ ◼ ◽ ◾ ⬛ ⬜ 🔶 🔷 🔸 🔹 🔺 🔻 💠 🔘 🔲 🔳 ⚪ ⚫ 🔴 🔵 🏁 🚩 🏴 🏳";
        List<String> list = mergeFaceString2List(null, faces);
        return list;
    }

    /**
     * 國際音標符號
     * 
     * @author fszhouzz@qq.com
     * @time 2018年3月20日下午3:22:15
     * @return
     */
    public static List<String> getIpaListString() {
        String faceStr = "a ɐ ɑ ɒ æ ɑ̃ ʌ b ɓ ʙ β c ç ɕ ɔ ɔ̃ d ɗ ɖ ð d͡z d͡ʒ d̠͡ʑ ɖ͡ʐ e ə ɘ ɛ ɛ̃ ɜ ɚ ɝ f ɸ g ɡ ɠ ɢ ʛ ɣ ɤ h ħ ɦ ɧ ʜ ɥ i ĩ ɨ ɪ j ʝ ɟ ʄ k l ɫ ɬ ɭ ʟ ɮ ʎ m ɱ ɯ ɰ n ɲ ŋ ɳ ȵ ɴ o õ ɵ ø ɞ œ œ̃ ɶ ɔ ɔ̃ ʊ ʘ p ɸ p͡f q r ɾ ɼ ɺ ɽ ɹ ɻ ʀ ʁ ɿ ˞ s ʂ ʃ ʄ ʅ t ʈ θ t͡ɕ ʈ͡ʂ t͡s t͡ʃ u ũ ʉ ʊ v ʋ ѵ ⱱ ʌ w w̃ ʍ ɰ x χ y ʏ ɥ ʎ z ʑ ʐ ʒ ʔ ʡ ʕ ʢ ʘ ǀ ǂ ǁ ǃ ˈ ˌ ː ˑ ̆ || ‖ ‿ x͡y x͡ ͡ . ̋ ˥ ́ ˦ ̄ ˧ ̀ ˨ ̏ ˩ ̌ ̂ ꜜ ꜛ ↗ ↘ ̥ ̬ ʰ ʱ ʲ ʷ ˠ ˁ ̹ ̜ ̟ ̠ ̈ ̽ ̩ ̯ ˞ ̃ ̰ ̝ ̞ ̘ ̙ ̪ ̺ ̻ ⁿ ˡ ̚";
        String[] facesArr = faceStr.split(" ");
        List<String> list = new ArrayList<String>();
        for (String str : facesArr) {
            list.add(str);
        }
        return list;
    }

    /**
     * 注音
     * 
     * @author fszhouzz@qq.com
     * @time 2018年10月17日 下午7:31:41
     * @return
     */
    public static List<String> getPinyinListString() {
        return getListByString(
                "āáǎàōóǒòêēéěèīíǐìūúǔùǖǘǚǜüˉˊˇˋ˙˪˫◌〪〭〫〬ㄅㄆㄇㄈㄉㄊㄋㄌㄍㄎㄏㄐㄑㄒㄓㄔㄕㄖㄗㄘㄙㄚㄛㄜㄝㄞㄟㄠㄡㄢㄣㄤㄥㄦㄧㄨㄩㄪㄫㄬㄭㄮㄯㆠㆡㆢㆣㆤㆥㆦㆧㆨㆩㆪㆫㆬㆭㆮㆯㆰㆱㆲㆳㆴㆵㆶㆷㆸㆹㆺ");
    }

    /**
     * 獲取日文符號
     */
    public static List<String> getJapanListString() {
        // 先按日文符號區內順序
        // 與下面的有褈複
        String str1 = "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんゔゕゖ゙゚゛゜ゝゞゟ゠ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶヷヸヹヺ・ーヽヾ";

        str1 += "あいうゔえおアイウヴエオぁぃぅぇぉァィゥェォかゕきくけゖこカヵキクケヶコがぎぐげごガギグゲゴさしすせそサシスセソざじずぜぞザジズゼゾたちつってとタチツッテトだぢづでどダヂヅデドなにぬねのナニヌネノはひふへほハヒフヘホばびぶべぼバビブベボぱぴぷぺぽパピプペポまみむめもマミムメモやゆよヤユヨゃゅょャュョらりるれろラリルレロわゎゐゑをワヮヷヰヸヱヹヲヺんンー゠々ゝゞヽヾ〆乄ゟ゚゛゜ヿ・";
        str1 += "ㇰㇱㇲㇳㇴㇵㇶㇷㇸㇹㇺㇻㇼㇽㇾㇿ㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩㈪㈫㈬㈭㈮㈯㈰㈱㈲㈳㈴㈵㈶㈷㈸㈹㈺㈻㈼㈽㈾㈿㉀㉁㉂㉃㉄㉅㉆㉇㊀㊁㊂㊃㊄㊅㊆㊇㊈㊉㊊㊋㊌㊍㊎㊏㊐㊑㊒㊓㊔㊕㊖㊗㊘㊙㊚㊛㊜㊝㊞㊟㊠㊡㊢㊣㊤㊥㊦㊧㊨㊩㊪㊫㊬㊭㊮㊯㊰㋐㋑㋒㋓㋔㋕㋖㋗㋘㋙㋚㋛㋜㋝㋞㋟㋠㋡㋢㋣㋤㋥㋦㋧㋨㋩㋪㋫㋬㋭㋮㋯㋰㋱㋲㋳㋴㋵㋶㋷㋸㋹㋺㋻㋼㋽㋾㌀㌁㌂㌃㌄㌅㌆㌇㌈㌉㌊㌋㌌㌍㌎㌏㌐㌑㌒㌓㌔㌕㌖㌗㌘㌙㌚㌛㌜㌝㌞㌟㌠㌡㌢㌣㌤㌥㌦㌧㌨㌩㌪㌫㌬㌭㌮㌯㌰㌱㌲㌳㌴㌵㌶㌷㌸㌹㌺㌻㌼㌽㌾㌿㍀㍁㍂㍃㍄㍅㍆㍇㍈㍉㍊㍋㍌㍍㍎㍏㍐㍑㍒㍓㍔㍕㍖㍗㍻㍼㍽㍾㍿";
        List<String> list = getListByString(str1);
        String faceStr = "🈁 🈂 🈷 🈶 🈯 🈹 🈚 🈲 🈸 🈴 🈳 🈺 🈵 🀄 🉐 🉑 ㊗ ㊙ ㊣ ㊀ ㊁ ㊂ ㊃ ㊄ ㊅ ㊆ ㊇ ㊈ ㊉ ㊎ ㊍ ㊌ ㊋ ㊏ ㊐ ㊊ ㊚ ㊛ ㊤ ㊥ ㊦ ㊧ ㊨ ㊞ ㊑ ㊒ ㊓ ㊓ ㊔ ㊕ ㊖ ㊗ ㊗ ㊘ ㊜ ㊝ ㊟ ㊠ ㊡ ㊢ ㊩ ㊪ ㊫ ㊬ ㊬ ㊭ ㊮ ㊮ ㊯ ㊰ ㊙ 🎌 🗾 👘 🏣 🏯 🎎 🎏";
        list = mergeFaceString2List(list, faceStr);
        return list;
    }

    /**
     * 諺文符號
     * 
     * @author fszhouzz@qq.com
     * @time 2018年10月17日 下午7:33:10
     * @return
     */
    public static List<String> getKoreaListString() {
        return getListByString(
                "ㅏㅓㅗㅜㅡㅣㅐㅔㅚㅟㅑㅕㅛㅠㅒㅖㅘㅙㅝㅞㅢㄱㄲㅋㄷㄸㅌㅂㅃㅍㅈㅉㅊㅅㅆㅎㄴㅁㅇㄹᅀᄼᄽᄾᄿᅎᅏᅐᅑᅔᅕᅌᄐᆞᆝᆟᆠᆡᆢ㈀㈁㈂㈃㈄㈅㈆㈇㈈㈉㈊㈋㈌㈍㈎㈏㈐㈑㈒㈓㈔㈕㈖㈗㈘㈙㈚㈛㈜㈝㈞㉠㉡㉢㉣㉤㉥㉦㉧㉨㉩㉪㉫㉬㉭㉮㉯㉰㉱㉲㉳㉴㉵㉶㉷㉸㉹㉺㉻㉼㉽㉾㉿");
    }

    /**
     * 拉丁
     * 
     * @author fszhouzz@qq.com
     * @time 2018年10月17日 下午7:34:16
     * @return
     */
    public static List<String> getLatinListString() {
        return getListByString("ÄÆÅÀÁÂÃÇĒĚÈÉÊËÐÌÍÎÏÖØÒÓÔÕÑÙÚÛÜÝÞäæåàáâãçēěèéêëðìíîïöøòóôõñùúûüýþ");
    }

    /**
     * 希俄
     * 
     * @author fszhouzz@qq.com
     * @time 2018年10月17日 下午7:34:55
     * @return
     */
    public static List<String> getGreerussiaListString() {
        return getListByString(
                "αβγδεζηθικλμνξοπρστυφχψωΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩабвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ");
    }

    /**
     * 製表
     * 
     * @author fszhouzz@qq.com
     * @time 2018年10月17日 下午7:35:44
     * @return
     */
    public static List<String> getTableListString() {
        return getListByString("┌┍┎┏┐┑┒┓─┄┈├┝┞┟┠┡┢┣│┆┊┬┭┮┯┰┱┲┳┼┽┾┿╀╁╂└┕┖┗┘┙┚┛━┅┉┤┥┦┧┨┩┪┫┴┵┶┷┸┹┺┻╄╅╆╇╈╉╊╋");
    }

    /**
     * 表情字符串合并入列表 faceStr 各表情字符用空格分隔
     */
    private static List<String> mergeFaceString2List(List<String> list, String faceStr) {
        String[] facesArr = faceStr.trim().split(" +");
        Set<String> set = new LinkedHashSet<String>();
        if (null != list && !list.isEmpty()) {
            set.addAll(list);
        }
        for (String face : facesArr) {
            if (null != face && !"".equals(face) && !set.contains(face)) {
                set.add(face);
            }
        }
        return new ArrayList<String>(set);
    }

    /**
     * 把字符串转成字符串的列表
     * 
     * @author fszhouzz@qq.com
     * @time 2017年12月12日上午10:14:57
     * @param string
     * @return
     */
    private static List<String> getListByString(String keys) {
        Set<String> set = new LinkedHashSet<String>();
        for (int i = 0; i < keys.length(); i++) {
            Character cha = keys.charAt(i);
            if (!set.contains(cha.toString())) {
                set.add(cha.toString());
            }
        }
        return new ArrayList<String>(set);
    }
}
