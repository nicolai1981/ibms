<?php
include 'util.php';
initDB();

$in_user_id = ($_POST['USER_ID']);
$in_pwd = ($_POST['PWD']);

$sqlQuery = "SELECT * FROM PROFILE WHERE MEMBRO_ID=".$in_user_id." AND SENHA='".$in_pwd."'";
$result = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($result);
if (!$resultArray) {
	// password not match
	echo "{\"CODE\":1}";
	exit;
}
$profile_type = $resultArray["TIPO"];
$profile_token = $resultArray["TOKEN"];

echo "{\"CODE\":0,\"TYPE\":".$profile_type.",\"TOKEN\":'".$profile_token."'}";

?>
