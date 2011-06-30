<?php

$conn = mysql_connect("http://www8.subdomain.com/phpMyAdmin/", "user1484171", "pyxsMdeg");

if (!$conn) 
{
    echo "Keine Verbindung zur DB: " . mysql_error();
    exit;
}

if (!mysql_select_db("magdb")) 
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

// Solange eine Zeile mit Daten existiert, wird dies in dem assoziativen Array
// $row abgelegt.
// Anmerkung: Wenn Sie nur eine Zeile erwarten, brauchen Sie keine Schleife.
// Anmerkung: Wenn Sie extract($row) innerhalb der folgenden Schleife
//            verwenden, können Sie damit die Variablen
//            $userid, $fullname und $userstatus erzeugen.

while ($row = mysql_fetch_assoc($result)) 
{
    echo $row["id"];
    echo $row["email"];
    echo $row["pass"];
}

mysql_free_result($result);

?> 