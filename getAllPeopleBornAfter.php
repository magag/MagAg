<?php
mysql_connect("localhost","testUser","");
mysql_select_db("test");

$q=mysql_query("SELECT * FROM people WHERE birthyear>'".$_REQUEST['year']."'");

while($e=mysql_fetch_assoc($q))
        $output[]=$e;

print(json_encode($output));

mysql_close();
?>
