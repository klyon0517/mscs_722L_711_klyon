<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: query_hiscore.php
      * Author: Kerry Lyon
      * Created: February 9, 2023

      * PHP REST API. Queries the top 10 highest streaks.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
  
  $stmt = $mariadb_conn->prepare(
    "SELECT
      id, usr_name, streak, 
      DATE_FORMAT(streak_date, '%m-%d-%Y') AS streak_date_formatted
    FROM
      usr_stats
    ORDER BY streak DESC
    LIMIT 10");
  $stmt->execute();
  $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        
  if (!empty($result)) {
    
    echo json_encode($result);
    
  } else {
    
    $none = array("id" => "", "usr_name" => "", "streak" => "", "streak_date_formatted" => "");
    echo json_encode($none);
          
  }
  
  $mariadb_conn = null;

?>