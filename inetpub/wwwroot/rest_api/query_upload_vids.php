<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: query_upload_vids.php
      * Author: Kerry Lyon
      * Created: March 16, 2023

      * PHP REST API. Sends the username, videos, and tickle spot.
      * Once payload is received, the database is checked for the username.
      * If it does not exist, a new user is created.
      * Otherwise, the existing id is queried.
      * Using the id, a new 'game_info' entry is created.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
  
  $_POST = json_decode(file_get_contents('php://input'), true);
  $usr_name = $_POST['usr_name'];
  $tickle_btn = $_POST['tickle_btn'];
  
  $stmt = $mariadb_conn->prepare(
    "SELECT
      id
    FROM
      usr_stats
    WHERE usr_name = :usr_name");
  $stmt->bindParam("usr_name", $usr_name, PDO::PARAM_STR);
  $stmt->execute();
  $result = $stmt->fetchObject();
        
  if (!empty($result)) {
    
    $usr_id = $result->id;
    
  } else {
    
    $stmt = $mariadb_conn->prepare(
      "INSERT INTO
        usr_stats
      (usr_name, coin)
      VALUES
      (:usr_name, 3)");
    $stmt->bindParam("usr_name", $usr_name, PDO::PARAM_STR);
    $stmt->execute();
    
    $usr_id = $mariadb_conn->lastInsertId();
          
  }
  
  // Insert game info    
  $stmt = $mariadb_conn->prepare(
    "INSERT INTO
      game_info
    (usr_id, tickle_btn)
    VALUES
    (:usr_id, :tickle_btn)");
  $stmt->bindParam("usr_id", $usr_id, PDO::PARAM_STR);
  $stmt->bindParam("tickle_btn", $tickle_btn, PDO::PARAM_STR);
  $stmt->execute();
  
  echo json_encode(array("message" => "Successful Upload"));
  
  $mariadb_conn = null;

?>