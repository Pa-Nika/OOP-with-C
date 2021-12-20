#include "SortFile.h"

void SortFile::setArgs(const std::list<std::string> &block_data) {
    for (const auto & i : block_data) {
        data.push_back(i);
    }

    if (data[0] != "sort" || data.size() > WITHOUT_ARGS) {
        throw WorkerException("Dad order for sort");
    }
}

std::list<std::string>& SortFile::work(std::list<std::string> & answer_data) {
    if (answer_data.empty()) {
        throw WorkerException ("The \"sort\" block has not received data from another block");
    }

    Comparator sort;
    answer_data.sort(sort);
    return  answer_data;
}
