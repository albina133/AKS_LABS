<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.volkova.kandalov.entity.Student" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список студентов</title>
</head>
<body>
<h1>Студенты</h1>

<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>

<table border="1">
    <tr>
        <th>ID</th>
        <th>ФИО</th>
        <th>Email</th>
        <th>Возраст</th>
    </tr>

    <%
        if (students != null) {
            for (Student s : students) {
    %>
    <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getFullName() %></td>
        <td><%= s.getEmail() %></td>
        <td><%= s.getAge() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

<h2>Добавить студента</h2>
<form method="post" action="students">
    <input type="text" name="fullName" placeholder="ФИО" required><br>
    <input type="email" name="email" placeholder="Email"><br>
    <input type="number" name="age" placeholder="Возраст"><br>
    <button type="submit">Добавить</button>
</form>

</body>
</html>