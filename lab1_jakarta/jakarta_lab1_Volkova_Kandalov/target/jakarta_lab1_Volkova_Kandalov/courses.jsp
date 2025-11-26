<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.volkova.kandalov.entity.Course" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список курсов</title>
</head>
<body>

<h1>Курсы</h1>

<%
    List<Course> courses = (List<Course>) request.getAttribute("courses");
%>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Описание</th>
        <th>ID студента</th>
        <th>Имя студента</th>
    </tr>

    <%
        if (courses != null) {
            for (Course c : courses) {
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getTitle() %></td>
        <td><%= c.getDescription() %></td>
        <td><%= (c.getStudent() != null ? c.getStudent().getId() : "") %></td>
        <td><%= (c.getStudent() != null ? c.getStudent().getFullName() : "") %></td>
    </tr>
    <%
            }
        }
    %>
</table>

<h2>Добавить курс</h2>
<form method="post" action="courses">
    <input type="text" name="title" placeholder="Название курса" required><br>
    <input type="text" name="description" placeholder="Описание"><br>
    <input type="number" name="studentId" placeholder="ID студента (необязательно)"><br>
    <button type="submit">Добавить курс</button>
</form>

<p>
    <a href="students">← К списку студентов</a>
</p>

</body>
</html>