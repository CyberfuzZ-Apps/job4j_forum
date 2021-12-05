<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <title>Тема</title>
</head>
<body>

<c:if test="${not empty post}">
    <c:set value="${post.author}" var="username"/>
    <c:set value="${post.id}" var="post_id"/>
    <h1>Редактировать тему</h1>
</c:if>
<c:if test="${empty post}">
    <h1>Новая тема</h1>
</c:if>

<div class="row">
    <a class="nav-link" href="<c:url value="/index?login=true&username=${username}"/>">Главная</a>
    &nbsp;&nbsp;
    ${username}

    <a class="nav-link" href="<c:url value="/login"/>">Выйти</a>
</div>
<br>
<br>
<form action="<c:url value='/save'/>" method='POST'>
    <table style="font-size: larger">
        <tr>
            <td>Тема:</td>
            <td>
                <input size="50" type='text' name='name' value="${post.name}"
                       placeholder="Введите название темы" required>
            </td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td>
                <textarea rows="5" cols="50" name='description'
                          placeholder="Введите подробное описание"
                          required><c:if test="${not empty post}"><c:out value="${post.description}"/></c:if></textarea>

            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="author" value="${username}" hidden>
                <c:if test="${not empty post}">
                    <input type="text" name="id" value="${post.id}" hidden>
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan='2'><input class="btn btn-success" name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>

</body>
</html>
