#include <unistd.h>
#include <cstdio>
#include <netdb.h>
#include <iostream>
#include <string>
#include <sys/socket.h>
#include <sstream>
#include <string.h>
#include <jsoncpp/json/json.h>
#include <jsoncpp/json/value.h>
#include <iostream>
#include <fstream>


#include "rapidxml-1.13/rapidxml_utils.hpp"


using namespace std;
using namespace rapidxml;

void *task(void *);

void print();

static int connFd, listenFd;
bool active = true;

int portNo, portA, portP, ClientSocket, type;
char *ip;
bool inUse;
struct sockaddr_in serverAddress, clientAddress;

int parser() {

    string strFilePath = "/home/elias/CLionProjects/Odyssey_server/ejemplo.xml";
    rapidxml::file<> docFile(strFilePath.c_str());
    xml_document<> doc;    // character type defaults to char
    doc.parse<0>(docFile.data());    // 0 means default parse flags

    rapidxml::xml_node<> *pNode = doc.first_node();
    for (; pNode != NULL; pNode = pNode->next_sibling()) {
        std::cout << pNode->name() << std::endl;
        for (rapidxml::xml_node<> *pChildNode = pNode->first_node();
             pChildNode != NULL; pChildNode = pChildNode->next_sibling()) {
            std::cout << " " << pChildNode->name() << " " << pChildNode->value() << std::endl;
            for (rapidxml::xml_node<> *pChildNodeTwo = pChildNode->first_node();
                 pChildNodeTwo != NULL; pChildNodeTwo = pChildNodeTwo->next_sibling()) {
                std::cout << " " << pChildNodeTwo->name() << " " << pChildNodeTwo->value() << std::endl;
            }
        }
    }
}

int main() {

    //Generador_Datos generador_datos;
    //generador_datos.Obtener_datos("/home/elias/CLionProjects/servidor/output.txt");

    //return 0;

    //cout << "Escriba el tipo de servidor" <<endl;/*
    cout << "Escriba el puerto para el servidor" << endl;
    portNo = 8080;


    inUse = false;

    socklen_t len; //store size of the address



    //Set the thread Number
    pthread_t threadA[100];


    //create socket
    ClientSocket = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP);
    //listenFd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);


    if (ClientSocket < 0) {
        cout << "Cannot open socket" << endl;
        return 0;
    }


    serverAddress.sin_family = AF_INET;
    serverAddress.sin_addr.s_addr = INADDR_ANY;
    serverAddress.sin_port = htons(portNo);

    //bind socket
    if (bind(ClientSocket, (sockaddr *) &serverAddress, sizeof(serverAddress)) < 0) {
        cout << "Cannot bind" << endl;
        return 0;
    }

    listen(ClientSocket, 100);

    int noThread = 0;

    while (active) {
        socklen_t len = sizeof(clientAddress);
        //cout << "Listening" << endl;
        //this is where client connects. svr will hang in this mode until client conn
        connFd = accept(ClientSocket, (struct sockaddr *) &clientAddress, &len);

        if (connFd < 0) {
            cout << "Cannot accept connection" << endl;
            return 0;
        } else {
            cout << "Connection successful" << endl;
        }

        //pthread_t hilo;
        pthread_create(&threadA[noThread], NULL, task, NULL);
        //pthread_create(&hilo,NULL,task1,NULL);
        sleep(1);
        noThread++;
    }

    return 0;
}

void *task(void *dummyPt) {
    std::cout << "Thread No: " << pthread_self() << std::endl;
    char test[1024];
    bzero(test, 1025);
    std::string nombre;
    std::string dato;
    std::string bar;
    int dato_int;
    std::string tipo;
    std::ofstream outfile;
    //Json::StyledStreamWriter writer;
    //Json::Value value_obj;
    /*char* buffer = new char[100];
    read(connFd, buffer, 100);

    std::string tester(buffer);

    if (tester.size() != 0){

    }*/
    bool loop = false;
    std::cout << dummyPt << std::endl;
    //char s[1024];
    while (ClientSocket > 0) {
        //bzero(test, 301);

        do {

            recv(connFd, test, 1024, 0);
            //recv(connFd,datos, 1024, 0);

            std::cout << test << std::endl;


        } while (*test != '&');

        //read(connFd, test, 300);
        std::string tester(test);
        std::cout << tester << '\n';


        int o = 0;
        do {

            if (test[o] == 'V') {
                std::cout << test[o + 8] << std::endl;
            }
            if (test[o] == 'T') {
                for (int n = 9; test[o + n + 1] != '"'; n++) {
                    tipo += test[o + n];
                }
            }
            if (test[o] == 'd') {
                for (int n = 9; test[o + n + 1] != '"'; n++) {
                    dato += test[o + n];
                }
            }
            if (test[o] == 'n' && test[o + 1] == 'o' && test[o + 2] == 'm') {
                for (int n = 11; test[o + n + 1] != '"'; n++) {
                    nombre += test[o + n];
                }
            }
            o++;
        } while (test[o] != '&');

        std::cout << nombre << '\n';
        std::cout << tipo << '\n';
        std::cout << dato << '\n';

        /*value_obj["Variables"][nombre]["Tipo"] = tipo;
        value_obj["Variables"][nombre]["Dato"] = dato;
        std::cout << value_obj;
        outfile.open("output.json");
        writer.write(outfile, value_obj);
        //{"Variable":"[{\"Tipo\":\"int\",\"dato\":\"5\",\"nombre\":\"juancito\"}]"}* *
        //{"Variable":"[{\"Tipo\":\"int\",\"dato\":\"5\",\"nombre\":\"camilo\"}]"}* *


        outfile.close();*/



        do {
            //if (*test == 'h') {
            std::cout << "Enter respond: ";
            //bzero(s, 301);
            //std::cin.getline(s, 300);
            memset(&test[0], 0, sizeof(test));
            int n = 0;


            std::string dat;
            std::cin >> dat;

            for (int x = 0; dat[x] != NULL; x++) {
                test[x] = dat[x];

            }
            while (test[n] != NULL) {
                n++;
            }


            send(connFd, test, n, 0);
            std::cout << test << std::endl;

            if (!*test == 'e') {
                ClientSocket = 0;
                close(connFd);
                active = false;
                break;
            }
        } while (*test != '#');
    }
    std::cout << "\nClosing thread and conn" << std::endl;
    close(connFd);
}

//g++ /home/elias/CLionProjects/Odyssey_server/main.cpp -o servidor -pthread

