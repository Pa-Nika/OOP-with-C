cmake_minimum_required(VERSION 3.17)
project(Clab1)

set(CMAKE_CXX_STANDARD 17)
add_subdirectory(lib/googletest)

include_directories(lib/googletest/googletest/include)
add_executable(Clab1 main.cpp Trit.cpp TritSet.cpp)
target_link_libraries(Clab1 gtest gtest_main)
