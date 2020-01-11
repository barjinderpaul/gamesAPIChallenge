# gamesAPIChallenge
 1. Open Sutherland Hackerearth folder in IntelliJ and Import changes in the pom.xmlfile.
 2.  Go to Run->GamesapiApplication
 3.  Open a browser window and navigate to http://localhost:8080. It will open the 'Swagger documentation' through which you can see all
    the end points available.
     - The endpoints are available for :
         - /games
         - /users
         - /authenticate (to get the jwt token)
 4. **Unauthenticated end points :**
    - POST /upload (to upload the .csv file)
    - GET /games
    - GET /games/{id}
    - POST /users (to create a new user.)
    - POST /authenticate (to generate a new JWT token)
    - GET / 
    - GET /swagger-ui.html#
 5. **Authenticated end points (Authenticated end points need an Authorization header with value = Bearer ${JwtToken} ):** 
    - POST /games
 
    - PUT /games/{id}

    - PATCH /games/{id}

    - DELETE /games/{id}


 6. **After running the project from IntelliJ, open Postman**
 7. Pictures :
    - POST : /users 
    ![enter image description here][6]
    - POST :/authenticate (to get the JWT token)
    ![enter image description here][7]
    - GET : /upload
    ![enter image description here][8]
    - GET : /games
    ![enter image description here][9]
    -  GET :/games/{id}
![enter image description here][10]
    -  GET :/games?title='game title'
![enter image description here][11]
    -  POST :/games
   ![enter image description here][1]
    -  PUT :/games/{id}
    ![enter image description here][2]
    -  PATCH :/games/{id}
    ![enter image description here][3]
    -  DELETE :/games/{id}
![enter image description here][4]
    - GET /users    
![enter image description here][5]

    - **For any other reference,  refer swagger documentation on http://localhost:8080**


  [1]: https://he-s3.s3.amazonaws.com/media/uploads/1a42c4b.png
  [2]: https://he-s3.s3.amazonaws.com/media/uploads/a437c39.png
  [3]: https://he-s3.s3.amazonaws.com/media/uploads/4af292e.png
  [4]: https://he-s3.s3.amazonaws.com/media/uploads/6ba548e.png
  [5]: https://he-s3.s3.amazonaws.com/media/uploads/8bb4481.png
  [6]: https://he-s3.s3.amazonaws.com/media/uploads/c6fd2ad.png
  [7]: https://he-s3.s3.amazonaws.com/media/uploads/3d27255.png
  [8]: https://he-s3.s3.amazonaws.com/media/uploads/978f25d.png
  [9]: https://he-s3.s3.amazonaws.com/media/uploads/ba3c13e.png
  [10]: https://he-s3.s3.amazonaws.com/media/uploads/65d33a5.png
  [11]: https://he-s3.s3.amazonaws.com/media/uploads/d081827.png
