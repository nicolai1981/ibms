<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_course_id = ($_POST['COURSE_ID']);
$in_teacher_id_list = ($_POST['TEACHER_ID_LIST']);

$sqlQuery = "DELETE FROM MEMBRO_CURSO WHERE FK_CURSO=".$in_course_id." AND IS_TEACHER=1" ;
$result = mysql_query($sqlQuery);

if (strlen($in_teacher_id_list) != 0) {
	$teacher_list = split(",", $in_teacher_id_list);
	foreach($teacher_list as $teacher_id) {
		$sqlQuery = "INSERT INTO MEMBRO_CURSO (IS_TEACHER, DATA_INSCRICAO, COMPLETO, FK_MEMBRO, FK_CURSO) VALUES (1, '0000-00-00', 0, ".$teacher_id.",".$in_course_id.")";
		$result = mysql_query($sqlQuery);
	}	
}

if ($result) {
	echo "{\"CODE\":0}";
} else {
	echo "{\"CODE\":2}";
}
?>
