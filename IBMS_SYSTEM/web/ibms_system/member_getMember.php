<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_id = ($_POST['ID']);

$sqlQuery = "SELECT * FROM MEMBRO WHERE _ID=".$in_id;
$resultSelect = mysql_query($sqlQuery);
if (!$resultSelect) {
	echo "{\"ERROR_CODE\":2}";
	exit;
}

$resultArray = mysql_fetch_array($resultSelect);
$name = $resultArray['NOME'];
$birthday = $resultArray['ANIVERSARIO'];
$gender = $resultArray['SEXO'];
$maritial_status = $resultArray['ESTADO_CIVIL'];
$blood_type = $resultArray['TIPO_SANGUINEO'];
$rg = $resultArray['RG'];
$cpf = $resultArray['CPF'];
$mobile = $resultArray['CELULAR'];
$phone_home = $resultArray['TELEFONE_CASA'];
$phone_work = $resultArray['TELEFONE_TRABALHO'];
$email = $resultArray['EMAIL'];
$address = $resultArray['ENDERECO'];
$address_more = $resultArray['COMPLEMENTO'];
$district = $resultArray['BAIRRO'];
$city = $resultArray['CIDADE'];
$zip = $resultArray['CEP'];
$create_date = $resultArray['DATA_INICIAL'];
$start_date = $resultArray['DATA_ENTRADA'];
$end_date = $resultArray['DATA_FINAL'];
$start_type = $resultArray['FK_ENTRADA'];
$is_leader = $resultArray['IS_LIDER'];
$generation_id = $resultArray['FK_GERACAO'];

$sqlQuery = "SELECT * FROM MEMBRO_LIDER WHERE FK_MEMBRO=".$in_id." AND DATA_FINAL='0000-00-00'";
$resultSelect = mysql_query($sqlQuery);
if (!$resultSelect) {
	echo "{\"ERROR_CODE\":4}";
	exit;
}
$resultArray = mysql_fetch_array($resultSelect);
$leader_id = $resultArray['FK_LIDER'];

$sqlQuery = "SELECT * FROM MEMBRO_GRADUACAO WHERE FK_MEMBRO=".$in_id." AND DATA_FINAL='0000-00-00'";
$resultSelect = mysql_query($sqlQuery);
if (!$resultSelect) {
	echo "{\"ERROR_CODE\":5}";
	exit;
}
$resultArray = mysql_fetch_array($resultSelect);
$level = $resultArray['FK_GRADUACAO'];

$result = "{"
	."\"CODE\":0,"
	."\"ID\":\"".$in_id."\","
	."\"NAME\":\"".$name."\","
	."\"BIRTHDAY\":\"".$birthday."\","
	."\"GENDER\":\"".$gender."\","
	."\"MARITIAL_STATUS\":\"".$maritial_status."\","
	."\"BLOOD_TYPE\":\"".$blood_type."\","
	."\"RG\":\"".$rg."\","
	."\"CPF\":\"".$cpf."\","
	."\"MOBILE\":\"".$mobile."\","
	."\"PHONE_HOME\":\"".$phone_home."\","
	."\"PHONE_WORK\":\"".$phone_work."\","
	."\"EMAIL\":\"".$email."\","
	."\"ADDRESS\":\"".$address."\","
	."\"ADDRESS_MORE\":\"".$address_more."\","
	."\"DISTRICT\":\"".$district."\","
	."\"CITY\":\"".$city."\","
	."\"ZIP\":\"".$zip."\","
	."\"CREATE_DATE\":\"".$create_date."\","
	."\"START_DATE\":\"".$start_date."\","
	."\"START_TYPE\":\"".$start_type."\","
	."\"END_DATE\":\"".$end_date."\","
	."\"IS_LEADER\":\"".$is_leader."\","
	."\"FK_GENERATION\":\"".$generation_id."\","
	."\"FK_LEVEL\":\"".$level."\","
	."\"LEADER_ID\":\"".$leader_id."\""
	."}";
echo $result;
?>
