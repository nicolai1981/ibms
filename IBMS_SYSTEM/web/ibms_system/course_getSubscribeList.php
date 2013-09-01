<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_id = ($_POST['ID']);

$result = "{\"CODE\":0, \"SUBSCRIBE_LIST\":[";

$sqlQuery = "SELECT m.NOME, m_c.* FROM MEMBRO m, MEMBRO_CURSO m_c WHERE m_c.FK_CURSO=".$in_id." AND m_c.FK_MEMBRO=m._ID ORDER BY m.NOME";

$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$id = $resultArray["_ID"];
	$course_id = $resultArray["FK_CURSO"];
	$member_id = $resultArray["FK_MEMBRO"];
	$name = $resultArray["NOME"];
	$is_teacher = $resultArray["IS_TEACHER"];
	$complete = $resultArray["COMPLETO"];
	$start_date = $resultArray["DATA_INSCRICAO"];
	$end_date = $resultArray["DATA_FINAL"];
	$total_lessons = $resultArray["TOTAL_AULAS"];

	$result = $result
			."{"
			."\"ID\":".$id.","
			."\"COURSE_ID\":".$course_id.","
			."\"MEMBER_ID\":".$member_id.","
			."\"NAME\":"."\"".$name."\","
			."\"IS_TEACHER\":".$is_teacher.","
			."\"COMPLETED\":".$complete.","
			."\"START_DATE\":"."\"".$start_date."\","
			."\"END_DATE\":"."\"".$end_date."\","
			."\"TOTAL_LESSONS\":".$total_lessons
			."}";

	$resultArray = mysql_fetch_array($resultSelect);
	if ($resultArray) {
		$result = $result.",";
	}
}
echo $result."]}";
?>
