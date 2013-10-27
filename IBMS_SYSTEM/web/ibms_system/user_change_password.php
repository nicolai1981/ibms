<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_user_id = ($_POST['USER_ID']);
$in_old_pwd = ($_POST['OLD_PWD']);
$in_new_pwd = ($_POST['NEW_PWD']);

$sqlQuery = "SELECT * FROM PROFILE WHERE MEMBRO_ID=".$in_user_id." AND SENHA='".$in_old_pwd."'";
$result = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($result);
if (!$resultArray) {
	// password not match
	echo "{\"CODE\":1}";
	exit;
}

$sqlQuery = "UPDATE PROFILE SET "
				."SENHA='".$in_new_pwd."'"
				." WHERE MEMBRO_ID=".$in_user_id;
$result = mysql_query($sqlQuery);
if (!$result) {
	echo "{\"CODE\":2}";
	exit;
}

echo "{\"CODE\":0}";
?>
