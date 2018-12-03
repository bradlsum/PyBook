# Sumner Bradley
import pickle
import socket
import json
from src.server import Server

HOST = '127.0.0.1'
PORT = 65432

if __name__ == '__main__':
    server = Server()
    message = ""

    server.add_user("user", "John", "Doe", "email@email.com", "PASS")
    server.add_user("Billybob", "Bill", "Bob", "email@email.com", "PASS")

    server.add_post("user", "This is a test")
    server.add_post("user", "This is also a test")

    print(server.user_json())
    print(server.post_json())

    while True:
        if message == "getUsers":
            message = server.user_json()
            # Send message to client
            # TODO

        elif message == "getPosts":
            message = server.post_json()
            # Send message to client
            # TODO

        elif message == "addUser":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            # Add to server
            server.add_user(json_m['first_name'], json_m['last_name'],
                            json_m['email'], json_m['username'], json_m['password'])

        elif message == "removeUser":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            # Remove from server
            server.remove_user(json_m['username'])

        elif message == "addPost":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            # Add post to server
            server.add_post(json_m['username'], json_m['text'])

        elif message == "addComment":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            server.add_comment(json_m['pid'], json_m['username'], json_m['text'])

        elif message == "removeComment":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            server.remove_comment(json_m['pid'], json_m['username'])

        elif message == "addLike":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            server.add_like(json_m['pid'], json_m['username'])

        elif message == "removeLike":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            server.remove_like(json_m['pid'], json_m['username'])

        elif message == "auth":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            check = server.auth(json_m['username'], json_m['password'])
            # Send check back to client
            # TODO
