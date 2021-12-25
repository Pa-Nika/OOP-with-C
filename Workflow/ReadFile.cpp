#include "ReadFile.h"

void ReadFile::setArgs(const std::list<std::string>& block_data) {
    for (const auto & i : block_data) {
        data.push_back(i);
    }

    if (data[0] != "readfile" || data.size() > COUNT_ARGS) {
        throw WorkerException ("Can't open file for readfile");
    }
    else
        file_name = data[NAME_POSITION];
}

Text &ReadFile::work(Text & answer_data) {

    if (answer_data.getStatus()) {
        throw WorkerException ("The \"read\" block has received data from another block");
    }

    answer_data.setStatus(true);

    fin.open(file_name);
    if (!fin.is_open()) {
        throw WorkerException ("Can't open file for read");
    }

    std::list<std::string> my_text;

    while (!fin.eof()) {
        std::string str;
        getline(fin, str);
        my_text.push_back(str);
    }
    answer_data.setText(my_text);

    return answer_data;
}

ReadFile::~ReadFile() {
    fin.close();
}