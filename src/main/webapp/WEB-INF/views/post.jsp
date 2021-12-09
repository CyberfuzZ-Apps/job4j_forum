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
                <a class="nav-link" href="<c:url value="/index"/>">Главная</a>
            </li>
            <li>
                ${username}
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/logout"/>">Выйти</a>
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
<form action="<c:url value="/saveAnswer?post_id=${post.id}"/> " method="post">
    <div class="container">
        <div class="row">Ответить:&nbsp;
            <input name="answerName" placeholder="Введите свой ответ" required>
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
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${post.answers}" var="answer">
            <tr>
                <td>
                    <c:out value="${answer.answerName}"/>
                </td>
                <td>
                    <c:out value="${answer.author}"/>
                </td>
                <td>
                    <fmt:formatDate value="${answer.created}" pattern="dd.MM.yyyy - k:mm"/>
                </td>
                <td>
                    <a id="delete" href="<c:url value="/deleteAnswer?answer_id=${answer.id}&post_id=${post.id}"/>">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                            <path fill-rule="evenodd"
                                  d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                        </svg>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
