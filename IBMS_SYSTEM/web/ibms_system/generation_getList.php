<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$result = "";
$sqlQuery = "SELECT g.*, l_g.FK_LIDER FROM GERACAO g, LIDER_GERACAO l_g WHERE g._ID=l_g.FK_GERACAO AND l_g.DATA_FINAL='0000-00-00' ORDER BY NOME";
$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$user_id = $resultArray["_ID"];
	$user_name = $resultArray["NOME"];
	$user_start_date = $resultArray["DATA_INICIAL"];
	$user_end_date = $resultArray["DATA_FINAL"];
	$user_leaderId = $resultArray["FK_LIDER"];
	$result = $result
		."{"
		."\"ID\":".$user_id.","
		."\"NAME\":"."\"".$user_name."\","
		."\"START_DATE\":"."\"".$user_start_date."\","
		."\"END_DATE\":"."\"".$user_end_date."\","
		."\"LEADER_ID\":".$user_leaderId
		."}";

	$resultArray = mysql_fetch_array($resultSelect);
	if ($resultArray) {
		$result = $result.",";
	}
}
$result."]";

echo "{\"CODE\":0,\"GENERATION_LIST\":[".$result."]}";
exit;
?>
