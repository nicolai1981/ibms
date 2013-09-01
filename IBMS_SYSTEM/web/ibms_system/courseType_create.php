<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_name = ($_POST['NAME']);
$in_start_date = ($_POST['START_DATE']);

$sqlQuery = "INSERT INTO TIPO_CURSO (NOME, DATA_INICIAL) VALUES ('".$in_name."','".$in_start_date."')";
$result = mysql_query($sqlQuery);
if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}
?>
