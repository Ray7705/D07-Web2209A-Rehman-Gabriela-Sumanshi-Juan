<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <form action="login" method="post">
            <fieldset>
                <legend>Log In</legend>
                <label>Username<input type="text" placeholder="Enter the user name" name="username"></label>
                <label>Password<input type="password" placeholder="Enter the password" name="password"></label>
                <button type="submit" class="signupbutton">Sign up</button>
                <p style="text-align: center">Not a registered user?<a href="/store/signup">Sign up</a> </p>
            </fieldset>
            
            
        </form>
    </body>
</html>
