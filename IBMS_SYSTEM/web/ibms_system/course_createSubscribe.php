<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_course_id = ($_POST['COURSE_ID']);
$in_member_id = ($_POST['MEMBER_ID']);
$in_start_date = ($_POST['START_DATE']);

$sqlQuery = "INSERT INTO MEMBRO_CURSO (IS_TEACHER, DATA_INSCRICAO, COMPLETO, FK_MEMBRO, FK_CURSO) VALUES (0, '".$in_start_date."', 0, ".$in_member_id.",".$in_course_id.")";
$result = mysql_query($sqlQuery);

if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}
?>
