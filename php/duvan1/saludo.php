<html>
<head>
</head>
<body>
	<h1>Saludos, <?php echo $_POST['fname'];?>!</h1>
	Hoy es <?php
		$date = new DateTime();
		echo $date->format('Y-m-d H:i:s');
		$str = "noteur";
		$ent = 4;
		$flot = 4.3;
		echo "tengo $ent perros";
		?>
	<br/>
</body>
</html>
