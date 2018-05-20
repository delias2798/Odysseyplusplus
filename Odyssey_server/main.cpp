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
#include <cstring>

#include "Archivador.h"
#include "Archivador.cpp"
#include "rapidxml-1.13/rapidxml_utils.hpp"


using namespace std;
using namespace rapidxml;

void *task(void *);

void print();

static int connFd, listenFd;
bool active = true;

int portNo, portA, portP, ClientSocket, type;
char test[300024];
char message[300024];
std::string string1;
char *ip;
bool inUse;
struct sockaddr_in serverAddress, clientAddress;
std::string strFilePath = "/home/elias/CLionProjects/Odyssey_server/ejemplo.xml";
Archivador archivador;


int parser() {

    rapidxml::file<> docFile(strFilePath.c_str());
    xml_document<> doc;    // character type defaults to char
    doc.parse<0>(docFile.data());    // 0 means default parse flags


    rapidxml::xml_node<> *pNode = doc.first_node();
    for (; pNode != NULL; pNode = pNode->next_sibling()) {
        std::cout << pNode->name() << std::endl;
        for (rapidxml::xml_node<> *pChildNode = pNode->first_node();
             pChildNode != NULL; pChildNode = pChildNode->next_sibling()) {

            std::cout << " " << pChildNode->name() << " " << " " << pChildNode->value() << std::endl;

            std::cout << " " << pChildNode->document()->name() << " " << pChildNode->document()->value() << std::endl;
            for (rapidxml::xml_node<> *pChildNodeTwo = pChildNode->first_node();
                 pChildNodeTwo != NULL; pChildNodeTwo = pChildNodeTwo->next_sibling()) {
                std::cout << " " << pChildNodeTwo->name() << " " << pChildNodeTwo->value() << std::endl;
                std::string n(pChildNodeTwo->name());
                if(n == pChildNodeTwo->name())
                    std::cout << "truuue" << '\n';
            }
        }
    }
}



void *solicitar(void *dummyPt){
    char *instruction = test;
    std::cout << "------------------------------" << std::endl;
    std::cout << instruction << std::endl;
    std::cout << "------------------------------" << std::endl;
    std::string instr_string(instruction);
    //FILE * pFile;
    //pFile = fopen ("/home/elias/CLionProjects/Odyssey_server/ejemplo.txt", "w+");
    std::ofstream xml("/home/elias/CLionProjects/Odyssey_server/ejemplo.xml");
    xml << instruction;
    xml.write(instruction, sizeof(instruction));
    //fclose(pFile);
    xml.close();
    //parser();
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


void *task(void *dummyPt)  {
    std::cout << "Thread No: " << pthread_self() << std::endl;
    bzero(test, 300025);
    char action[13];
    std::string regist;
    std::string action_str;
    std::string nombre;
    std::string dato;
    std::string bar;
    int dato_int = 0;
    std::string tipo;
    std::ofstream outfile;


    //pthread_t threadB[100];
    int noThread = 0;
    //Json::StyledStreamWriter writer;
    //Json::Value value_obj;

    bool loop = false;
    std::cout << dummyPt << std::endl;
    //char s[1024];
    while (ClientSocket > 0) {
        //bzero(test, 301);
        outfile.open("/home/elias/CLionProjects/Odyssey_server/ejemplo.xml", std::ios_base::trunc);
        outfile.close();
        outfile.open("/home/elias/CLionProjects/Odyssey_server/ejemplo.xml", std::ios_base::app);

        do {

            recv(connFd, test, 300024, 0);
            //recv(connFd,datos, 1024, 0);

            std::cout << test << std::endl;
            memmove(message, test + 1, strlen(test + 1) + 1);

            outfile.write(message,strlen(message));

            //writer.write(outfile, remove_backn(test));

            //dato_int += 1;



        } while (*test != '&');
        //*test = '\0';
        std::cout << "..........................." << '\n';
        //pthread_create(&threadB[noThread], NULL, solicitar, NULL);


        //read(connFd, test, 300);

        //std::string tester(*message);
        //writer.write(outfile, tester);

        outfile.close();

        memmove(action, test + (strlen(test) - 15), 14);
        action_str = action;
        std::cout << action_str << '\n';
        if(action_str == "<!--new_usu-->"){
            std::cout << action_str << '\n';
            regist = archivador.agregar_usuario();
        }
        else if (action_str == "<!--bus_usu-->"){std::cout << action_str << '\n';}
        else if (action_str == "<!--act_usu-->"){
            std::cout << action_str << '\n';
            regist = archivador.actualizar_usuario();
        }
        else if (action_str == "<!--new_can-->"){
            std::cout << action_str << '\n';

        }
        else if (action_str == "<!--bus_can-->"){std::cout << action_str << '\n';}
        else if (action_str == "<!--act_can-->"){std::cout << action_str << '\n';}
        else if (action_str == "<!--new_ami-->"){std::cout << action_str << '\n';}
        else if (action_str == "<!--bus_ami-->"){std::cout << action_str << '\n';}
        else if (action_str == "<!--act_ami-->"){std::cout << action_str << '\n';}
        else if (action_str == "<!--log_usu-->"){
            outfile.open("/home/elias/CLionProjects/Odyssey_server/ejemplo.txt", std::ios_base::trunc);
            outfile.close();
        }



        do {
            //if (*test == 'h') {
            std::cout << "Enter respond: ";
            //bzero(s, 301);
            //std::cin.getline(s, 300);
            memset(&test[0], 0, sizeof(test));
            int n = 0;


            std::string dat;
            dat = regist;
            std::cin >> dat;

            for (int x = 0; dat[x] != NULL; x++) {
                test[x] = dat[x];

            }
            while (test[n] != NULL) {
                n++;
            }


            send(connFd, test, n, 0);
            std::cout << test << std::endl;

            if (*test == 'e') {
                ClientSocket = 0;
                close(connFd);
                active = false;
                break;
            }
        } while (*test != '#');
        close(connFd);

        break;


        //ClientSocket = 0;
    }
    std::cout << "\nClosing thread and conn" << std::endl;
    close(connFd);
}

/*value_obj["Variables"][nombre]["Tipo"] = tipo;
        value_obj["Variables"][nombre]["Dato"] = dato;
        std::cout << value_obj;
        outfile.open("output.json");
        writer.write(outfile, value_obj);
        //{"Variable":"[{\"Tipo\":\"int\",\"dato\":\"5\",\"nombre\":\"juancito\"}]"}* *
        //{"Variable":"[{\"Tipo\":\"int\",\"dato\":\"5\",\"nombre\":\"camilo\"}]"}* *


        outfile.close();*/
/*do {

            recv(connFd, test, 30024, 0);
            //recv(connFd,datos, 1024, 0);

            std::cout << test << std::endl;
            end_char = test[strlen(test) - 1];


        } while (*test != '&');*/

//g++ /home/elias/CLionProjects/Odyssey_server/main.cpp -o servidor -pthread -ljsoncpp

