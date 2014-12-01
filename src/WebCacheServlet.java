import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ldong on 11/30/14.
 */
public class WebCacheServlet extends javax.servlet.http.HttpServlet {
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
        request.setAttribute("cachedWebsite", "HELLOWORLD123213");
//        response.sendRedirect("/index.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
