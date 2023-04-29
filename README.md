# Spring 2023 Enterprise Mobile Development
This is my mobile dev repository.

## Project 3

ARCore project.
* Watched the project videos
* Android Studio & Emulator are up-to-date
* cloned the sceneform-android-sdk
* Successfully emulated GLTF sample AR app
* Successfully emulated SceneView Sceneform Maintained sample AR apps
* Posted instructions in the forums
* Created my own project 3 app
  * https://codemaker2016.medium.com/augmented-reality-technology-continues-to-change-the-way-we-interact-with-the-environment-that-66a0a039d385
  * Added a model I grabbed from Sketchfab (since the Poly 3D Models is defunct).
  * Resized the tank model .glb in Blender since it was huge (matched it to the tiger model.glb)
  * Placed multiple tank models in my emulator and took a screenshot

Tabletop AR D&D
  * Place D&D miniatures on the map on the table in the emulated environment.
  * Choose and place multiple types of miniatures.
  * Reposition miniature.
  * D20 dice roll with audio (ImageView not AR)

To Do:
  * Remove miniatures from the table
  * ARCore Cloud Anchors


## Project 1 and 2: Tickle Ball (Native Android Game)

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
  * inetpub/wwwroot/rest_api/query_upload_vids.php
  * inetpub/wwwroot/rest_api/update_coin_streak.php
* Media located in
  * inetpub/wwwroot/rest_api/files/

Project 2 additions:
* Time limit on initial (idle) video
  * must guess tickle spot in 5 secs or autofail
* Connect to Android device from my PC
* Create an upload page
* Access camera
* Access app storage
* base64 encode and upload videos (all 3: idle, success, fail)
* Create player account and vids via REST API
* Track coins
  * start with 3
  * earn 3 on a successful tickle
  * lose 1 on an autofail (no guess within 5 secs)
  * pay 1 to see the success video (from the fail screen)
* Track streak
  * streak continues from where player left off

Potential enhancements (time allowing):
* User login
* Nice logo
* Sound effects
* Particle effects
* Animations
* Bookmark favorite creators / videos
* Comments
* View your library of videos
  * Likes, winners, view count, comments
