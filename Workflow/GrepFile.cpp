#include "GrepFile.h"

void GrepFile::setArgs(const std::list<std::string> &block_data) {
    for (const auto & i : block_data) {
        data.push_back(i);
    }

    if (data[0] != "grep" || data.size() > COUNT_ARGS) {
        throw WorkerException("Bad order for grep");
    }
    else
        word = data[NAME_POSITION];
}

std::list<std::string>& GrepFile::work(std::list<std::string> &answer_data) {
    if (answer_data.empty()) {
        throw WorkerException ("The \"grep\" block has not received data from another block");
    }

    for (auto it = answer_data.begin(); it != answer_data.end(); ++it) {
        if ((*it).find(word) != std::string::npos) {
            answer_data.erase(it);
        }
    }
    return answer_data;
}
