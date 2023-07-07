<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <form action="signup" method="post">
        <fieldset>
        <legend>Sign Up</legend>
        <label>First Name<input type="text" placeholder="Enter your first name" name="FirstName"></label>
        <label>Last Name<input type="text" placeholder="Enter your last name" name="LastName"></label>
        <label>Username<input type="text" placeholder="Enter user name" name="Username"></label>
        <label>Password<input type="password" placeholder="Enter password" name="Password"></label>
        <label>Confirm Password<input type="password" placeholder="Re-enter password" name="ConfirmPassword"></label>
        <label>Email Id<input type="text" placeholder="Enter your email id" name="EmailId"></label><br>
        <button type="submit" class="signupbutton">Sign up</button>
        <p style="text-align: center">Already have an account?<a href="/Store/login"> Log in</a> </p>
        </fieldset>
        </form>   
    </body>
</html>
