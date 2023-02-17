<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: query_tickle_vids.php
      * Author: Kerry Lyon
      * Created: February 16, 2023

      * PHP REST API. Queries the tickle btn and idle, success, and fail videos.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
  
  $stmt = $mariadb_conn->prepare(
    "SELECT
      id,
      usr_id,
      tickle_btn,
      idle,
      success,
      fail
    FROM
      game_info");
  $stmt->execute();
  $result = $stmt->fetchObject();
        
  if (!empty($result)) {
    
    echo json_encode($result);
    
  } else {
    
    $none = array("id" => "", "usr_id" => "", "tickle_btn" => "", "idle" => "", "success" => "", "fail" => "");
    echo json_encode($none);
          
  }
  
  $mariadb_conn = null;

?>