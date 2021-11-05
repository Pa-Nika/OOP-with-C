cmake_minimum_required(VERSION 3.17)
project(Clab0)

set(CMAKE_CXX_STANDARD 17)

add_executable(Clab0 main.cpp list_filler.cpp word_writer.cpp word_find.cpp)

set(CMAKE_CXX_STANDARD_LIBRARIES "-static-libgcc -static-libstdc++ -lwsock32 -lws2_32 ${CMAKE_CSS_STANDARD_LIBRARIES}")
set (CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Wl,-Bstatic,--whole-archive -lwinpthread -Wl,--no-whole-archive")