cmake_minimum_required(VERSION 3.17)
project(Workflow)

set(CMAKE_CXX_STANDARD 17)

add_executable(Workflow main.cpp ParsConfigFile.cpp WorkflowExecutor.cpp IWorker.h
        ReadFile.cpp IFactory.h BlocksFactory.h WriteFile.cpp GrepFile.cpp SortFile.cpp
        ReplaceFile.cpp DumpFile.cpp IException.h WorkerException.cpp Constants.h WorkflowException.cpp Text.h)

set(CMAKE_CXX_STANDARD_LIBRARIES "-static-libgcc -static-libstdc++ -lwsock32 -lws2_32 ${CMAKE_CSS_STANDARD_LIBRARIES}")
set (CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Wl,-Bstatic,--whole-archive -lwinpthread -Wl,--no-whole-archive")