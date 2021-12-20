#include "DumpFile.h"

void DumpFile::setArgs(const std::list<std::string> &block_data) {
    for (const auto & i : block_data) {
        data.push_back(i);
    }

    if (data[0] != "dump" || data.size() > COUNT_ARGS) {
        throw WorkerException("Bab order for dump");
    }
    else
        file_name = data[NAME_POSITION];
}

std::list<std::string>& DumpFile::work(std::list<std::string> &answer_data) {
    if (answer_data.empty()) {
        throw WorkerException ("The \"dump\" block has not received data from another block");
    }

    fout.open(file_name);

    if (!fout.is_open()) {
        throw WorkerException("Can't open file for dump");
    }

    for (auto & it : answer_data) {
        fout << it << std::endl;
    }

    return answer_data;
}

DumpFile::~DumpFile() {
    fout.close();
}
