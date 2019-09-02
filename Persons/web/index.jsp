<%-- 
    Document   : index
    Created on : Aug 21, 2019, 5:53:08 AM
    Author     : gheor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PERSONAS LOGIN</title>
        <link rel="stylesheet" type="text/css" href=".\\css\\mystyle.css">
    </head>
    <body>
        <form action="./Login" method="POST">
            <table class="tablecenterdwithborder">
                <tr>
                    <td>User name:</td><td><input name="personas_login_username" type="text"></td>
                </tr>
                <tr>
                    <td>Password:</td><td><input name="personas_login_password" type="password"></td>
                </tr>
                <tr>
                    <td></td><td><input name="personas_login_login" type="submit" value="Login"/></td></td>
                </tr>
            </table>
        </form>    
    </body>
</html>
