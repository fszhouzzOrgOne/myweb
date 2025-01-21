package d20220724;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 书籍叠放：〈br/> 长宽都比下面的小，才能叠在上面。给定一批书的长宽，最多能叠几本。〈br/>
 * 如[[20,16],[15,11],[10,10],[9,10]]，可以叠三本[20,16][15,11][10,10]，输出3。〈br/>
 * [[20,16],[15,11],[10,10],[9,10],[8,7]]，返回4 <br/>
 * [[20,13],[16,14],[15,11],[10,10],[9,10],[8,7]]，返回4
 */
public class T03BooksStacking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().trim().replaceAll("[ \\[\\]]+", "").split(",");
        sc.close();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < parts.length - 1; i += 2) {
            books.add(new Book(i, Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1])));
        }
        System.out.println(countBooksStacked(books));
    }

    private static int countBooksStacked(List<Book> books) {
        // map中放长宽比自己小的
        Map<Integer, List<Integer>> idMap = new HashMap<Integer, List<Integer>>();
        for (Book book : books) {
            List<Integer> subIds = idMap.getOrDefault(book.id, new ArrayList<Integer>());
            for (Book book2 : books) {
                if (book2.id != book.id && book2.len < book.len && book2.wid < book.wid) {
                    subIds.add(book2.id);
                }
            }
            idMap.put(book.id, subIds);
        }
        int res = 0;
        System.out.println(idMap);
        for (Integer id : idMap.keySet()) {
            int res1 = getDeepestLevel(idMap, id, 1);
            if (res1 > res) {
                res = res1;
            }
        }
        return res;
    }

    private static int getDeepestLevel(Map<Integer, List<Integer>> idMap, Integer id, int level) {
        if (idMap.get(id) == null || idMap.get(id).isEmpty()) {
            return level;
        }
        int res = level;
        for (Integer sub : idMap.get(id)) {
            int res1 = getDeepestLevel(idMap, sub, level + 1);
            if (res1 > res) {
                res = res1;
            }
        }
        return res;
    }

    private static class Book {
        private int id;
        private int len;
        private int wid;

        public Book(int id, int len, int wid) {
            this.id = id;
            this.len = len;
            this.wid = wid;
        }
    }
}
