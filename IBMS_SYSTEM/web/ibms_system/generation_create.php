<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_name = ($_POST['NAME']);
$in_leader_id = ($_POST['LEADER_ID']);
$in_start_date = ($_POST['START_DATE']);

$sqlQuery = "INSERT INTO GERACAO (NOME, DATA_INICIAL, FK_LEADER_ID) VALUES ('".$in_name."','".$in_start_date."',".$in_leader_id.")";
$result = mysql_query($sqlQuery);
if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}
?>
