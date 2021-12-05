<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <title>Пост</title>
</head>
<body>

<div class="container mt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/index?login=true&username=${username}"/>">Главная</a>
            </li>
            <li>
                ${username}
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/login"/>">Выйти</a>
            </li>
        </ul>
    </div>
</div>

<div class="container">
    <div class="card text-center">
        <div class="card-header">
            <h2>${post.name}</h2>
        </div>
        <div class="card-body">
            <h5 class="card-title">${post.description}</h5>
        </div>
        <div class="card-footer text-muted">
            Автор: ${post.author} | Дата создания: <fmt:formatDate value="${post.created}" pattern="dd.MM.yyyy - k:mm"/>
        </div>
    </div>
</div>
<br>
<form action="<c:url value="/saveAnswer?id=${post.id}"/> " method="post">
    <div class="container">
        <div class="row">Ответить:&nbsp;
            <input name='answer' placeholder="Введите свой ответ" required>
            <input name='author' value="${username}" hidden>
            &nbsp;
            <div>
                <button type="submit" class="btn btn-primary">Сохранить ответ</button>
            </div>
        </div>
    </div>
</form>

<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Ответ</th>
            <th scope="col">Автор</th>
            <th scope="col">Дата ответа</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${post.answers}" var="answer">
            <tr>
                <td>
                    <c:out value="${answer.answer}"/>
                </td>
                <td>
                    <c:out value="${answer.author}"/>
                </td>
                <td>
                    <fmt:formatDate value="${answer.created}" pattern="dd.MM.yyyy - k:mm"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
