<?php
session_start();

if(isset($_POST["login"]) && isset($_POST["password"]))
    $login = $_POST["login"];
    $pass = $_POST["password"];
    $_POST["login"] = $_POST["password"] = "";
    if($login == "1234" && $pass = "5678"){
        $_SESSION["logged"] = "trueLogged";
        setcookie("logged", "trueLogged");
        $_SESSION["login"] = $login;
        setcookie("login", $login);
        header('Location: logged.php');
        die();
    }
    else{
        echo "<script>setTimeout(function(){window.location = './';}, 2000);</script>";
        echo "<html><head>
        <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
        <link href='css/style.css' rel='stylesheet' type='text/css' />
        <script src='js/jquery-3.2.0.js'></script>
        </head>
        <body>
            Błędne dane logowania
        </body>
        </html>";
        die();
    }


?>