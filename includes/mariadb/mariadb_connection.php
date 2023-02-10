<?php

  /*  Timesheet

      * Software: CSA app for managing consulting hours.
      * Filename: mariadb_connection.php
      * Author: Kerry Lyon
      * Created: December 22, 2021

      * This file contains the connection information to the CSA MariaDB database.

  */



  $mariadb_servername = "localhost";
	$mariadb_username = "root";
	$mariadb_password = "";
	$mariadb_dbname= "tickle_ball";

  $mariadb_conn = new PDO("mysql:host=$mariadb_servername;dbname=$mariadb_dbname", $mariadb_username, $mariadb_password);
    
  // Set the PDO error mode to exception  
  $mariadb_conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

?>