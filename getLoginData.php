<?php
$conn = mysql_connect("localhost", "user1484171", "pyxsMdeg");
if (!$conn) { echo "Keine Verbindung zur DB: " . mysql_error(); exit; }
if (!mysql_select_db("db1484171-magdb")) { echo "Kann  magdb nicht auswählen: " . mysql_error(); exit; }

$sql="SELECT COUNT(*) as exist FROM benutzer WHERE pass ='helloworld' AND email='mgerber@smail.uni-koeln.de'";

$result = mysql_query($sql);
if (!$result) { echo "0"; exit; }
if (mysql_num_rows($result) == 0) { echo "null"; exit; }


while ($row = mysql_fetch_assoc($result)) 
{
    echo $row["exist"];
}

?>
