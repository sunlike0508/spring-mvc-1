<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- action이 절대경로가 아닌 save이다. /jsp/members/new-form.jsp에서 호출했다면 /jsp/members/save로 실행되는 것-->
<form action="save" method="post">
    username: <input type="text" name="username"/>
    age: <input type="text" name="age"/>
    <button type="submit">전송</button>
</form>
</body>