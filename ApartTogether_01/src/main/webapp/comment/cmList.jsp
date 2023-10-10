<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSONArray에 대한 내용을 출력합니다. --%>
<%
	JSONArray jsArr = (JSONArray)request.getAttribute("jsArr");	
%>
<%=jsArr.toString()%>