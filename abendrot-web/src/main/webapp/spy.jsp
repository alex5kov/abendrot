<%@ page language="java"
%><%@page import="java.util.Properties"
%><%@page import="java.util.Set"
%><%@page import="java.util.TreeSet"
%><!DOCTYPE html>
<html>
  <head>
    <title>abendrot | spy</title>
    
    <%@include file="/WEB-INF/jspf/header.jspf" %>
    
  </head>
  
  <body>
  
    <%@include file="/WEB-INF/jspf/navbar.jspf" %>
    
    <div class="container">
    <div class="span9">
  
    <table id="properties" class="table table-striped table-bordered">
      <caption>java system properties</caption>
      <thead>
        <tr>
          <th>key</th>
          <th>value</th>
        </tr>
      </thead>
      <tbody>
<% Properties properties = System.getProperties();
Set<String> keys = new TreeSet<>(properties.stringPropertyNames()); 
for (String key : keys) { %>
        <tr>
          <td><%=key                          %></td>
          <td><%=properties.getProperty(key) %></td>
        </tr>
<% } %>
      </tbody>
    </table>

    <%@include file="/WEB-INF/jspf/github-ribbon.jspf" %>
    
    </div> <!-- span9 -->
    </div> <!-- container -->

  </body>
  
  <script type="text/javascript">
  $(document).ready(function(){
    $('#properties').dataTable( {} );
  });
  </script>

  <%@include file="/WEB-INF/jspf/footer.jspf" %>
  
</html>
