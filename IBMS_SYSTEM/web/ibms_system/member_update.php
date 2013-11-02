<?php
include 'util.php';
initDB();

$in_token = ($_POST['token']);
if (!checkToken($in_token)) {
	echo "{\"CODE\":1}";
	exit;
}

$in_id = ($_POST['ID']);
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
$in_end_date = ($_POST['END_DATE']);
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

$sqlQuery = "UPDATE MEMBRO SET "
				."NOME='".$in_name."',"
				."ANIVERSARIO='".$in_birthday."',"
				."SEXO=".$in_gender.","
				."ESTADO_CIVIL=".$in_maritial_status.","
				."TIPO_SANGUINEO=".$in_blood_type.","
				."RG='".$in_rg."',"
				."CPF='".$in_cpf."',"
				."CELULAR='".$in_mobile."',"
				."TELEFONE_CASA='".$in_phone_home."',"
				."TELEFONE_TRABALHO='".$in_phone_work."',"
				."EMAIL='".$in_email."',"
				."ENDERECO='".$in_address."',"
				."COMPLEMENTO='".$in_address_more."',"
				."BAIRRO='".$in_district."',"
				."CIDADE='".$in_city."',"
				."CEP='".$in_zip."',"
				."DATA_INICIAL='".$in_create_date."',"
				."DATA_FINAL='".$in_end_date."',"
				."DATA_ENTRADA='".$in_start_date."',"
				."FK_ENTRADA=".$in_start_type.","
				."IS_LIDER=".$in_is_leader.","
				."FK_GERACAO=".$in_gen_id
			." WHERE _ID=".$in_id;
$result = mysql_query($sqlQuery);
if (!$result) {
	echo "{\"CODE\":2}";
	exit;
}

// Get old leader data from user
$sqlQuery = "SELECT * FROM MEMBRO_LIDER WHERE FK_MEMBRO=".$in_id." AND DATA_FINAL='0000-00-00'";
$result = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($result);
if (!$resultArray) {
	echo "{\"CODE\":3}";
	exit;
}
$in_old_leader_id = $resultArray["FK_LIDER"];
$reg_id = $resultArray["_ID"];
if ($in_leader_id != $in_old_leader_id) {
	$sqlQuery = "UPDATE MEMBRO_LIDER SET "
					."DATA_FINAL='".date('Y-m-d', time())."'"
				." WHERE _ID=".$reg_id;
	$result = mysql_query($sqlQuery);
	if (!$result) {
		echo "{\"CODE\":4}";
		exit;
	}

	$sqlQuery = "INSERT INTO MEMBRO_LIDER ("
					."FK_MEMBRO,"
					."FK_LIDER,"
					."DATA_INICIAL,"
					."DATA_FINAL"
				.") VALUES ("
					.$in_id.","
					.$in_leader_id.",'"
					.date('Y-m-d', time())."',"
					."'0000-00-00')";
	$result = mysql_query($sqlQuery);
	if (!$result) {
		echo "{\"CODE\":5}";
		exit;
	}
}

// Get old level data from user
$sqlQuery = "SELECT * FROM MEMBRO_GRADUACAO WHERE FK_MEMBRO=".$in_id." AND DATA_FINAL='0000-00-00'";
$result = mysql_query($sqlQuery);
$resultArray = mysql_fetch_array($result);
if (!$resultArray) {
	echo "{\"CODE\":6}";
	exit;
}
$in_old_leader_id = $resultArray["FK_LIDER"];
$in_old_level = $resultArray["FK_GRADUACAO"];
$reg_id = $resultArray["_ID"];
if (($in_leader_id != $in_old_leader_id) || ($in_level != $in_old_level)) {
	$sqlQuery = "UPDATE MEMBRO_GRADUACAO SET "
					."DATA_FINAL='".date('Y-m-d', time())."'"
				." WHERE _ID=".$reg_id;
	$result = mysql_query($sqlQuery);
	if (!$result) {
		echo "{\"CODE\":7}";
		exit;
	}

	$sqlQuery = "INSERT INTO MEMBRO_GRADUACAO ("
					."FK_MEMBRO,"
					."FK_LIDER,"
					."FK_GRADUACAO,"
					."DATA_INICIAL,"
					."DATA_FINAL"
				.") VALUES ("
					.$in_id.","
					.$in_leader_id.","
					.$in_level.",'"
					.date('Y-m-d', time())."',"
					."'0000-00-00')";
	$result = mysql_query($sqlQuery);
	if (!$result) {
		echo "{\"CODE\":8}";
		exit;
	}
}

echo "{\"CODE\":0}";
?>
