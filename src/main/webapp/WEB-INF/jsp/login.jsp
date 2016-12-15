<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>ТВИТОР: Авторизация</title>
<link rel="stylesheet" type="text/css" href="/twitter.css">
</head>
<body>

    <div id="login">
       <h1>Авторизация</h1>
          <c:if test="${param.error != null}">
              <div class="result">
                  Ошибка аутентификации
                  <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                    Причина: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                  </c:if>
              </div>
          </c:if>
          <c:if test="${param.logout != null}">
              <div class="result">
                  Вы успешно вышли из системы.
              </div>
          </c:if>

          <div class="login_form">
               <form name='f' action="login" method='POST'>
                  <table>
                     <tr>
                        <td><input type='text' name='username' value='' placeholder='Логин'></td>
                     </tr>
                     <tr>
                        <td><input type='password' name='password' placeholder='Пароль' /></td>
                     </tr>
                     <tr>
                        <td><input name="submit" type="submit" value="войти" /></td>
                     </tr>
                  </table>
              </form>
          </div>
    </div>

    <div class="reglink">
        <a href="/reg">Зарегистрироваться</a>
    </div>

</body>
</html>