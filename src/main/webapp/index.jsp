<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@page import="net.aequologica.cloud.GitRepositoryState"
%><%@page import="java.util.Properties"
%><%@page import="java.io.IOException"
%><!DOCTYPE html>
<html>
  <head>
    <title>abendrot | index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <h1>Hello, World!</h1>
<%
Properties properties = new Properties();
properties.load(this.getClass().getClassLoader().getResourceAsStream("git.properties"));
%>    <a href="https://github.com/cthiebaud/abendrot/commit/<%=new GitRepositoryState(properties).getCommitId()%>" target="_blank">
      <img style="position: absolute; top: 0; right: 0; border: 0;" src="https://s3.amazonaws.com/github/ribbons/forkme_right_orange_ff7600.png" alt="Fork me on GitHub">
    </a>
  </body>
</html>