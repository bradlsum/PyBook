# Server for PyBook
## Server actions
> * getPosts
> * getUsers
> * addUser
> * removeUser
> * addPost
> * removePost
> * addComment
> * removeComment
> * addLike
> * removeLike
> * auth
> * exit
## Making a request to the server can be done by setting the action inside of the JSON passed to the server
**Example add user JSON message**
```json
{
  "action":"addUser",
  "id": 0,
  "_ID": 1,
  "first_name": "John",
  "last_name": "Doe",
  "email": "email@place.com",
  "username": "user",
  "password": "pass"
}
```
