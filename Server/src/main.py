# Sumner Bradley
import pickle
import socket
from src.server import Server

HOST = '127.0.0.1'
PORT = 65432

if __name__ == '__main__':
    server = Server()

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        conn, addr = s.accept()
        with conn:
            print('Connected by', addr)
            while True:
                message = conn.recv(1024).decode()

                print(message)
                conn.close()
