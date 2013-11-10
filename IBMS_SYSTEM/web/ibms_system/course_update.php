<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_id = ($_POST['ID']);
$in_course_type_id = ($_POST['COURSE_TYPE_ID']);
$in_start_date = ($_POST['START_DATE']);
$in_end_date = ($_POST['END_DATE']);
$in_deactivate_date = ($_POST['DEACTIVATE_DATE']);
$in_total_lessons = ($_POST['TOTAL_LESSONS']);

$sqlQuery = "UPDATE CURSO SET "
				."FK_TIPO_CURSO='".$in_course_type_id."',"
				."DATA_INICIAL='".$in_start_date."',"
				."DATA_FINAL='".$in_end_date."',"
				."DATA_DESATIVAR='".$in_deactivate_date."',"
				."TOTAL_AULAS=".$in_total_lessons
				." WHERE _ID=".$in_id;
$result = mysql_query($sqlQuery);
if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}
?>
