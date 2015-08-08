<?php
	require 'conexion.php';
	$q = "delete  from usuarios where id = ${_POST['uid']} LIMIT 1;";
        $res = mysqli_query($con, $q);
	echo "eliminado";
?>

