<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_id = ($_POST['ID']);

$result = "{\"CODE\":0, \"MEMBER_LIST\":[";

$sqlQuery = "SELECT * FROM MEMBRO WHERE FK_GERACAO=".$in_id." ORDER BY NOME";

$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$user_id = $resultArray["_ID"];
	$user_name = $resultArray["NOME"];
	$user_is_leader = $resultArray["IS_LIDER"];
	$user_final_date = $resultArray["DATA_FINAL"];
	$user_enter_date = $resultArray["DATA_ENTRADA"];
	$result = $result
			."{"
			."\"ID\":".$user_id.","
			."\"NAME\":"."\"".$user_name."\","
			."\"IS_LEADER\":".$user_is_leader.","
			."\"END_DATE\":"."\"".$user_final_date."\","
			."\"ENTER_DATE\":"."\"".$user_enter_date."\""
			."}";

	$resultArray = mysql_fetch_array($resultSelect);
	if ($resultArray) {
		$result = $result.",";
	}
}
echo $result."]}";
?>
