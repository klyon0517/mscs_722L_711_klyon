<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: update_coin_streak.php
      * Author: Kerry Lyon
      * Created: February 20, 2023

      * PHP REST API. Updates the user stats with coin amount
      * and streak.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
  
  $queryPackage = new stdClass();
  
  $_POST = json_decode(file_get_contents('php://input'), true);
  $coin = $_POST['coin'];
  $streak = $_POST['streak'];
  
  // Save the coin and streak state
  $stmt = $mariadb_conn->prepare(
    "UPDATE
      usr_stats
    SET
      coin = :coin,
      streak = :streak
    WHERE usr_name = 'klyon'");
  $stmt->bindParam("coin", $coin, PDO::PARAM_STR);
  $stmt->bindParam("streak", $streak, PDO::PARAM_STR);
  $stmt->execute();
    
  $response = array("response" => "success");
  echo json_encode($response);
  
  $mariadb_conn = null;

?>