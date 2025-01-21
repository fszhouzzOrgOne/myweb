package random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class RandomWorkTest {

    public static void main(String[] args) throws Exception {
        List<String> lines = readLinesInFile("src\\random\\works.txt");
        String work = pickRandowmItem(lines);
        System.out.println(work);
    }

    private static String pickRandowmItem(List<String> lines) {
        SecureRandom sran = new SecureRandom();
        return lines.get(sran.nextInt(lines.size()));
    }

    private static List<String> readLinesInFile(String string) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File("src\\random\\works.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        while (true) {
            line = reader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            lines.add(line);
        };
        reader.close();
        return lines;
    }
}
