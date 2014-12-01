import java.util.Date;

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
}
