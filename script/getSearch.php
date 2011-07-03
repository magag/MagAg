<?php
$conn = mysql_connect("localhost", "user1484171", "pyxsMdeg");
if (!$conn) { echo "Keine Verbindung zur DB: " . mysql_error(); exit; }
if (!mysql_select_db("db1484171-magdb")) { echo "Kann  magdb nicht auswählen: " . mysql_error(); exit; }
$sql="";
if ($_REQUEST['veranstaltung'] == '*')
{
	$sql="SELECT * FROM mitschriften";
}
else
{
	$sql="SELECT * FROM mitschriften WHERE Veranstaltung ='".$_REQUEST['veranstaltung']."'";
}
$result = mysql_query($sql);
if (!$result) { echo "0"; exit; }

if (mysql_num_rows($result) == 0) { echo "null"; exit; }

$arrReturn = array();

while ($arrDbResult = mysql_fetch_assoc($result)) {

$arrReturn[][]=$arrDbResult;

}

echo json_encode($arrReturn)."\n";
?>
