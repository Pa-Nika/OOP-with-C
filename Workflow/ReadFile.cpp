#include "ReadFile.h"

void ReadFile::setArgs(const std::list<std::string>& block_data) {
    short count_args = START_POSITION;
    for (const auto & i : block_data) {
        count_args++;
        data.push_back(i);
    }

    if (data[0] != "readfile" || count_args  > COUNT_ARGS) {
        throw WorkerException ("Can't open file for readfile");
    }
    else
        file_name = data[NAME_POSITION];
}

std::list<std::string>& ReadFile::work(std::list<std::string>& answer_data) {
    fin.open(file_name);
    if (!fin.is_open()) {
        throw WorkerException ("Can't open file for read");
    }

    while (!fin.eof()) {
        std::string str;
        getline(fin, str);
        answer_data.push_back(str);
    }

    return answer_data;
}

ReadFile::~ReadFile() {
    fin.close();
}




