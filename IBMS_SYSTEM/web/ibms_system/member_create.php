<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_name = ($_POST['NAME']);
$in_birthday = ($_POST['BIRTHDAY']);
$in_gender = ($_POST['GENDER']);
$in_maritial_status = ($_POST['MARITIAL_STATUS']);
$in_blood_type = ($_POST['BLOOD_TYPE']);
$in_rg = ($_POST['RG']);
$in_cpf = ($_POST['CPF']);
$in_mobile = ($_POST['MOBILE']);
$in_phone_home = ($_POST['PHONE_HOME']);
$in_phone_work = ($_POST['PHONE_WORK']);
$in_email = ($_POST['EMAIL']);
$in_address = ($_POST['ADDRESS']);
$in_address_more = ($_POST['ADDRESS_MORE']);
$in_district = ($_POST['DISTRICT']);
$in_city = ($_POST['CITY']);
$in_zip = ($_POST['ZIP']);
$in_create_date = ($_POST['CREATE_DATE']);
$in_start_date = ($_POST['START_DATE']);
$in_start_type = ($_POST['START_TYPE']);
$in_is_leader = ($_POST['IS_LEADER']);
$in_leader_id = ($_POST['LEADER_ID']);
$in_level = ($_POST['FK_LEVEL']);

$sqlQuery = "SELECT * FROM MEMBRO WHERE _ID=".$in_leader_id;
$result = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($result);
if ($resultArray) {
	$in_gen_id = $resultArray["FK_GERACAO"];
} else {
	$in_gen_id = 0;
}

$sqlQuery = "INSERT INTO MEMBRO ("
				."NOME,"
				."ANIVERSARIO,"
				."SEXO,"
				."ESTADO_CIVIL,"
				."TIPO_SANGUINEO,"
				."RG,"
				."CPF,"
				."CELULAR,"
				."TELEFONE_CASA,"
				."TELEFONE_TRABALHO,"
				."EMAIL,"
				."ENDERECO,"
				."COMPLEMENTO,"
				."BAIRRO,"
				."CIDADE,"
				."CEP,"
				."DATA_INICIAL,"
				."DATA_FINAL,"
				."DATA_ENTRADA,"
				."FK_ENTRADA,"
				."IS_LIDER,"
				."FK_GERACAO"
			.") VALUES ("
				."'".$in_name."',"
				."'".$in_birthday."',"
				.$in_gender.","
				.$in_maritial_status.","
				.$in_blood_type.","
				."'".$in_rg."',"
				."'".$in_cpf."',"
				."'".$in_mobile."',"
				."'".$in_phone_home."',"
				."'".$in_phone_work."',"
				."'".$in_email."',"
				."'".$in_address."',"
				."'".$in_address_more."',"
				."'".$in_district."',"
				."'".$in_city."',"
				."'".$in_zip."',"
				."'".$in_create_date."',"
				."'0000-00-00',"
				."'".$in_start_date."',"
				.$in_start_type.","
				.$in_is_leader.","
				.$in_gen_id
			.")";
$result = mysql_query($sqlQuery);
if (!$result) {
	echo "{\"CODE\":2}";
	exit;
}

$sqlQuery = "SELECT _ID FROM MEMBRO WHERE NOME='".$in_name."' AND DATA_INICIAL='".$in_create_date."'" ;
$result = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($result);
if (!$resultArray) {
	echo "{\"CODE\":3}";
	exit;
}
$user_id = $resultArray["_ID"];

$sqlQuery = "INSERT INTO MEMBRO_LIDER (FK_MEMBRO, FK_LIDER, DATA_INICIAL, DATA_FINAL) VALUES (".$user_id.",".$in_leader_id.",'".$in_create_date."','0000-00-00')";
$result = mysql_query($sqlQuery);
if (!$result) {
	echo "{\"CODE\":4}";
	exit;
}

$sqlQuery = "INSERT INTO MEMBRO_GRADUACAO (FK_MEMBRO, FK_LIDER, FK_GRADUACAO, DATA_INICIAL, DATA_FINAL) VALUES (".$user_id.",".$in_leader_id.",".$in_level.",'".$in_create_date."','0000-00-00')";
$result = mysql_query($sqlQuery);
if (!$result) {
	echo "{\"CODE\":5}";
	exit;
}
echo "{\"CODE\":0}";
?>
