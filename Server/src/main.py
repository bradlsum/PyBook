# Authors:
#   Sumner Bradley
#   Danny Cahoon
import pickle
import socket
import selectors
import json
import types
from src.server import Server

HOST = '127.0.0.1'
PORT = 65432


def action(sock, server, message):
    # Convert message to JSON
    print("message = ", message)
    if message != "":
        json_m = json.loads(message)
    else:
        print("Message not found")
        return

    if json_m['action'] == "getUsers":
        resp = server.user_json().encode("utf-8")
        sock.send(resp)
        server.save()
        print("Sending user JSON")

    elif json_m['action'] == "getPosts":
        resp = server.post_json().encode("utf-8")
        sock.send(resp)
        server.save()
        print("Sending post JSON")

    elif json_m['action'] == "addUser":
        # Add to server
        print(json_m['first_name'], json_m['last_name'],
                        json_m['email'], json_m['username'], json_m['password'])
        server.add_user(json_m['first_name'], json_m['last_name'],
                        json_m['email'], json_m['username'], json_m['password'])
        server.save()
        print("Adding user", json_m['username'])

    elif json_m['action'] == "removeUser":
        # Remove from server
        server.remove_user(json_m['username'])
        server.save()
        print("Removing user", json_m['username'])

    elif json_m['action'] == "addPost":
        # Add post to server
        server.add_post(json_m['username'], json_m['text'])
        print("Posts: ", server.post_json())
        server.save()
        print("Adding post", json_m['text'])

    elif json_m['action'] == "removePost":
        server.remove_post(json_m["pid"])
        server.save()
        print("Removing post", json_m['pid'])

    elif json_m['action'] == "addComment":
        server.add_comment(json_m['pid'], json_m['username'], json_m['text'])
        server.save()
        print("Adding comment", json_m['text'])

    elif json_m['action'] == "removeComment":
        server.remove_comment(json_m['pid'], json_m['cid'])
        server.save()
        print("Removing post", json_m['pid'])

    elif json_m['action'] == "addLike":
        server.add_like(json_m['pid'], json_m['username'])
        server.save()

    elif json_m['action'] == "removeLike":
        server.remove_like(json_m['pid'], json_m['username'])
        server.save()

    elif json_m['action'] == "auth":
        user_auth = server.auth(json_m['username'], json_m['password'])
        sock.send(user_auth.encode("UTF-8"))
        server.save()

    elif json_m['action'] == "exit":
        return


def accept_wrapper(sock):
    conn, addr = sock.accept()  # Should be ready to read
    print('accepted connection from', addr)
    conn.setblocking(False)
    data = types.SimpleNamespace(addr=addr, inb=b'', outb=b'')
    events = selectors.EVENT_READ | selectors.EVENT_WRITE
    sel.register(conn, events, data=data)


def service_connection(key, mask, server):
    sock = key.fileobj
    data = key.data
    if mask & selectors.EVENT_READ:
        recv_data = sock.recv(1024)  # Should be ready to read
        if recv_data:
            data.outb += recv_data
            recv_data = recv_data.decode("utf-8")
            request_list = recv_data.split("}")
            for req in request_list:
                if req == "":
                    continue
                req += "}"
                action(sock, server, req)
        else:
            print('closing connection to', data.addr)
            sel.unregister(sock)
            sock.close()
    if mask & selectors.EVENT_WRITE:
        if data.outb:
            print('echoing', repr(data.outb), 'to', data.addr)
            sent = sock.send(data.outb)  # Should be ready to write
            data.outb = data.outb[sent:]


if __name__ == '__main__':

    sel = selectors.DefaultSelector()

    skt = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    skt.bind((HOST, PORT))
    skt.listen()
    print('listening on', (HOST, PORT))
    skt.setblocking(False)
    sel.register(skt, selectors.EVENT_READ, data=None)

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

        print(server.post_json())
        server.remove_post("p1")
        print(server.post_json())
        server.add_comment("p0", "user", "test comment")
        print(server.post_json())

        server.add_comment(0, "Guy", "Really cool!")
        server.add_comment(0, "Billybob", "wow!")

        server.remove_comment(0, 2)

        server.add_like(1, "Guy")
        server.add_like(1, "user")

        server.remove_like(1, "Guy")

        print(server.user_json())
        print(server.post_json())

        server.save()

    try:
        while True:
            connections = sel.select(timeout=None)
            for key, mask in connections:
                # This determines whether or not
                if key.data is None:
                    accept_wrapper(key.fileobj)
                else:
                    service_connection(key, mask, server)
            # TODO authentication
            # TODO if user signs in make a new thread and start action
    except KeyboardInterrupt:
        print("Caught KeyboardInterrupt")
        sel.close()
        exit()
    finally:
        print("Closing Socket")
        sel.close()