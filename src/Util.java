import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

import com.mongodb.*;
/**
 * Utility class
 * Created by ldong on 12/1/14.
 */


public class Util {
    public static int MAX_NUMBER_DAYS_OF_HISTORY = 14;
    public static Date getDateXDaysBeforeDateY(int x, Date y){
        // 24 hrs in presentation of Epoch
        long day = 86400000;
        long curr = y.getTime();
        return new Date(curr- (x*day));
    }

    public static String convertDateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }

    public static Date convertStringToDate(String str){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getContentFromUrlByDates(String url, String date) throws UnknownHostException {
        MongoClient mongoClient = null;
        String content = "Not Found";
        try {
            mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("webs");
            DBCollection coll = db.getCollection("webs");
            BasicDBObject query = new BasicDBObject();
            query.put("web", url);
            DBCursor cursor = coll.find(query);
            Date d;
            while (cursor.hasNext()) {
                DBObject curr = cursor.next();
                LinkedHashMap<String, String> list = (LinkedHashMap<String, String>) curr.get("versions");
                int count = 1;
                do {
                    if(list.get(date) == null) {
                        d = Util.convertStringToDate(date);
                        d = getDateXDaysBeforeDateY(1, d);
                        date = Util.convertDateToString(d);
                    } else {
                        content = list.get(date);
                        break;
                    }
                    count++;
                } while(count <= MAX_NUMBER_DAYS_OF_HISTORY);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
            return content;
        }
    }
}
