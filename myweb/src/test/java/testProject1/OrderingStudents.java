package testProject1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 本来学生按1,2,3.n排序的。<br/>
 * 今要按身高升序，再按体重升序，再按序号排序<br/>
 * 输入如：<br/>
 * 4 100 100 120 130 40 30 60 50〈br/>
 * 要求输出学生的编号顺序：2 1 3 4
 * 
 */
public class OrderingStudents {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        float[] hights = new float[cnt];
        for (int i = 0; i < cnt; i++) {
            hights[i] = sc.nextFloat();
        }
        ArrayList<Student> ss = new ArrayList<Student>(cnt);
        for (int i = 0; i < cnt; i++) {
            ss.add(new Student(i + 1, hights[i], sc.nextFloat()));
        }
        sc.close();
        ss.sort((x, y) -> {
            int hRes = Float.compare(x.getHf(), y.getHf());
            if (hRes != 0) {
                return hRes;
            }
            int wRes = Float.compare(x.getWf(), y.getWf());
            if (wRes != 0) {
                return wRes;
            }
            return Integer.compare(x.getNo(), y.getNo());
        });
        String res = ss.stream().map(x -> x.getNo().toString()).collect(Collectors.joining(" "));
        System.out.println(res);
    }

}

class Student {
    private Integer no;
    private Float hf;
    private Float wf;

    public Student(Integer no, Float hf, Float wf) {
        super();
        this.no = no;
        this.hf = hf;
        this.wf = wf;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Float getHf() {
        return hf;
    }

    public void setHf(Float hf) {
        this.hf = hf;
    }

    public Float getWf() {
        return wf;
    }

    public void setWf(Float wf) {
        this.wf = wf;
    }
}
