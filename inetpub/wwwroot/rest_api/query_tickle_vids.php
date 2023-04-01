<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: query_tickle_vids.php
      * Author: Kerry Lyon
      * Created: February 16, 2023

      * PHP REST API. Queries the tickle btn and idle, success, and fail videos.
      * Maintains coin and streak state as well.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
  
  $queryPackage = new stdClass();
  
  // Select klyon user account
  // for coin and streak tracking
  $stmt = $mariadb_conn->prepare(
    "SELECT
      coin,
      streak
    FROM
      usr_stats
    WHERE usr_name = 'klyon'");
  $stmt->execute();
  $result = $stmt->fetchObject();
  
  if (!empty($result)) {
    
    $user = $result;
    $queryPackage->user = $user;
    
  } else {
    
    $user = array("coin" => "", "streak" => "");
    $queryPackage->user = $user;
          
  }
  
  // Select a random game id
  $stmt = $mariadb_conn->prepare(
    "SELECT
      id
    FROM
      game_info");
  $stmt->execute();
  $result = $stmt->fetchAll();
  
  $idsArray = [];
  
  foreach($result as $row) {
    
    array_push($idsArray,$row['id']);
    
  }
  
  $random_key = array_rand($idsArray, 1);    
  $id = $idsArray[$random_key];
  
  // Select the game based on the id
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
    WHERE id = $id");
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