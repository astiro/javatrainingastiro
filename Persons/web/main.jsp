<%-- 
    Document   : main
    Created on : Sep 2, 2019, 12:10:49 PM
    Author     : gheor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personas main page</title>
        <link rel="stylesheet" type="text/css" href="css/mystyle.css"
    </head>
    <body>
        <c:choose>
            <c:when test="${validUser == true}">
                <%@include file="jspf/menu.jspf" %>
            </c:when> 
            <c:otherwise>
                <c:redirect url="./index.jsp">
                </c:redirect>
            </c:otherwise>
        </c:choose>
    </body>
</html>
