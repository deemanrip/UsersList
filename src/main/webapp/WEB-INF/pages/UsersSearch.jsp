<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Users List</title>
	<style type="text/css">
    		th {
    			background: #31B404;
    		}
    	</style>
</head>
<body>
<table border="1" cellpadding="1" width="40%" height="100px">
	<tr>
		<th>Number</th>
		<th>Name</th>
		<th>Age</th>
		<th>isAdmin</th>
		<th>Created Date</th>
	</tr>
	<c:forEach items="${foundUsers}" var="user">
		<tr>
			<td>${user.id}</td>
			<td>${user.name}</td>
			<td>${user.age}</td>
			<td>${user.admin}</td>
			<td>${user.createdDate}</td>
			<td>
            </form>
            <form action="/deleteUser/${user.id}">
            <input type="submit" value="Delete"/>
            </form>
            </td>
            <td>
            </form>
            <form action="/editUser/${user.id}">
            <input type="submit" value="Edit"/>
            </form>
            </td>
		</tr>
	</c:forEach>
</table>
<a href="/users.html">Back</a>
<br/>
</body>
</html>