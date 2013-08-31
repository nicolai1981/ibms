<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_id = ($_POST['ID']);
$in_course_id = ($_POST['COURSE_ID']);
$in_member_id = ($_POST['MEMBER_ID']);
$in_start_date = ($_POST['START_DATE']);
$in_end_date = ($_POST['END_DATE']);
$in_is_teacher = ($_POST['IS_TEACHER']);
$in_completed = ($_POST['COMPLETED']);
$in_total_lessons = ($_POST['TOTAL_LESSONS']);

$sqlQuery = "UPDATE MEMBRO_CURSO SET "
				."DATA_INSCRICAO='".$in_start_date."',"
				."DATA_FINAL='".$in_end_date."',"
				."TOTAL_AULAS=".$in_total_lessons.","
				."COMPLETO=".$in_completed.","
				."IS_TEACHER=".$in_is_teacher.","
				."FK_MEMBRO=".$in_member_id.","
				."FK_CURSO=".$in_course_id
				." WHERE _ID=".$in_id;

$result = mysql_query($sqlQuery);

if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}
?>
