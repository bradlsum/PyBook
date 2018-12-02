# Sumner Bradley
import pickle
import socket
import json
from src.server import Server
from src.post import Post

HOST = '127.0.0.1'
PORT = 65432

if __name__ == '__main__':
    server = Server()

    server.add_user("user", "John", "Doe", "email@email.com", "PASS")

    print(server.user_json())
