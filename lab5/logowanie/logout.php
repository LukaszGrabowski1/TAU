<?php
echo "<script>
setTimeout(function(){
  window.location = './';
}, 2000);
</script>";
session_start();
setcookie("logged", "", time() - 3600);
setcookie("login", "", time() - 3600);
session_unset();
if(session_destroy()){
  echo "<html><head>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
    <link href='css/style.css' rel='stylesheet' type='text/css' />
    <script src='js/jquery-3.2.0.js'></script>
    </head>
    <body>
      Wylogowano pomyślnie
      </body>
    </html>";
}
session_write_close();
?>

