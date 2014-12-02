<%--
  Created by IntelliJ IDEA.
  User: ldong
  Date: 11/29/14
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>TripAdvisor</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <script>
    $(function() {
      $( "#datepicker" ).datepicker();
    });
  </script>
</head>
<body>

<% // java code
  String content, date;
  if(request.getAttribute("cachedWebsite") != null){
    content= request.getAttribute("cachedWebsite").toString();
    date = request.getAttribute("date").toString();
  } else {
    content = "Hello, please enter the URL and date you want to lookup";
    date = "";
  }
%>

  <form action="cache">
    <p>
      Website <input type="text" name="website" id="website">
      Date: <input type="text" name="date" id="datepicker">
      <input type="submit" value="submit">
    </p>
  </form>
  <p>Web Content</p>
  <div id="webcontent">
    <p> Cached Date: <%=date%> </p>
    <p> Content:  <%=content%> </p>
  </div>

</body>
</html>