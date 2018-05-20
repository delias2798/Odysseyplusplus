//
// Created by elias on 20/05/18.
//

#include <jsoncpp/json/json.h>
#include <iostream>
#include "Archivador.h"
#include "rapidxml-1.13/rapidxml_utils.hpp"
#include "rapidxml-1.13/rapidxml_print.hpp"


bool Archivador::agregar_usuario() {

    strFilePath = "/home/elias/CLionProjects/Odyssey_server/ejemplo.xml";
    rapidxml::file<> docFile(strFilePath.c_str());
    rapidxml::xml_document<> doc;    // character type defaults to char
    doc.parse<0>(docFile.data());    // 0 means default parse flags
    std::string name;
    std::string age;
    std::string lastname;
    std::string friends;
    std::string like;
    std::string pass;
    std::string username;

    int x=0;
    rapidxml::xml_node<> *pNode = doc.first_node();
    for (; pNode != NULL; pNode = pNode->next_sibling()) {
        std::cout << pNode->name() << std::endl;
        for (rapidxml::xml_node<> *pChildNode = pNode->first_node();
             pChildNode != NULL; pChildNode = pChildNode->next_sibling()) {
            std::string nodo_name(pChildNode->name());
            std::string nodo_value(pChildNode->value());
            std::cout<<x;
            x++;
            if("name" == nodo_name){
                name = nodo_value;
            } else if("age" == nodo_name){
                age = nodo_value;
            }else if("lastname" == nodo_name){
                lastname = nodo_value;
            }else if("friends" == nodo_name){
                friends = nodo_value;
            }else if("like" == nodo_name){
                like = nodo_value;
            }else if("pass" == nodo_name){
                pass = nodo_value;
            }else if("username" == nodo_name){
                username = nodo_value;
                if(buscar_usuario(username)){std::cout<<"false";}
                    //return false;
            }
            std::cout << "<<<< "<< nodo_name << " "<< nodo_value << " "<< username << " "  << name << " " << age << " " << lastname << " " << friends << " " << like << " >>>>>" << pass << std::endl;
            std::cout << " " << pChildNode->name() << " " << pChildNode->value() << std::endl;
        }
    }

    usuarios["Usuario"][username]["nombre"] = name;
    usuarios["Usuario"][username]["contrasena"] = pass;
    usuarios["Usuario"][username]["edad"] = age;
    usuarios["Usuario"][username]["amigos"] = friends;
    usuarios["Usuario"][username]["gustos"] = like;
    usuarios["Usuario"][username]["Apellido"] = lastname;
    archivo.open("/home/elias/CLionProjects/Odyssey_server/usuarios.json");
    writer.write(archivo, usuarios);
    archivo.close();
    return true;
    /*
    usuarios["Usuario"][nombre]["Tipo"] = tipo;
    usuarios["Variables"][nombre]["Dato"] = dato;
    std::cout << usuarios;
    outfile.open("output.json");
    writer.write(outfile, value_obj);*/

}

bool Archivador::buscar_usuario(std::string usuario) {


    strFilePath = "/home/elias/CLionProjects/Odyssey_server/ejemplo.xml";
    rapidxml::file<> docFile(strFilePath.c_str());
    rapidxml::xml_document<> doc;    // character type defaults to char
    doc.parse<0>(docFile.data());

    rapidxml::xml_node<> *pNode = doc.first_node();
    for (; pNode != NULL; pNode = pNode->next_sibling()) {
        std::cout << pNode->name() << std::endl;
        for (rapidxml::xml_node<> *pChildNode = pNode->first_node();
             pChildNode != NULL; pChildNode = pChildNode->next_sibling()) {
            std::cout << " " << pChildNode->name() << " " << pChildNode->value() << std::endl;
            std::string user(pChildNode->name());
            if(usuarios["Usuario"][user] == usuario){
                return false;
            }

        }
    }

    return true;
}



