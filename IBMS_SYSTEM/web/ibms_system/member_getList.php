<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$result = "";

$sqlQuery = "SELECT MEMBRO.* FROM MEMBRO ORDER BY NOME";
$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$user_id = $resultArray["_ID"];
	$user_name = $resultArray["NOME"];
	$user_end_date = $resultArray["DATA_FINAL"];
	$user_is_leader = $resultArray["IS_LIDER"];
	$user_gen_id = $resultArray["FK_GERACAO"];
	$result = $result
		."{"
		."\"ID\":".$user_id.","
		."\"NAME\":"."\"".$user_name."\","
		."\"END_DATE\":"."\"".$user_end_date."\","
		."\"IS_LEADER\":".$user_is_leader.","
		."\"FK_GENERATION\":".$user_gen_id
		."}";

	$resultArray = mysql_fetch_array($resultSelect);
	if ($resultArray) {
		$result = $result.",";
	}
}

echo "{\"CODE\":0,\"MEMBER_LIST\":[".$result."]}";
exit;
?>
