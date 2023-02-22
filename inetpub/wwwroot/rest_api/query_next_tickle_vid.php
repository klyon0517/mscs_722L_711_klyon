<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: query_next_tickle_vid.php
      * Author: Kerry Lyon
      * Created: February 20, 2023

      * PHP REST API. Queries the tickle btn and idle, success, and fail videos.
      * Selects a different entry than the previous one based on id.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
    
  $_POST = json_decode(file_get_contents('php://input'), true);
  $prev_id = $_POST['previous_id'];
  
  $stmt = $mariadb_conn->prepare(
    "SELECT
      id,
      usr_id,
      tickle_btn,
      idle,
      success,
      fail
    FROM
      game_info
    WHERE id != $prev_id
    LIMIT 1");
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