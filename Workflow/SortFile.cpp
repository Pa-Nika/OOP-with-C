#include "SortFile.h"

void SortFile::setArgs(const std::list<std::string> &block_data) {
    short count_args = START_POSITION;
    for (const auto & i : block_data) {
        count_args++;
        data.push_back(i);
    }

    if (data[0] != "sort" || count_args > SMALL_COUNT_ARGS) {
        throw WorkerException("Dad order for sort");
    }
}

std::list<std::string>& SortFile::work(std::list<std::string> & answer_data) {
    Comparator sort;
    answer_data.sort(sort);
    return  answer_data;
}
