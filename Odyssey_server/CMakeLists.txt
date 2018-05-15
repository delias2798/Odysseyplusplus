cmake_minimum_required(VERSION 3.9)
project(Odyssey_server)

set(CMAKE_CXX_STANDARD 11)

add_executable(Odyssey_server main.cpp parser.hpp tinyxml2.cpp tinyxml2.h rapidxml-1.13)
