#include "SortFile.h"

void SortFile::setArgs(const std::list<std::string> &block_data) {
    for (const auto & i : block_data) {
        data.push_back(i);
    }

    if (data[0] != "sort" || data.size() > WITHOUT_ARGS) {
        throw WorkerException("Bad order for sort");
    }
}

Text &SortFile::work(Text & answer_data) {
    if (!answer_data.getStatus()) {
        throw WorkerException ("The \"sort\" block has not received data from another block");
    }

    std::list<std::string> my_text = answer_data.getText();
    Comparator sort;
    my_text.sort(sort);
    answer_data.setText(my_text);

    return  answer_data;
}