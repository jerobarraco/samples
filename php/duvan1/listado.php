<html>
<head>
 <meta charset="utf-8"> 
</head>
<body>
<h1>Estos son todos los usuarios</h1>
<table>
	<tr>
		<td>Id</td>
		<td>Nombre</td>
		<td>Correo</td>
		<td>Telefono</td>
		<td>Modificar</td>
		<td>Eliminar</td>

	</tr>
	<?php
		require 'conexion.php';
		$q = "Select * from usuarios;";
		$res = mysqli_query($con, $q);
		while ($fila=mysqli_fetch_assoc($res)) {
			echo "<tr>";
			echo "<td>${fila['id']}</td>";
			echo "<td>${fila['nombre']}</td>";
			echo "<td>${fila['correo']}</td>";
			echo "<td>${fila['telefono']}</td>";
			echo "<td><a href='index.php?uid=${fila['id']}'>✎</a></td>";
		 	?>
			<form action ="eliminar.php" method="POST">
				<input type='hidden' value='<?=$fila['id']?>' name='uid' />
				<td> <input type='submit' value='☹' /></td>
			</form>
			</tr>;
			<?php
		}
	?>
</table>
</body></html>
