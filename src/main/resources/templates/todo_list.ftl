<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
</head>

<body>
    <h1> Ur todo </h1>
    <#list todos as todo>
        <li> ${todo.description} : ${todo.hasCompleted }</li>
</body>

</html>