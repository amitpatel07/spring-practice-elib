<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLibrary</title>
</head>
<body>${books }


	<div>
		<form action="addBook" method="POST">
			<table>
				<thead>
					<tr>
						<th>Book name</th>
						<th>Author</th>
						<th>Year</th>
						<th>ISBN</th>
						<th>Image path</th>
						<th>Storage Path</th>
					</tr>
				</thead>
				<tr>
					<td><input type="text" name="name" /></td>
					<td><input type="text" name="author" /></td>
					<td><input type="text" name="year" /></td>
					<td><input type="text" name="isbn" /></td>
					<td><input type="text" name="imagepath" /></td>
					<td><input type="text" name="storagepath" /></td>
					<td><input type="submit" value="Add Book" /></td>
				</tr>


			</table>


		</form>

	</div>
</body>
</html>