
/**
 * Created by ldong on 11/29/14.
 */
public class SiteTuple {
    private String sha1sum;
    private String content;

    public SiteTuple(String sha1sum, String content){
        this.sha1sum = sha1sum;
        this.content = content;
    }

    public String getSha1sum(){
        return sha1sum;
    }
}