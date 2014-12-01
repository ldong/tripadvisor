import com.firebase.client.Firebase;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by ldong on 12/1/14.
 */
public class Main {
    public static void main(String[] args) {
        Firebase myFirebaseRef = new Firebase("https://tripadvisorminiproj.firebaseio.com");
        myFirebaseRef.child("somePath");
        System.out.println(myFirebaseRef.getRoot());
//        LRUMemoryCache cache = new LRUMemoryCache();
//        cache.initialize();
//        LinkedHashMap<CacheWebsite, String> lru = new LinkedHashMap<CacheWebsite, String>();
        LRU lru = new LRU();
        for (int i = 0; i < 2; i++) {
            CacheWebsite web = new CacheWebsite("google.com","12/1/2014");
            System.out.println(web.hashCode());
            lru.put(web, "Hi");
            System.out.println(lru.size());
            System.out.println(lru);
        }
        CacheWebsite web = new CacheWebsite("google.com","12/1/2014");
        System.out.println(web.hashCode());
        String content = (String)lru.get(web);
        lru.put(web, "Hi");
        System.out.println(content);
        System.out.println(Util.getDateXDaysBeforeDateY(1, web.getDate()));
        System.out.println(Util.getDateXDaysBeforeDateY(2, web.getDate()));
        System.out.println(Util.getDateXDaysBeforeDateY(3, web.getDate()));

    }
}
