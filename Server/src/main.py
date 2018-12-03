# Sumner Bradley
import pickle
import socket
import json
from src.server import Server

HOST = '127.0.0.1'
PORT = 65432


def action(sever, message):
    # Convert message to JSON
    if message != "":
        json_m = json.loads(message)
    else:
        print("Message not found")
        return

    if json_m['action'] == "getUsers":
        message = server.user_json()
        # TODO Send message to client
        server.save()
        print("Sending user JSON")

    elif json_m['action'] == "getPosts":
        message = server.post_json()
        # TODO Send message to client
        server.save()
        print("Sending post JSON")

    elif json_m['action'] == "addUser":
        # Add to server
        server.add_user(json_m['first_name'], json_m['last_name'],
                        json_m['email'], json_m['username'], json_m['password'])
        server.save()
        print("Adding user", json_m['username'])

    elif json_m['action'] == "removeUser":
        # Remove from server
        server.remove_user(json_m['username'])
        server.save()

    elif json_m['action'] == "addPost":
        # Add post to server
        server.add_post(json_m['username'], json_m['text'])
        server.save()

    elif json_m['action'] == "addComment":
        server.add_comment(json_m['pid'], json_m['username'], json_m['text'])
        server.save()

    elif json_m['action'] == "removeComment":
        server.remove_comment(json_m['pid'], json_m['username'])
        server.save()

    elif json_m['action'] == "addLike":
        server.add_like(json_m['pid'], json_m['username'])
        server.save()

    elif json_m['action'] == "removeLike":
        server.remove_like(json_m['pid'], json_m['username'])
        server.save()

    elif json_m['action'] == "auth":
        check = server.auth(json_m['username'], json_m['password'])
        # TODO Send check back to client
        server.save()

    elif json_m['action'] == "exit":
        return


if __name__ == '__main__':
    server = Server()
    message = '{"action":"addUser","id": 0,"_ID": 1,"first_name": "John",'
    message += '"last_name": "Doe","email": "email@place.com","username": "user","password": "pass"}'

    # Select pickle or sample
    choice = input("Enter 1 for pickled file or 2 for demo input:")

    if choice == '1':   # Open serialized file
        pkl_in = open('./data.pickle', 'rb')
        server = pickle.load(pkl_in)
        pkl_in.close()

    elif choice == '2':  # Sample users and posts
        server.add_user("user", "John", "Doe", "email@email.com", "PASS")
        server.add_user("Billybob", "Bill", "Bob", "email@email.com", "PASS")
        server.add_user("Guy", "Jane", "Smith", "email@email.com", "PASS")

        server.add_post("user", "This is a test")
        server.add_post("Guy", "This is also a test")

        server.add_comment(0, "Guy", "Really cool!")
        server.add_comment(0, "Billybob", "wow!")

        server.add_like(1, "Guy")
        server.add_like(1, "user")

        print(server.user_json())
        print(server.post_json())

        server.save()

    while True:
        # TODO authentication
        # TODO if user signs in make a new thread and start action
        action(server, message)

        break
