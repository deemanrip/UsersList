<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Edit the user</title>
</head>
<body>
<h1> Edit the user </h1>
<form action="/editUser/${user.id}" method="post">
<table>
    <tr>
    <td>
		Name:
    </td>
    <td>
		 <input type="text" value="${user.name}" name="name"/>
    </td>
    </tr>
    <tr>
    <td>
		Age:
	</td>
	<td>
	<input type="number" value="${user.age}" name="age"/>
	</td>
	</tr>
	<c:choose>
      <c:when test="${user.admin == true}">
        <tr>
        <td>
		isAdmin:
		</td>
		<td>
		<input type="checkbox" name="isAdmin" checked/>
		</td>
		</tr>
	</c:when>
      <c:otherwise>
      <tr>
      <td>
		isAdmin: <input type="checkbox" name="isAdmin"/>
		</td>
		</tr>
      </c:otherwise>
    </c:choose>
    </table>
	<input type="submit" value="Save"/>
	<a href="/users.html">Back</a>
</form>
</body>
</html>