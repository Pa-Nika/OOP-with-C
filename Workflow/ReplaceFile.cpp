#include "ReplaceFile.h"

void ReplaceFile::setArgs(const std::list<std::string> &block_data) {
    short count_args = START_POSITION;
    for (const auto & i : block_data) {
        count_args++;
        data.push_back(i);
    }

    if (data[0] != "replace" || count_args > BIG_COUNT_ARGS) {
        throw WorkerException("Bad order for replace command");
    }
    else {
        word1 = data[NAME_POSITION];
        word2 = data[NEXT_NAME_POSITION];
    }

}

std::list<std::string>& ReplaceFile::work(std::list<std::string> &answer_data) {
    for (auto it = answer_data.begin(); it != answer_data.end(); ++it) {
        if ((*it).find(word1) != std::string::npos) {
            //walk along the line that contains word1
            std::string str;
            for (int i = 0; i != it->size(); ++i) {
                if (('A' <= (*it)[i] && (*it)[i] <= 'Z') || ('a' <= (*it)[i] && (*it)[i] <= 'z')) {
                    str += (*it)[i];
                    if (word1 == str) {
                        auto pos = (*it).find(word1);
                        (*it).erase(pos, word1.size());
                        (*it).insert(pos, word2);
                        str.clear();
                    }
                }
                else
                    str.clear();
            }
        }
    }
    return answer_data;
}
