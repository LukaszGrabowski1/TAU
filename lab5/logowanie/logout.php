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
if(session_destroy())
    echo "Wylogowano pomyślnie";
session_write_close();
?>

