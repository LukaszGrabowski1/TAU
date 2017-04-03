<?php
session_start();
if(isset($_SESSION["logged"]) && $_SESSION["logged"] = "trueLogged"){
    echo "Witaj,jesteś zalogowany. Zalogowany jako ".$_SESSION["login"].". <a href='logout.php'>Wyloguj się</a>";
}
else{
    header('Location: ./');
    die();
}
?>
