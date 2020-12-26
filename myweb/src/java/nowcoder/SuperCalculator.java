package nowcoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// (1+2)*3+4+(-5)+((7+8)*6+9)*2
public class SuperCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            String res = calculate(line.trim());
            System.out.println(res);
        }
    }

    private static String calculate(String trim) {
        Composite tmp = Composite.generateComposite(trim);
        return tmp.result().setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    static class Composite {
        private Composite left;
        private char sign;
        private Composite right;

        public Composite() {

        }

        public Composite(Composite l, char s, Composite r) {
            this.left = l;
            this.sign = s;
            this.right = r;
        }

        // 來到這個參數中，都只有加減，或乘除法
        public static Composite generateComposite(String formula) {
            int openIndex = formula.indexOf("(");
            // 找第一級的加減號
            List<Integer> plusMinus = new ArrayList<Integer>();
            List<Integer> multiplyDivide = new ArrayList<Integer>();
            int openCnt = 0;
            for (int i = 0; i < formula.length(); i++) {
                char cha = formula.charAt(i);
                if (cha == '(') {
                    openCnt++;
                } else if (cha == ')') {
                    openCnt--;
                } else if (cha == '+' || cha == '-') {
                    if (0 == openCnt) {
                        plusMinus.add(i);
                    }
                } else if (cha == '*' || cha == '/') {
                    if (0 == openCnt) {
                        multiplyDivide.add(i);
                    }
                }
            }
            // 沒有加減號
            if (plusMinus.isEmpty()) {
                if (multiplyDivide.isEmpty()) {
                    // 有括号
                    if (0 == openIndex) {
                        return generateComposite(
                                formula.substring(1, formula.length() - 1));
                    } else {
                        return new Element(formula);
                    }
                } else {
                    return generateCompositeUsingPos(formula, multiplyDivide);
                }
            } else {
                return generateCompositeUsingPos(formula, plusMinus);
            }
        }

        private static Composite generateCompositeUsingPos(String formula,
                List<Integer> pos) {
            if (pos.size() == 1 && pos.get(0) == 0) {
                return new Element(formula);
            }
            Composite tmp = generateComposite(formula.substring(0, pos.get(0)));
            for (int i = 0; i < pos.size(); i++) {
                int one = pos.get(i);
                char sign = formula.charAt(one);
                if (pos.size() > i + 1) {
                    tmp = new Composite(tmp, sign, generateComposite(
                            formula.substring(one + 1, pos.get(i + 1))));
                } else {
                    tmp = new Composite(tmp, sign,
                            generateComposite(formula.substring(one + 1)));
                }
            }
            return tmp;
        }

        public BigDecimal result() {
            BigDecimal lres = left.result();
            BigDecimal rres = right.result();
            if (sign == '+') {
                return lres.add(rres);
            } else if (sign == '-') {
                return lres.subtract(rres);
            } else if (sign == '*') {
                return lres.multiply(rres);
            } else if (sign == '/') {
                return lres.divide(rres);
            }
            return null;
        }

        @Override
        public String toString() {
            boolean withoutBracket = (sign == '*' || sign == '/');
            return withoutBracket ? ("" + left + sign + right)
                    : ("(" + left + sign + right + ")");
        }
    }

    static class Element extends Composite {
        private BigDecimal value;

        public Element(String formula) {
            value = new BigDecimal(formula.replaceAll("\\(|\\)", ""));
        }

        public Element(BigDecimal val) {
            value = val;
        }

        @Override
        public BigDecimal result() {
            return value;
        }

        @Override
        public String toString() {
            return value.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
    }

}
