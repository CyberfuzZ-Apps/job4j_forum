<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="css/login.css" rel="stylesheet" type="text/css" />

    <title>Login</title>
</head>
<body>

<div class="wrapper fadeInDown">
    <div id="formContent">

        <c:if test="${not empty errorMessage}">
            <div style="color:red; font-weight: bold; margin: 30px 0px;">
                    ${errorMessage}
            </div>
        </c:if>

        <div class="fadeIn first">
            <h1>Вход</h1>
        </div>

        <!-- Login Form -->
        <form name='login' action="<c:url value='/login'/>" method="post">
            <input class="fadeIn second" type='email' name='username' id="email" placeholder="Введите вашу почту"
                   required>
            <input class="fadeIn third" type='password' name='password' id="password" placeholder="Введите ваш пароль"
                   required>
            <input class="fadeIn fourth" name="submit" type="submit" value="Войти">
        </form>

        <div id="formFooter">
            <a class="underlineHover" href="<c:url value='/reg'/>">
                Регистрация нового пользователя
            </a>
        </div>

    </div>
</div>

</body>
</html>
