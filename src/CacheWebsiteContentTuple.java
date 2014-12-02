/**
 * Created by ldong on 12/1/14.
 */
public class CacheWebsiteContentTuple {


    private CacheWebsite site;
    private String content;

    public CacheWebsiteContentTuple(String url, String date, String content) {
        site = new CacheWebsite(url, date);
        this.content = content;
    }

    public CacheWebsite getSite() {
        return site;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return "Cache Website: " + site + " Content: " + content;
    }
}
