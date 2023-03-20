<?php

  /*  Tickle Ball

      * Software: Native Android mobile game.
      * Filename: query_base64_test.php
      * Author: Kerry Lyon
      * Created: March 20, 2023

      * Test file for converting base64 encoding video files.

  */
  
  
  
  date_default_timezone_set("America/New_York");

  include 'mariadb/mariadb_connection.php';
    
  $stmt = $mariadb_conn->prepare(
    "SELECT
      fail, fail_video
    FROM
      game_info
    WHERE
      id = 30");
  $stmt->execute();
  $result = $stmt->fetchObject();
        
  if (!empty($result)) { 

    $nam = $result->fail;
    $vid = $result->fail_video;
    
    $vid = base64_decode($vid, true);
    
    file_put_contents("files/" . $nam, $vid);
    
    echo "complete";
    
  } else {
    
    echo "empty";
          
  }
        
  $mariadb_conn = null;

?>