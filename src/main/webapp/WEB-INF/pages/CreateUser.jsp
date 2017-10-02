<html>
<head>
	<title>Create the user</title>
</head>
<body>
<h1> Create the user </h1>
<form action="/createUser.html" method="post">
<table>
<tr>
    <td>
	Name:
	</td>
	<td>
	<input type="text" name="name"/>
	</td>
</tr>
<tr>
	<td>
	Age:
	</td>
	<td>
	<input type="text" name="age"/>
	</td>
</tr>
<tr>
    <td>
	isAdmin:
	 </td>
	 <td>
	 <input type="checkbox" name="isAdmin"/>
	 </td>
</tr>
</table>
	<input type="submit" value="Add User"/>
    <a href="/users.html">Back</a>
</form>
</body>
</html>