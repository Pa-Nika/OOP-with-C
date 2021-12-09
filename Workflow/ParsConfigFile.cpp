#include "ParsConfigFile.h"

ParsConfigFile::~ParsConfigFile() {
    fin.close();
}

void ParsConfigFile::readFile() {
    fin.open(file_name);
    std::string line_from_file;

    if (!fin.is_open()) {
        throw WorkflowException("Can't open file for parsing");
    }
    bool flag = true;

    getline (fin, line_from_file); //read "desc"
    while (!fin.eof() && flag) {
        getline (fin, line_from_file);
        flag = breakIntoPieces(line_from_file); //fill data_workflow
    }

    //read a string from indices and ->
    getline (fin, line_from_file);
    findOrder(line_from_file);
}

bool ParsConfigFile::breakIntoPieces(std::string &line) {
    bool flag = false, flag_for_index = false;
    int index = START_POSITION, count = START_POSITION;
    std::string words;
    std::pair<int, std::vector<std::string>> node;
    std::pair <int, std::list<std::string>> node_new;

    for (int i = 0; i <= line.size(); i++) {
        if (line[i] == '=')
            flag = true;

        if (!flag && ('0' <= line[i] && line[i] <= '9')) {
            index *= 10;
            index += line[i] - '0';
            flag_for_index = true;
        }

        if (flag && !(line[i] == ' ' || line[i] == '\0' || line[i] == '=')) {
            words += line[i];
            count ++;
        }

        if (flag && count != START_POSITION && (line[i] == ' ' || line[i] == '\0')) {
            node.second.push_back(words);
            node_new.second.push_back(words);
            words.clear();
        }
    }

    if (flag_for_index) {
        node.first = index;
        node_new.first = index;
        data_workflow.push_back(node_new);
    }

    return flag;
}

void ParsConfigFile::findOrder(std::string &line) {
    int index = START_POSITION;
    bool flag_for_index = false;

    for (int i = 0; i <= line.size(); i++) {
        if ('0' <= line[i] && line[i] <= '9') {
            index *= 10;
            index += line[i] - '0';
            flag_for_index = true;
        }
        else if (line[i] == ' ' || line[i] == '\0') {
            if (flag_for_index) {
                order_of_block.push_back(index);
                flag_for_index = false;
                index = START_POSITION;
            }
        }
        else if (line[i] != '-' && line[i] != '>') {
            throw WorkflowException("Wrong input");
        }
    }
}
