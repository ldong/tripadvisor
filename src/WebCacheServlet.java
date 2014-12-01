import com.firebase.client.Firebase;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by ldong on 11/30/14.
 */
public class WebCacheServlet extends javax.servlet.http.HttpServlet {
    private static LRU lruCache;
    private static Firebase firebase;
    static {
        lruCache = new LRU();
        firebase = new Firebase("https://tripadvisorminiproj.firebaseio.com");
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {    String color= request.getParameter("color");
        String website= request.getParameter("website");
        String date= request.getParameter("date");
        PrintWriter out = response.getWriter();
//        out.println(
//                "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
//                        "<html> \n" +
//                        "<head> \n" +
//                        "<title> My first jsp  </title> \n" +
//                        "</head> \n" +
//                        "<body> \n" +
//                        "Hello World" +
//                        "<p>" + website + "</p>" +
//                        "<p>" + date + "</p>" +
//                        "</body> \n" +
//                        "</html>"
//        );
        //page submission at your server
//        HttpSession session = request.getSession();

        //set your session variable here
        CacheWebsite web = new CacheWebsite(website, date);
        String content;
        if(lruCache.get(web) != null){
            content = (String)lruCache.get(web);
        } else { // Not Found locally
            content = "";
            Date d;
            for (int i = 0; i < Util.DAYS; i++) {
                d = Util.getDateXDaysBeforeDateY(i, web.getDate());
                web = new CacheWebsite(website, d);
                if(lruCache.get(web) != null) {
                    content = (String) lruCache.get(web);
                    break;
                } else {
                    // fetch from Database
                    firebase.getRoot();
                    // if found
                    // update cache
                    lruCache.put(web, d);
                    break;
                }
            }
            // content to be the root of
            content = firebase.getRoot();
        }
        request.setAttribute("cachedWebsite", content);
//        response.sendRedirect("/index.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
