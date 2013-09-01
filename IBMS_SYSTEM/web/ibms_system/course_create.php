<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_course_type_id = ($_POST['COURSE_TYPE_ID']);
$in_start_date = ($_POST['START_DATE']);
$in_end_date = ($_POST['END_DATE']);
$in_total_lessons = ($_POST['TOTAL_LESSONS']);

$sqlQuery = "INSERT INTO CURSO (FK_TIPO_CURSO, DATA_INICIAL, DATA_FINAL, TOTAL_AULAS) VALUES (".$in_course_type_id.",'".$in_start_date."','".$in_end_date."',".$in_total_lessons.")";
$result = mysql_query($sqlQuery);
if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}
?>
