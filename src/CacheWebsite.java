/**
 * Created by ldong on 12/1/14.
 */

import java.io.Serializable;
import java.util.Date;

public class CacheWebsite implements Serializable {
    public String url;
    public Date date;

    /**
     * Constructor with URL and String
     *
     * @param url
     * @param date
     */
    public CacheWebsite(String url, String date) {
        this.url = url;
        this.date = Util.convertStringToDate(date);
    }

    /**
     * Constructor with URL and Date
     *
     * @param url
     * @param date
     */
    public CacheWebsite(String url, Date date) {
        this.url = url;
        this.date = date;
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

    public String getUrl() {
        return url;
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        return "URL: " + url + " Date: " + date;
    }
}
