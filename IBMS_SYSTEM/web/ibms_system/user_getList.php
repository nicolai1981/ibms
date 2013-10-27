<?php
include 'util.php';
initDB();

$result = "";

$sqlQuery = "SELECT MEMBRO.* FROM MEMBRO, PROFILE WHERE PROFILE.MEMBRO_ID=MEMBRO._ID ORDER BY MEMBRO.NOME";
$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$user_id = $resultArray["_ID"];
	$user_name = $resultArray["NOME"];
	$result = $result
		."{"
		."\"ID\":".$user_id.","
		."\"NAME\":"."\"".$user_name."\""
		."}";

	$resultArray = mysql_fetch_array($resultSelect);
	if ($resultArray) {
		$result = $result.",";
	}
}

echo "{\"CODE\":0,\"USER_LIST\":[".$result."]}";
exit;
?>
