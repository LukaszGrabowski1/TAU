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
        echo "Błędne dane logowania";
        die();
    }


?>