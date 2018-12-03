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

    choice = input("Enter 1 for pickled file or 2 for demo input:")
    if choice == '1':
        # Open serialized file
        pkl_in = open('./data.pickle', 'rb')
        server = pickle.load(pkl_in)
        pkl_in.close()
    elif choice == '2':
        server.add_user("user", "John", "Doe", "email@email.com", "PASS")
        server.add_user("Billybob", "Bill", "Bob", "email@email.com", "PASS")

        server.add_post("user", "This is a test")
        server.add_post("user", "This is also a test")

        server.save()

    print(server.user_json())
    print(server.post_json())

    while True:
        if message == "getUsers":
            message = server.user_json()
            # Send message to client
            # TODO
            server.save()

        elif message == "getPosts":
            message = server.post_json()
            # Send message to client
            # TODO
            server.save()

        elif message == "addUser":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            # Add to server
            server.add_user(json_m['first_name'], json_m['last_name'],
                            json_m['email'], json_m['username'], json_m['password'])
            server.save()

        elif message == "removeUser":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            # Remove from server
            server.remove_user(json_m['username'])
            server.save()

        elif message == "addPost":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            # Add post to server
            server.add_post(json_m['username'], json_m['text'])
            server.save()

        elif message == "addComment":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            server.add_comment(json_m['pid'], json_m['username'], json_m['text'])
            server.save()

        elif message == "removeComment":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            server.remove_comment(json_m['pid'], json_m['username'])
            server.save()

        elif message == "addLike":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            server.add_like(json_m['pid'], json_m['username'])
            server.save()

        elif message == "removeLike":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            server.remove_like(json_m['pid'], json_m['username'])
            server.save()

        elif message == "auth":
            # Get next message from client
            # TODO
            json_m = json.load(message)
            check = server.auth(json_m['username'], json_m['password'])
            # Send check back to client
            # TODO
            server.save()

        break

