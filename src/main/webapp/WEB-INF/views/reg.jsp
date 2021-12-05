<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="../../styles/login.css" rel="stylesheet" type="text/css" />

    <title>Регистрация</title>
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
            <h3>Регистрация нового пользователя</h3>
        </div>

        <!-- Reg Form -->
        <form name='login' action="<c:url value='/reg'/>" method='POST'>
            <input class="fadeIn second" type='text' name='username' placeholder="Имя пользователя" required>
            <input class="fadeIn second" type="email" name="email" placeholder="Почта" required>
            <input class="fadeIn third" type='password' name='password' placeholder="Пароль" required>
            <input class="fadeIn fourth" name="submit" type="submit" value="Сохранить">
        </form>

        <div id="formFooter">Есть аккаунт? ->
            <a class="underlineHover" href="<c:url value='/login'/>">
                Войти
            </a>
        </div>

    </div>
</div>

</body>
</html>
