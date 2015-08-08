<?php
//1) Conseguir los datos
if(isset( $_POST['uid']) && $_POST['uid'] && $_POST['uid'] != "0" ) {
	$uid = $_POST['uid'];

}else{
	$uid = "NULL";
}

$uname = $_POST['uname'];
$email = $_POST['email'];

if(isset($_POST['phone'])){
	$tel= $_POST['phone'];
}else{
    $tel= 0;
}

//2) hacer algo
require 'conexion.php';
//if ($uid)
//	$qstr = "update usuarios set nombre = $uname ..  where id = $uid";
//}else{
//	$qstr = "insert into usuarios(nombre, correo, telefono) values('$uname', '$email', $tel);";
//}

$qstr = "REPLACE into usuarios(id, nombre, correo, telefono) values ($uid, '$uname', '$email', $tel);";

echo "Este es el comando que se va a ejecutar <br/>";
echo $qstr."<br/>";
$resultado = mysqli_query($con, $qstr);
$id = mysqli_insert_id($con);
$fila = mysqli_fetch_assoc($resultado);
//3) devolver los resultados
print_r($fila);
echo "<br/> el id es $id";
?>
<a href="listado.php">listado</a>
