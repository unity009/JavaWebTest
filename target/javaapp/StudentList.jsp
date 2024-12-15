<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Students List</title>
</head>
<body>
    <h1>Student List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <%
                java.sql.ResultSet rs = (java.sql.ResultSet) request.getAttribute("students");
                while (rs.next()) {
                    int id = rs.getInt("STUDENTID");
                    String name = rs.getString("FIRSTNAME");
            %>
            <tr>
                <td><%= id %></td>
                <td><%= name %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
