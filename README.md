# Spring 2023 Enterprise Mobile Development
This is my mobile dev repository.

## Project 1: Tickle Ball (Native Android Game)

Social media game where the player interacts with 3 green circles (tickle points) overlaying a short video of a person standing idle. The tickle point overlay is always in the same location: 1 circle near the head, 1 near the armpit, and 1 near the feet. (This also acts as a positioning template for content creators to get creative with. Ex. What kind of a funny and creative position could a person stand in relation to the tickle circles.) The player guesses where the person in the video is ticklish by clicking one of the three tickle points. If they get it correct, they get the "tickle ending"; a new video of the person laughing. If the player guesses wrong, it goes to a video of the person acting angry or sad.

If the player won, they receive 3 credits. If they lost, there is an option to spend 1 credit to unlock the tickle ending. After the player's guess, they can click to move onto the next game video. And on and on...

High score lists the top 10 tickle streaks (correct guesses in a row).

External Files for MariaDB / PHP REST API:
* Software used: IIS, PHP 8.0, MariaDB 10.6
* tickle_ball_mariadb.sql (database dump ready for import)
* PHP DB conn file
  * includes/mariadb/mariadb_connection.php
* PHP REST API files
  * inetpub/wwwroot/rest_api/query_hiscore.php
  * inetpub/wwwroot/rest_api/query_next_tickle_vid.php
  * inetpub/wwwroot/rest_api/query_tickle_vids.php
* Media located in
  * inetpub/wwwroot/rest_api/files/

Potential enhancements (time allowing):
* Coins
* Nice logo
* Sound effects
* Particle effects
* Animations
* Ability for content creators to upload the 3 short 5 second videos (idle, tickle ending, wrong ending)
* Bookmark favorite creators / videos
* Comments
* View your library of videos
  * Likes, winners, view count, comments
