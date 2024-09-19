<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
</head>
<body>

<h1>Login Page</h1>

<form action="/login" method="post">
    <fieldset>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required />
    </fieldset>

    <fieldset>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required />
    </fieldset>

    <fieldset>
        <input value="Login" type="submit" />
    </fieldset>

    <#if hasError??>
        <p style="color: red;">Invalid email or password.</p>
    </#if>
</form>

</body>
</html>
