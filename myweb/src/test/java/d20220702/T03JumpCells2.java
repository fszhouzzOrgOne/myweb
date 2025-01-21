package d20220702;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 跳格子游戏：<br/>
 * 第一行输入n，表示要跳n个格子。<br/>
 * 下面几行，每行两个数a b，表示跳进a后，b格子开启了。<br/>
 * 输出能不能跳到所有格子中。<br/>
 * 输入如下，要输出true：<br/>
3
0 1
0 2 
 * 输入如下，要输出false：<br/>
4
0 1
2 3
2 1
 * 注意有方向。改用并查集。<br/>
 * 
 * @author Administrator
 * @time 2022年7月2日下午2:19:10
 */
public class T03JumpCells2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        // 多个树，记住根就有了
        Map<String, Set<String>> roots = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.length() < 1) {
                break;
            }
            String[] parts = line.split(" +");
            // parts[0]在某个树中，parts[1]就要放到某个树中
            List<String> targets = findRootsByLeft(roots, parts[0]);
            if (targets.isEmpty()) {
                targets.add(parts[0]);
            }
            // parts[1]如果是树根，对应的树就要合并到各个targets中
            Set<String> rightTree = roots.getOrDefault(parts[1], new HashSet<String>());
            if (rightTree.isEmpty()) {
                rightTree.add(parts[1]);
            }
            // 树的合并
            for (String root : targets) {
                Set<String> targetTree = roots.getOrDefault(root, new HashSet<String>());
                // parts[1]对应的树全加入
                targetTree.addAll(rightTree);
                // 根自己也加入
                targetTree.add(root);
                roots.put(root, targetTree);
            }
        }
        sc.close();
        List<String> fullTreeRoots = findFullTree(roots, n);
        // System.out.println(roots);
        // System.out.println(fullTreeRoots);
        System.out.println(fullTreeRoots.size() > 0);
    }

    private static List<String> findFullTree(Map<String, Set<String>> roots, int n) {
        return roots.entrySet().stream().filter(x -> x.getValue().size() >= n).map(x -> x.getKey())
                .collect(Collectors.toList());
    }

    private static List<String> findRootsByLeft(Map<String, Set<String>> roots, String left) {
        List<String> targets = new ArrayList<String>();
        for (Entry<String, Set<String>> entry : roots.entrySet()) {
            if (entry.getValue().contains(left)) {
                targets.add(entry.getKey());
            }
        }
        return targets;
    }
}
