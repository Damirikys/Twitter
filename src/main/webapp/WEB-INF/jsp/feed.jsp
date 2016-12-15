<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <link rel="stylesheet" type="text/css" href="/twitter.css">
<body id="feed">

<div class="usertitle">${username}</div>
<div class="postcount">Количество записей: ${size}</div>

<div id="feedblock">
<div class="feedtitle">

<c:if test="${myFeed}">
    <form action='addpost' method='post'>
        <input type='text' name='userId' value='${userId}' style='display:none'></input>
        <input type='text' name='text' placeholder="Что у вас нового?..."></input><button style='display:none'>Send</button>
    </form>
</c:if>

</div>

<ul class='messages'>
    ${feed}
</ul>
</div>

<div class="postcount">
<br>
<a href="/users">К странице с пользователями</a>
</div>
</body>
</html>