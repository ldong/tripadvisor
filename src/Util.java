import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.*;
/**
 * Utility class
 * Created by ldong on 12/1/14.
 */


public class Util {
    public static int DAYS = 14;

    public static Date getDateXDaysBeforeDateY(int x, Date y){
        // 24 hrs in presentation of Epoch
        long day = 86400000;
        long curr = y.getTime();
        return new Date(curr- (x*day));
    }

    public static SiteTuple getCollectionFromUrl(String url) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
        DB db = mongoClient.getDB("mydb");
        return null;
    }
}
