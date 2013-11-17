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

$sqlQuery = "SELECT * FROM CURSO WHERE FK_TIPO_CURSO=".$in_course_type_id." AND DATA_DESATIVAR='0000-00-00' ORDER BY VERSAO DESC";
$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
if ($resultArray) {
	$version = $resultArray["VERSAO"];
} else {
	$version = 0;
}
$version = $version + 1;

$sqlQuery = "INSERT INTO CURSO (FK_TIPO_CURSO, DATA_INICIAL, DATA_FINAL, TOTAL_AULAS, VERSAO) VALUES (".$in_course_type_id.",'".$in_start_date."','".$in_end_date."',".$in_total_lessons.",".$version.")";
$result = mysql_query($sqlQuery);
if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}
?>
