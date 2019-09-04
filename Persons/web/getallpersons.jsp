<%-- 
    Document   : getallpersons
    Created on : Aug 21, 2019, 8:29:06 AM
    Author     : gheor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/astiro" prefix="astiro" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display all persons</title>
        <link rel="stylesheet" type="text/css" href="./css/mystyle.css">
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/GetAllPersons" mehtod="POST">
            <sql:setDataSource 
                            var="snapshot" 
                            driver="org.apache.derby.jdbc.ClientDriver"
                            url="jdbc:derby://localhost:1527/persoane;create=true;"
                            user="test"  
                            password="test"/>
            <%-- we'll replace the standard tag from JSTL named sql:setDataSource with our new defined tag 
            <astiro:connectdb dbconnection="snapshot" 
                              dbdriver="org.apache.derby.jdbc.ClientDriver"
                              dburl="jdbc:derby://localhost:1527/persoane;create=true;"
                              dbusername="test"
                              dbpassword="test"/>
            --%>

            <sql:query dataSource="${snapshot}" var="result">
                SELECT PERSOANE.CNP AS CNP,PERSOANE.NAME AS NAME,PERSOANE.SURNAME AS SURNAME,PERSOANE.ADDRESS AS ADDRESS, LOCALITATI.DESCRIPTION AS LOCALITATE, JUDETE.DESCRIPTION AS JUDET
                FROM PERSOANE, LOCALITATI, JUDETE
                WHERE PERSOANE.ID_LOCALITATE=LOCALITATI.ID_LOCALITATE AND LOCALITATI.ID_JUDET=JUDETE.ID_JUDET
            </sql:query>
            <table width="100%" class="tablecenterdwithborder"> 
                <tr class="thc">
                    <th>CNP</th><th>NAME</th><th>SURNAME</th><th>ADDRESS</th><th>CITY</th><th>STATE</th>
                </tr>
                <c:forEach var="row" varStatus="loop" items="${result.rows}">
                <tr>
                    <td><c:out value="${row.cnp}"/></td>
                    <td><c:out value="${row.name}"/></td>
                    <td><c:out value="${row.surname}"/></td>
                    <td><c:out value="${row.address}"/></td>
                    <td><c:out value="${row.localitate}"/></td>
                    <td><c:out value="${row.judet}"/></td>
                </tr>
                </c:forEach>
                <tr>
                    <td></td><td></td>
                    <td class="tdc"><input type="submit" class="ebooksstorebutton" name="report_allpersons_back" value="Back"></td>
                    <td></td><td></td>
                </tr>
            </table>
        </form>
    </body>
</html>
