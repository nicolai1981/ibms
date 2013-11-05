<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_id = ($_POST['ID']);
$in_name = ($_POST['NAME']);
$in_leader_id = ($_POST['LEADER_ID']);
$in_start_date = ($_POST['START_DATE']);
$in_end_date = ($_POST['END_DATE']);

$sqlQuery = "UPDATE GERACAO SET "
				."NOME='".$in_name."',"
				."DATA_INICIAL='".$in_start_date."',"
				."DATA_FINAL='".$in_end_date."'"
				." WHERE _ID=".$in_id;
$result = mysql_query($sqlQuery);
if (!$result) {
	echo "{\"CODE\":2}";
	exit;
}

// Get old generation data
$sqlQuery = "SELECT * FROM LIDER_GERACAO WHERE FK_GERACAO=".$in_id." AND DATA_FINAL='0000-00-00'";
$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
$lg_id = $resultArray["_ID"];
$lg_leader_id = $resultArray["FK_LIDER"];

if ($in_leader_id != $lg_leader_id) {
	$sqlQuery = "UPDATE LIDER_GERACAO SET DATA_FINAL='".date('Y-m-d')."' WHERE _ID=".$lg_id;
	$result = mysql_query($sqlQuery);
	if (!$result) {
		echo "{\"CODE\":3}";
		exit;
	}

	$sqlQuery = "INSERT INTO LIDER_GERACAO (FK_LIDER, FK_GERACAO, DATA_INICIAL, DATA_FINAL) VALUES (".$in_leader_id.",".$in_id.",'".date('Y-m-d')."','0000-00-00')";
	$result = mysql_query($sqlQuery);
	if (!$result) {
		echo "{\"CODE\":4}";
		exit;
	}

	$sqlQuery = "UPDATE MEMBRO SET FK_GERACAO=".$in_id." WHERE _ID=".$in_leader_id;
	$result = mysql_query($sqlQuery);
	if (!$result) {
		echo "{\"CODE\":5}";
		exit;
	}
}

echo "{\"CODE\":0}";
?>