std::string Archivador::builduser(std::string username, std::string pass, std::string name, std::string lastname, std::string age, std::string like, std::string friend_){
    rapidxml::xml_document<> doc_;
    rapidxml::xml_node<>* decl = doc_.allocate_node(rapidxml::node_declaration);
    decl->append_attribute(doc_.allocate_attribute("version", "1.0"));
    decl->append_attribute(doc_.allocate_attribute("encoding", "utf-8"));
    doc_.append_node(decl);

    rapidxml::xml_node<>* root_ = doc_.allocate_node(rapidxml::node_element, "user");
    doc_.append_node(root_);

    rapidxml::xml_node<>* name_ = doc_.allocate_node(rapidxml::node_element, "name");
    char* char_name = (char*) name.c_str();
    name_->value(char_name);
    root_->append_node(name_);

    rapidxml::xml_node<>* friend__ = doc_.allocate_node(rapidxml::node_element, "friend");
    char* char_frie = (char*) friend_.c_str();
    friend__->value(char_frie);
    root_->append_node(friend__);

    rapidxml::xml_node<>* username_ = doc_.allocate_node(rapidxml::node_element, "username");
    char* char_user = (char*) username.c_str();
    username_->value(char_user);
    root_->append_node(username_);

    rapidxml::xml_node<>* lastname_ = doc_.allocate_node(rapidxml::node_element, "lastname");
    char* char_last = (char*) lastname.c_str();
    lastname_->value(char_last);
    root_->append_node(lastname_);

    rapidxml::xml_node<>* age_ = doc_.allocate_node(rapidxml::node_element, "age");
    char* char_age = (char*) age.c_str();
    age_->value(char_age);
    root_->append_node(age_);

    rapidxml::xml_node<>* like_ = doc_.allocate_node(rapidxml::node_element, "like");
    char* char_like = (char*) like.c_str();
    like_->value(char_like);
    root_->append_node(like_);

    rapidxml::xml_node<>* pass_ = doc_.allocate_node(rapidxml::node_element, "pass");
    char* char_pass = (char*) pass.c_str();
    pass_->value(char_pass);
    root_->append_node(pass_);




// Convert doc to string if needed
    std::string xml_as_string;
    rapidxml::print(std::back_inserter(xml_as_string), doc_);
    return xml_as_string;

// Save to file
    std::ofstream file_stored("/home/toshiba/Escritorio/Proyecto2/user_send.xml");
    file_stored << doc_;
    file_stored.close();
    doc_.clear();


}



std::string Archivador::actualizar_usuario() {

    std::string usuario;
    strFilePath = "/home/elias/CLionProjects/Odyssey_server/ejemplo.xml";
    rapidxml::file<> docFile(strFilePath.c_str());
    rapidxml::xml_document<> doc;    // character type defaults to char
    doc.parse<0>(docFile.data());

    rapidxml::xml_node<> *pNode = doc.first_node();
    for (; pNode != NULL; pNode = pNode->next_sibling()) {
        std::cout << pNode->name() << std::endl;
        for (rapidxml::xml_node<> *pChildNode = pNode->first_node();
             pChildNode != NULL; pChildNode = pChildNode->next_sibling()) {
            std::cout << " " << pChildNode->name() << " " << pChildNode->value() << std::endl;
            usuario = pChildNode->name();
        }
    }

    std::string name = usuarios["Usuario"][usuario]["nombre"].asString();
    std::string age = usuarios["Usuario"][usuario]["edad"].asString();
    std::string lastname = usuarios["Usuario"][usuario]["Apellido"].asString();
    std::string friends = usuarios["Usuario"][usuario]["amigos"].asString();
    std::string like = usuarios["Usuario"][usuario]["gustos"].asString();
    std::string pass = usuarios["Usuario"][usuario]["contrasena"].asString();
    //std::string username = usuarios["Usuario"][usuario]["nombre"].asString();

    name = usuarios["Usuario"][usuario]["nombre"].asString();
    return builduser(usuario, pass, name, lastname, age, like, friends);

};


Archivador::Archivador(){

    usuarios["Usuario"]["delias"]["nombre"] = "Elias";
    usuarios["Usuario"]["delias"]["contrasena"] = "1234";
    usuarios["Usuario"]["david1"]["nombre"] = "David";
    usuarios["Usuario"]["david1"]["contrasena"] = "4321";
    usuarios["Usuario"]["nedd"]["nombre"] = "Eduardo";
    usuarios["Usuario"]["nedd"]["contrasena"] = "abcd";
    usuarios["Usuario"]["jjj"]["nombre"] = "juancito";
    usuarios["Usuario"]["jjj"]["contrasena"] = "jojo";
    archivo.open("/home/elias/CLionProjects/Odyssey_server/usuarios.json");
    writer.write(archivo, usuarios);
    archivo.close();

}


/*<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<user username="edd">
               <age>22</age>
<friends>No tengo</friends>
<lastname>Solano</lastname>
<like>Any</like>
<name>Eduardo</name>
<pass>123456</pass>
</user>*/
