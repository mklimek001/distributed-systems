import socket
import json
import threading

from server import send_message, send_to_message, PORT_NUM


def reader(self_socket):
    while True:
        reader_message_string = self_socket.recv(1024).decode('utf-8')
        reader_message_dict = json.loads(reader_message_string)
        if reader_message_dict['message'] in ["bye", "bye!"]:
            break

        print(reader_message_dict['message'], "  ~ ", reader_message_dict['user'], ' [', reader_message_dict['protocol'], '] ')


def udp_reader(self_socket):
    while True:
        coded_message, address_and_socket = self_socket.recvfrom(1024)
        udp_message_dict = json.loads(coded_message.decode('utf-8'))
        if udp_message_dict['message'] in ["bye", "bye!"]:
            break

        print(udp_message_dict['message'], "  ~ ", udp_message_dict['user'], ' [', udp_message_dict['protocol'], '] ')


if __name__ == '__main__':
    mode = 't'

    client_tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_tcp .connect(('127.0.0.1', PORT_NUM))

    client_udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    client_udp.connect(('127.0.0.1', PORT_NUM))

    print("New client started. After providing message, press enter two times.")
    print("Mode: t - type 't' to use tcp, 'u' to use udp, 'm' to use multicast udp.")
    nickname = input('Enter your nickname: ')
    send_message('t', nickname, "Hello Server", client_tcp)
    send_to_message('u', nickname, "Hello Server", client_udp)

    receiving_thread = threading.Thread(target=reader, args=(client_tcp,))
    receiving_thread.start()

    receiving_thread_udp = threading.Thread(target=udp_reader, args=(client_udp,))
    receiving_thread_udp.start()

    while True:
        lines = []

        while True:
            one_line = input()
            if one_line == '':
                break
            else:
                lines.append(one_line + '\n')

        given_text = ''.join(lines)[:-1]

        if given_text in ['t', 'u', 'm']:
            mode = given_text
            print("Mode: ", mode)

        else:
            if mode == 't':
                send_message(mode, nickname, given_text, client_tcp)
            elif mode == 'u':
                send_to_message(mode, nickname, given_text, client_udp)
            else:
                pass

        if given_text.lower() in ["bye", "bye!"]:
            break

    receiving_thread.join()





