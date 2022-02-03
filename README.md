# spring_rest_news_service
This is Test task in Clevertec. Task is to write RESTful web-service implementing functionality for working with
news management system.

Main entities: news and comments.
Implemented API: 
- CRUD for news    
- CRUD for comments
- sorting of news and comments in different parameters 
- getting list of all news and all comments of specific news with pagination.
            
Methods are linked to addresses according to REST approaches:

    GET /news   get all news
    POST /news   post title and text to add news; returns created entity
    PUT /news   post id, title and text to update entity with this id
    GET /news/{newsId}   get news with id = newsId
    DELETE /news/{newsId}   delete news and comments of this news with id = newsId
    
    GET /news/{newsId}/comments   get all comments of news
    POST /news/{newsId}/comments   post text and username to add comment; returns created entity
    PUT /news/{newsId}/comments   post id, text and username to update comment with this id
    GET /news/{newsId}/comments/{commentId}   get comment with this id
    DELETE /news/{newsId}/comments/{commentsId}   delete comment with this id
    
To run the project you should configure database setting in application.yml (set right user, password and url), and before this set up database PostgreSQL
    
Project is building by Gradle 

Implemented Exception Handling while client sends wrong data.

Connecting to different databases is based on Spring Profiles. In memory H2 and outer PostgreSQL

In test mode, tables are created and filled automatically. In production mode tables are created automatically without filling

Service level covered by tests.

Implemented aspect-style logging of called controller-level methods 

Setting moved to application.yml

Code is documented by JavaDoc.

Task description is following:
[embed]Task.pdf[/embed]