import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by ldong on 11/30/14.
 */
public class WebCacheServlet extends javax.servlet.http.HttpServlet {
    private static LRU lruCache;

    static {
        lruCache = new LRU();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String website = request.getParameter("website");
        String date = request.getParameter("date");
        CacheWebsite web = new CacheWebsite(website, date);
        String content = "";
        if (lruCache.get(web) != null) {
            content = (String) lruCache.get(web);
        } else { // Not Found locally
            // fetch from Database
            try {
                CacheWebsiteContentTuple tuple = Util.generateCacheWebsiteContentTupleFromUrlAndDate(website, date);
                content = tuple.getContent();
                web = tuple.getSite();
                date = web.getDate();
                // update cache
                lruCache.put(web, content);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        }
        request.setAttribute("cachedWebsite", content);
        request.setAttribute("date", date);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
