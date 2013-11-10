<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$result = "\"COURSE_TYPE_LIST\":[";
$sqlQuery = "SELECT TIPO_CURSO.* FROM TIPO_CURSO ORDER BY NOME";
$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$course_id = $resultArray["_ID"];
	$course_name = $resultArray["NOME"];
	$course_start_date = $resultArray["DATA_INICIAL"];
	$course_end_date = $resultArray["DATA_FINAL"];
	$result = $result
		."{"
		."\"ID\":".$course_id.","
		."\"NAME\":"."\"".$course_name."\","
		."\"START_DATE\":"."\"".$course_start_date."\","
		."\"END_DATE\":"."\"".$course_end_date."\""
		."}";
	$resultArray = mysql_fetch_array($resultSelect);
	if ($resultArray) {
		$result = $result.",";
	}
}

echo "{\"CODE\":0,".$result."]}";
exit;
?>