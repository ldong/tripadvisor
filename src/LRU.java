/**
 * Created by ldong on 12/1/14.
 */

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: flychao88
 * Ref: http://flychao88.iteye.com/blog/1975988
 * Implementing LRU by using Fixed size LinkedHashMap
 */
public class LRU extends LinkedHashMap {
    private static final long serialVersionUID = 6918023506928428613L;
    private static int MAX_ENTRIES = 10;

    public static int getMAX_ENTRIES() {
        return MAX_ENTRIES;
    }

    public static void setMAX_ENTRIES(int max_entries) {
        MAX_ENTRIES = max_entries;
    }

    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

    public static void main(String[] args) {
        Map map = new LRU();
        System.out.println(map.size());
        for(int i = 0; i < 50; i++) {
            map.put(i, true);
            System.out.println(map.size());
            System.out.println(map);
        }
    }
}
