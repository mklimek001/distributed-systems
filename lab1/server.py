import socket
import json
import threading
import time

PORT_NUM = 9000


def send_message(protocol, user, message, dest_socket):
    message_to_send_dict = {
        'protocol': protocol,
        'user': user,
        'message': message
    }

    message_to_send_string = json.dumps(message_to_send_dict)
    dest_socket.send(message_to_send_string.encode('utf-8'))


def send_to_message(protocol, user, message, dest_socket, address=("127.0.0.1", PORT_NUM)):
    message_to_send_dict = {
        'protocol': protocol,
        'user': user,
        'message': message
    }

    message_to_send_string = json.dumps(message_to_send_dict)
    dest_socket.sendto(message_to_send_string.encode('utf-8'), address)


def single_client_management(name, user_socket):
    while True:
        client_message_string = user_socket.recv(1024).decode('utf-8')
        client_message_dict = json.loads(client_message_string)
        print(client_message_dict['message'],  "  ~ ", client_message_dict['user'], ' [', client_message_dict['protocol'], '] ')

        if client_message_dict['message'].lower() in ["bye", "bye!"]:
            send_message('t', server_name, "bye", user_socket)
            user_socket.close()
            delete_from_client(name)
            break

        else:
            send_to_all(name, client_message_string)
            client_response = "Message confirmed"
            send_message('t', server_name, client_response, user_socket)


def udp_listening_thread():
    server_udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_udp.bind(('127.0.0.1', PORT_NUM))

    udp_clients = []

    while True:
        coded_message, address_and_socket = server_udp.recvfrom(1024)
        udp_message_dict = json.loads(coded_message.decode('utf-8'))
        print(udp_message_dict['message'],  "  ~ ", udp_message_dict['user'], ' [', udp_message_dict['protocol'], '] ')

        if udp_message_dict['message'].startswith("Hello"):
            time.sleep(0.1)
            udp_init_response = "Hello " + udp_message_dict['user'] + "!"
            send_to_message('u', server_name, udp_init_response, server_udp, address_and_socket)

            udp_client_info = {
                'name': udp_message_dict['user'],
                'socket': address_and_socket,
            }
            udp_clients.append(udp_client_info)

        else:
            for client in udp_clients:
                if client['name'] != udp_message_dict['user']:
                    server_udp.sendto(coded_message, client['socket'])

            client_response = "Message confirmed"
            send_to_message('u', server_name, client_response, server_udp, address_and_socket)


if __name__ == '__main__':
    server_name = "Server"
    server_tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_tcp.bind(('127.0.0.1', PORT_NUM))

    server_tcp.listen()
    clients = []

    udp_thread = threading.Thread(target=udp_listening_thread, args=())
    udp_thread.start()

    print("Server is running...")

    while True:
        new_client_tcp, address = server_tcp.accept()

        message_string = new_client_tcp.recv(1024).decode('utf-8')
        message_dict = json.loads(message_string)
        print(message_dict['message'],  "  ~ ", message_dict['user'], ' [', message_dict['protocol'], '] ')

        init_response = "Hello " + message_dict['user'] + "!"
        send_message('t', server_name, init_response, new_client_tcp)

        new_thread = threading.Thread(target=single_client_management, args=(message_dict['user'], new_client_tcp,))

        client_info = {
            'name': message_dict['user'],
            'socket': new_client_tcp,
        }

        clients.append(client_info)
        new_thread.start()


        def send_to_all(self_name, ready_message):
            for client in clients:
                if client['name'] != self_name:
                    client['socket'].send(ready_message.encode('utf-8'))


        def delete_from_client(client_name):
            for client in clients:
                if client['name'] == client_name:
                    clients.remove(client)













