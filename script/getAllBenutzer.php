<?php

$conn = mysql_connect("localhost", "user1484171", "pyxsMdeg");

if (!$conn) 
{
    echo "Keine Verbindung zur DB: " . mysql_error();
    exit;
}

if (!mysql_select_db("db1484171-magdb")) 
{
    echo "Kann  magdb nicht auswählen: " . mysql_error();
    exit;
}

$sql = "SELECT * FROM benutzer";

$result = mysql_query($sql);

if (!$result) 
{
    echo "Anfrage ($sql) konnte nicht ausgeführt werden : " . mysql_error();
    exit;
}

if (mysql_num_rows($result) == 0) 
{
    echo "Keine Zeilen gefunden, nichts auszugeben, daher Abbruch";
    exit;
}
 
$arrReturn = array();

while ($arrDbResult = mysql_fetch_assoc($result)) {

$arrReturn[][]=$arrDbResult;

}

echo json_encode($arrReturn)."\n";

?> 