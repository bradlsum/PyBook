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
**Example get posts JSON message**
```json
{
  "action":"getPosts"
}
```
**Example get users JSON message**
```json
{
  "action":"getUsers"
}
```
**Example add user JSON message**
```json
{
  "action":"addUser",
  "id":0,
  "first_name":"John",
  "last_name":"Doe",
  "email": "email@place.com",
  "username":"user",
  "password":"pass"
}
```
**Example remove user JSON message**
```json
{
  "action":"removeUser",
  "username":"user"
}
```
**Example add post JSON message**
```json
{
  "action":"addPost",
  "username":"user",
  "text":"Test post for PyBook"
}
```
**Example remove post JSON message**
```json
{
  "action":"removePost",
  "username":"user",
  "pid":"3"
}
```
**Example add comment JSON message**
```json
{
  "action":"addComment",
  "pid":"2",
  "username":"user",
  "text": "Test comment for PyBook"
}
```
**Example remove comment JSON message**
```json
{
  "action":"removeComment",
  "cid":"0",
  "pid":"3"
}
```
**Example add like JSON message**
```json
{
  "action":"addLike",
  "pid":"2",
  "username":"user"
}
```
**Example remove like JSON message**
```json
{
  "action":"removeLike",
  "pid":"3",
  "username":"user"
}
```
