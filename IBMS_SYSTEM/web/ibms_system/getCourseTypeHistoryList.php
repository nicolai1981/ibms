<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_id = ($_POST['ID']);

$result = "{\"CODE\":0, \"HISTORY_LIST\":[";

$sqlQuery = "SELECT * FROM CURSO WHERE FK_TIPO_CURSO=".$in_id." ORDER BY DATA_INICIAL DESC";

$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$id = $resultArray["_ID"];
	$start_date = $resultArray["DATA_INICIAL"];
	$end_date = $resultArray["DATA_FINAL"];
	$courseTypeId = $resultArray["FK_TIPO_CURSO"];
	$result = $result
			."{"
			."\"ID\":".$id.","
			."\"START_DATE\":"."\"".$start_date."\","
			."\"END_DATE\":"."\"".$end_date."\","
			."\"COURSE_TYPE_ID\":".$courseTypeId
			."}";

	$resultArray = mysql_fetch_array($resultSelect);
	if ($resultArray) {
		$result = $result.",";
	}
}
echo $result."]}";
?>
