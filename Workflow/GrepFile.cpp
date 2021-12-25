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

Text &GrepFile::work(Text & answer_data) {
    if (!answer_data.getStatus()) {
        throw WorkerException ("The \"grep\" block has not received data from another block");
    }

    std::list<std::string> my_text = answer_data.getText();

    for (auto it = my_text.begin(); it != my_text.end(); ++it) {
        if ((*it).find(word) != std::string::npos) {
            my_text.erase(it);
        }
    }
    answer_data.setText(my_text);

    return answer_data;
}