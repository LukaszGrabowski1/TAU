<?php
session_start();
if(isset($_SESSION["logged"]) && $_SESSION["logged"] = "trueLogged"){
    echo "<script src='js/jquery-3.2.0.js'></script>";
    echo "Witaj,jesteś zalogowany. Zalogowany jako ".$_SESSION["login"].". <a href='logout.php'>Wyloguj się</a>";
}
else{
    header('Location: ./');
    die();
}
?>
