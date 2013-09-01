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
				."DATA_FINAL='".$in_end_date."',"
				."FK_LEADER_ID=".$in_leader_id
				." WHERE _ID=".$in_id;
$result = mysql_query($sqlQuery);
if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}
?>
