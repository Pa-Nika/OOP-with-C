#include "WriteFile.h"

void WriteFile::setArgs(const std::list<std::string> &block_data) {
    for (const auto & i : block_data) {
        data.push_back(i);
    }

    if (data[0] != "writefile" || data.size() > COUNT_ARGS) {
        throw WorkerException("Bad order for writefile");
    }
    else
        file_name = data[NAME_POSITION];
}

Text &WriteFile::work(Text & answer_data) {
    if (!answer_data.getStatus()) {
        throw WorkerException ("The \"write\" block has not received data from another block");
    }

    fout.open(file_name);
    if (!fout.is_open()) {
        throw WorkerException("can't open file for writefile");
    }

    for (auto & it : answer_data.getText()) {
        fout << it << std::endl;
    }

    answer_data.setStatus(false);
    return answer_data;
}

WriteFile::~WriteFile() {
    fout. close();
}
