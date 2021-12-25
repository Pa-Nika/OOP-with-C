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

Text &DumpFile::work(Text &answer_data) {
    if (!answer_data.getStatus()) {
        throw WorkerException ("The \"dump\" block has not received data from another block");
    }

    fout.open(file_name);

    if (!fout.is_open()) {
        throw WorkerException("Can't open file for dump");
    }

    for (auto & it : answer_data.getText()) {
        fout << it << std::endl;
    }

    return answer_data;
}

DumpFile::~DumpFile() {
    fout.close();
}
