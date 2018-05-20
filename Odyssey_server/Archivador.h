//
// Created by elias on 20/05/18.
//

#ifndef ODYSSEY_SERVER_ARCHIVADOR_H
#define ODYSSEY_SERVER_ARCHIVADOR_H


#include <fstream>

class Archivador {
    Json::Value usuarios;
    Json::StyledStreamWriter writer;
    std::ofstream archivo;
    std::string strFilePath;

public:


    Archivador();

    bool agregar_usuario();
    //void agregar_cancion();
    //void agregar_amigo();
    bool buscar_usuario(std::string usuario);
    //void buscar_cancion();
    //void buscar_amigo();
    std::string actualizar_usuario();
    //void actualizar_cancion();
    //void actualizar_amigo();
    //void logear_usuario(std::string usuario);
    std::string builduser(std::string username, std::string pass, std::string name, std::string lastname, std::string age, std::string like, std::string friend_);

};


#endif //ODYSSEY_SERVER_ARCHIVADOR_H
