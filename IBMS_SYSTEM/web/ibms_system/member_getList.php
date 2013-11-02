<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$result = "";

$sqlQuery = "SELECT MEMBRO.*, MEMBRO_LIDER.FK_LIDER FROM MEMBRO, MEMBRO_LIDER WHERE MEMBRO._ID=MEMBRO_LIDER.FK_MEMBRO AND MEMBRO_LIDER.DATA_FINAL='0000-00-00' ORDER BY MEMBRO.NOME";
$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$user_id = $resultArray["_ID"];
	$user_name = $resultArray["NOME"];
	$user_end_date = $resultArray["DATA_FINAL"];
	$user_is_leader = $resultArray["IS_LIDER"];
	$user_gen_id = $resultArray["FK_GERACAO"];
	$user_leader_id = $resultArray["FK_LIDER"];
	$result = $result
		."{"
		."\"ID\":".$user_id.","
		."\"NAME\":"."\"".$user_name."\","
		."\"END_DATE\":"."\"".$user_end_date."\","
		."\"IS_LEADER\":".$user_is_leader.","
		."\"FK_LEADER\":".$user_leader_id.","
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
