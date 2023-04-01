<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: query_next_tickle_vid.php
      * Author: Kerry Lyon
      * Created: February 20, 2023

      * PHP REST API. Queries the tickle btn and idle, success, and fail videos.
      * Selects a different entry than the previous one based on id.
      * Maintains coin and streak state as well.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
  
  $queryPackage = new stdClass();
  
  $_POST = json_decode(file_get_contents('php://input'), true);
  $prev_id = $_POST['previous_id'];
  $coin = $_POST['coin'];
  $streak = $_POST['streak'];
  
  $user = array("coin" => $coin, "streak" => $streak);
  $queryPackage->user = $user;
  
  // Select the next game
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
    ORDER BY RAND();
    LIMIT 1");
  $stmt->execute();
  $result = $stmt->fetchObject();
        
  if (!empty($result)) {
    
    $vid = $result;
    $queryPackage->video = $vid;
    
  } else {
    
    $vid = array("id" => "", "usr_id" => "", "tickle_btn" => "", "idle" => "", "success" => "", "fail" => "");
    $queryPackage->video = $vid;
          
  }
  
  echo json_encode($queryPackage);
  
  $mariadb_conn = null;

?>