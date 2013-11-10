<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$result = "\"COURSE_LIST\":[";

$sqlQuery = "SELECT c.* FROM TIPO_CURSO t_c, CURSO c WHERE t_c._ID=c.FK_TIPO_CURSO ORDER BY c.DATA_INICIAL DESC, t_c.NOME";
$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$id = $resultArray["_ID"];
	$start_date = $resultArray["DATA_INICIAL"];
	$end_date = $resultArray["DATA_FINAL"];
	$deactivate_date = $resultArray["DATA_DESATIVAR"];
	$total_lessons = $resultArray["TOTAL_AULAS"];
	$version = $resultArray["VERSAO"];
	$course_type_id = $resultArray["FK_TIPO_CURSO"];
	$result = $result
		."{"
		."\"ID\":".$id.","
		."\"COURSE_TYPE_ID\":".$course_type_id.","
		."\"START_DATE\":"."\"".$start_date."\","
		."\"END_DATE\":"."\"".$end_date."\","
		."\"DEACTIVATE_DATE\":"."\"".$deactivate_date."\","
		."\"VERSION\":".$version.","
		."\"TOTAL_LESSONS\":".$total_lessons
		."}";

	$resultArray = mysql_fetch_array($resultSelect);
	if ($resultArray) {
		$result = $result.",";
	}
}

echo "{\"CODE\":0,".$result."]}";
exit;
?>
