<?php
	if (isset($_GET['uid'])){
		$uid = $_GET['uid'];
		require 'conexion.php';
                $q = "Select * from usuarios where id=$uid limit 1;";
                $res = mysqli_query($con, $q);
		$fila = mysqli_fetch_assoc($res);
		$uname = $fila['nombre'];
		$email = $fila['correo'];
		$phone = $fila['telefono'];
	}else{
		$uid = "";
		$uname = "";
		$email = "";
		$phone = "";

	};
?>
<html>
	<head>
	</head>
	<body>
		Hola!
		Si desea registrarse por favor ponga sus datos aquí. <br />
		<form action="registro.php" method="POST">
		  Nombre de usuario: <input type="text" name="uname" value="<?=$uname?>"><br>
		  Correo: <input type="email" name="email" value="<?=$email?>"><br>
		  Telefono (Opcional): <input type="tel" name="phone" value="<?=$phone?>"><br>
			<input type="hidden" name="uid" value="<?=$uid?>" />
			 <input type="submit" value="Ok" />
		 </form> 
	</body>
</html>
