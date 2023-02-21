<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: query_next_tickle_vids.php
      * Author: Kerry Lyon
      * Created: February 20, 2023

      * PHP REST API. Queries the tickle btn and idle, success, and fail videos.
      * Selects a different entry than the previous one based on id.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
  
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
    WHERE id != :prev_id
    LIMIT 1");
  $stmt->execute();
  $stmt->bindParam("prev_id", $prev_id, PDO::PARAM_STR);
  $result = $stmt->fetchObject();
        
  if (!empty($result)) {
    
    echo json_encode($result);
    
  } else {
    
    $none = array("id" => "", "usr_id" => "", "tickle_btn" => "", "idle" => "", "success" => "", "fail" => "");
    echo json_encode($none);
          
  }
  
  $mariadb_conn = null;

?>