<?php
session_start();
if(isset($_SESSION["logged"]) && $_SESSION["logged"] = "trueLogged"){
    echo "<html><head>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
    <link href='css/style.css' rel='stylesheet' type='text/css' />
    <script src='js/jquery-3.2.0.js'></script>
    </head>
    <body>
        Witaj,jesteś zalogowany. Zalogowany jako ".$_SESSION["login"].". <a href='logout.php'>Wyloguj się</a>
    </body>
    </html>";
}
else{
    header('Location: ./');
    die();
}
?>
