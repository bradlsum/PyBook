from socket import socket, AF_INET, SOCK_STREAM
import  json



def to_json():
    temp = '{"id":"' + "1010" + '","first":"' + "loco" + '",'
    temp += '"last":"' + "nose" + '","email":"' + "tucola@gmail.com" + '",'
    temp += '"username":"' + "eljuana22" + '","password":"' + "nomeclacontra" + '"}'

    return temp

serversocket = socket(AF_INET, SOCK_STREAM)
serversocket.bind( ('localhost', 9999) )
serversocket.listen(5)


while True:

   conn, addr = serversocket.accept()
   print("Got connection from", addr)
   length_of_message = int.from_bytes(conn.recv(2), byteorder='big')
   msg = conn.recv(length_of_message).decode("UTF-8")
   print(msg)
   print(len(msg))


   # Note the corrected indentation below
   idk = to_json()
   message_to_send = idk.encode("UTF-8")
   conn.send(len(message_to_send).to_bytes(2, byteorder='big'))
   conn.send(message_to_send)


   conn.close()


