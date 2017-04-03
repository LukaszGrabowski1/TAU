<?php
session_start();

if(isset($_SESSION["logged"]) && $_SESSION["logged"] = "trueLogged"){
    header('Location: logged.php');
    die();
}
else

echo "<html><head>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
    <link href='css/style.css' rel='stylesheet' type='text/css' />
    <script src='js/jquery-3.2.0.js'></script>
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