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

$sqlQuery = "INSERT INTO GERACAO (NOME, DATA_INICIAL) VALUES ('".$in_name."','".$in_start_date."')";
$result = mysql_query($sqlQuery);
if (!$result) {
	echo "{\"CODE\":2}";
	exit;
}

$sqlQuery = "SELECT _ID FROM GERACAO WHERE NOME='".$in_name."'";
$result = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($result);
if (!$resultArray) {
	echo "{\"CODE\":2}";
	exit;
}
$gen_id = $resultArray["_ID"];

$sqlQuery = "INSERT INTO LIDER_GERACAO (FK_LIDER, FK_GERACAO, DATA_INICIAL, DATA_FINAL) VALUES (".$in_leader_id.",".$gen_id.",'".$in_start_date."','0000-00-00')";
$result = mysql_query($sqlQuery);
if (!$result) {
	echo "{\"CODE\":2}";
	exit;
}

$sqlQuery = "UPDATE MEMBRO SET FK_GERACAO=".$gen_id." WHERE _ID=".$in_leader_id;
$result = mysql_query($sqlQuery);
if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}

?>
