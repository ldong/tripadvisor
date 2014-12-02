import com.mongodb.*;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by ldong on 12/1/14.
 */
public class Main {
    public static void main(String[] args) {
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
//        Util.getCollectionFromUrl();

        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient("localhost", 27017);
            // or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
            DB db = mongoClient.getDB("webs");
            Set<String> colls = db.getCollectionNames();
//            for (String s : colls) {
//                System.out.println(s);
//            }
            DBCollection coll = db.getCollection("webs");
//            DBObject myDoc = coll.findOne();
//            System.out.println(myDoc);
//            System.out.println(myDoc.get("versions"));
            BasicDBObject query = new BasicDBObject();
            query.put("web", "tripadvisor.com");
            DBCursor cursor = coll.find(query);
            System.out.println(cursor);
            while (cursor.hasNext()) {
                DBObject curr = cursor.next();
                System.out.println(curr.get("web"));
                LinkedHashMap<String, String> list = (LinkedHashMap<String, String>) curr.get("versions");
                String str = list.get("11/2/2014");
                if(str == null){
                    System.out.println(list.get("current"));
                } else {
                    System.out.println(str);
                }
            }
            System.out.println("Done");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }

        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println(d);
        System.out.println(dateFormat.format(d));

        try {
            System.out.println("Start");
            System.out.println(Util.getContentFromUrlByDates("tripadvisor.com", "1/2/2014"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
