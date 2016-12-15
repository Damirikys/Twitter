<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>ТВИТОР: Регистрация</title>
<link rel="stylesheet" type="text/css" href="/twitter.css">
</head>
<body>

    <div id="login">
       <h1>Регистрация</h1>
          <div class="login_form">
               <form name='f' action="reg" method='POST'>
                  <table>
                     <tr>
                        <td><input type='text' name='username' value='' placeholder='Логин'></td>
                     </tr>
                     <tr>
                        <td><input type='password' name='password' placeholder='Пароль' /></td>
                     </tr>
                     <tr>
                        <td><input name="submit" type="submit" value="зарегистрироваться" /></td>
                     </tr>
                  </table>
              </form>
          </div>
    </div>

    <div class="reglink">
        <a href="/login">авторизоваться</a>
    </div>

</body>
</html>

