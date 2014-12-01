/**
 * Created by ldong on 12/1/14.
 */
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CacheWebsite implements Serializable {
    public String url;
    public Date date;

    /**
     * Constructor
     * @param url
     * @param date
     */
    public CacheWebsite(String url, String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            this.url = url;
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheWebsite that = (CacheWebsite) o;

        if (!date.equals(that.date)) return false;
        if (!url.equals(that.url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    public String toString(){
        return "URL: "+url+ " Date: "+date;
    }
}
