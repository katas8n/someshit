<!DOCTYPE html>
<html>
<head>
<title>Todo List</title>
</head>
<body>
<h1>Your Todo List</h1>
<ul>
<#list todos as todo>
<li>${todo.description} - ${todo.hasCompleted ? "Completed" : "Pending"}</li>
</#list>
</ul>
</body>
</html>
