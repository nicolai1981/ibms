<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_id = ($_POST['ID']);

$result = "{\"CODE\":0, \"TEACHER_LIST\":[";

$sqlQuery = "SELECT m.NOME, m_c.* FROM MEMBRO m, MEMBRO_CURSO m_c WHERE m_c.FK_CURSO=".$in_id." AND m_c.IS_TEACHER=1 AND m_c.FK_MEMBRO=m._ID ORDER BY m.NOME";

$resultSelect = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($resultSelect);
while ($resultArray) {
	$name = $resultArray["NOME"];
	$member_id = $resultArray["FK_MEMBRO"];
	$result = $result
			."{"
			."\"MEMBER_ID\":".$member_id.","
			."\"NAME\":"."\"".$name."\""
			."}";

	$resultArray = mysql_fetch_array($resultSelect);
	if ($resultArray) {
		$result = $result.",";
	}
}
echo $result."]}";
?>
