<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: query_test.php
      * Author: Kerry Lyon
      * Created: February 9, 2023

      * Test file for the REST API.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
  
  // $user = $_SESSION["samAccountName"];
  
  $usersInfoPackage = new stdClass();
  
  $stmt = $mariadb_conn->prepare(
    "SELECT
      id, usr_name, streak, streak_date
    FROM
      usr_stats");
  $stmt->execute();
  $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        
  if (!empty($result)) {
    
    echo json_encode($result);
    
    
  } else {
    
    $none = array("name" => "empty");
    echo json_encode($none);
          
  }
  
  echo json_encode($usersInfoPackage);
      
  $mariadb_conn = null;

?>