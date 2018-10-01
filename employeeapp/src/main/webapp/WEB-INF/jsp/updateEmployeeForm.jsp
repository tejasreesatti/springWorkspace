<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Form</title>
</head>
<body bgcolor="cyan">
	<h1>Update Employee</h1><hr>
	<form:form action="/employeeapp/updateEmployee" method="post" modelAttribute="employee">
	<form:label path="employeeId">Employee ID:</form:label>
	<form:input path="employeeId" readonly="readonly"/><br>
	<form:label path="employeeName">Employee Name</form:label>
	<form:input path="employeeName"/><br>
	<form:label path="employeeDepartment">Employee Department:</form:label>
	<form:input path="employeeDepartment"/><br>
	<form:label path="employeeSalary">Employee Salary:</form:label>
	<form:input path="employeeSalary"/><br>
	
	<input type = "submit" value="update Employee"/>
	
	</form:form>
</body>
</html>