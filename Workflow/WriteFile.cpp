#include "WriteFile.h"

void WriteFile::setArgs(const std::list<std::string> &block_data) {
    short count_args = START_POSITION;
    for (const auto & i : block_data) {
        count_args++;
        data.push_back(i);
    }

    if (data[0] != "writefile" || count_args > COUNT_ARGS) {
        throw WorkerException("Bad order for writefile");
    }
    else
        file_name = data[NAME_POSITION];
}

std::list<std::string>& WriteFile::work(std::list<std::string> & answer_data) {
    fout.open(file_name);
    if (!fout.is_open()) {
        throw WorkerException("can't open file for writefile");
    }

    for (auto & it : answer_data) {
        fout << it << std::endl;
    }
    return answer_data;
}

WriteFile::~WriteFile() {
    fout. close();
}
