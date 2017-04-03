<?php
session_start();

if(isset($_SESSION["logged"]) && $_SESSION["logged"] = "trueLogged"){
    header('Location: logged.php');
    die();
}
else

echo "<html><head>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
    </head>
    <body>
    <form name='form-logowanie' action='login.php' method='post'>
          Login: <input type='text' name='login'><br>
          Hasło: <input type='password' name='password'>
          <input type='submit' name='zaloguj' value='Zaloguj'>
      </form>
      </body>
    </html>";

?>