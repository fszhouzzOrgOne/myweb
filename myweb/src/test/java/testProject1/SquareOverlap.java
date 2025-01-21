package testProject1;

import java.util.Scanner;

public class SquareOverlap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Square sq1 = new Square(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        Square sq2 = new Square(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        sc.close();
        if (overlap(sq1, sq2)) {
            System.out.println(true);
        } else {
            System.out.println(overlap(sq2, sq1));
        }

    }

    public static boolean overlap(Square sq1, Square sq2) {
        if (sq1.toString().equals(sq2.toString())) {
            return true;
        }
        if (pointInSquare(sq1, sq2)) {
            return true;
        } else {
            return edgeInSquare(sq1, sq2);
        }
    }

    private static boolean edgeInSquare(Square sq1, Square sq2) {
        int sq1MinX = sq1.getMinX();
        int sq1MaxX = sq1.getMaxX();
        // 如果X在 sq2里，则minY<= sq2的minY maxY>= sq2的maxY
        if ((sq1MinX > sq2.getMinX() && sq1MinX < sq2.getMaxX())
                || (sq1MaxX > sq2.getMinX() && sq1MaxX < sq2.getMaxX())) {
            if (sq1.getMinY() >= sq2.getMaxY() && sq1.getMinY() <= sq2.getMinY()) {
                return true;
            }
        }

        int sq1MinY = sq1.getMinY();
        int sq1MaxY = sq1.getMaxY();

        if ((sq1MinY > sq2.getMaxY() && sq1MinY < sq2.getMinY())
                || (sq1MaxY > sq2.getMaxY() && sq1MaxY < sq2.getMinY())) {
            if ((sq1MinX >= sq2.getMinX() && sq1MinX <= sq2.getMaxX())) {
                return true;
            }
        }

        return false;
    }

    private static boolean pointInSquare(Square sq1, Square sq2) {
        if (over(sq1.getMinX(), sq1.getMinY(), sq2)) {
            return true;
        } else if (over(sq1.getMinX(), sq1.getMaxY(), sq2)) {
            return true;
        } else if (over(sq1.getMaxX(), sq1.getMinY(), sq2)) {
            return true;
        } else if (over(sq1.getMaxX(), sq1.getMaxY(), sq2)) {
            return true;
        }
        return false;
    }

    private static boolean over(int x1, int y1, Square sq2) {
        int minX = sq2.getMinX();
        int maxX = sq2.getMaxX();
        int minY = sq2.getMinY();
        int maxY = sq2.getMaxY();
        return x1 > minX && x1 < maxX && y1 > minY && y1 < maxY;
    }

}

class Square {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Square(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    @Override
    public String toString() {
        return "Square [minX=" + minX + ", minY=" + minY + ", maxX=" + maxX + ", maxY=" + maxY + "]";
    }

}
