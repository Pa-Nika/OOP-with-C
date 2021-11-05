#include "word_find.h"

lab0::WordFind::~WordFind() {
    fin.close();
}

lab0::WordFind::WordFind(const std::string &fileName) {
    fin.open(fileName);
}

ERROR lab0::WordFind::readMyFile() {

    if (!fin.is_open()){
        std::cout << "File is not open" << std::endl;
        return FILE_NOT_FOUND;
    }

    while (!fin.eof()) {
        getline(fin, strOfFile);

        std::transform(strOfFile.begin(), strOfFile.end(), strOfFile.begin(), ::tolower);

        breakIntoWord(strOfFile);
    }

    fin.close();
    return OK;
}

void lab0::WordFind::breakIntoWord(std::string fileLine) {

    std::regex expression ("([a-zA-Z0-9]{1,})");

    //To iterate over words in a string, iterators (pointers) are used:
    std::sregex_iterator first(fileLine.begin(), fileLine.end(), expression);     // first - first word iterator
    std::sregex_iterator last;                                                    // last - last word iterator

    long long *thisCount;
    thisCount = &count;
    *thisCount += distance(first, last);

    //division into words
    for (auto i = first; i != last; i++) {
        std::smatch wordNow = *i;                 //string match_results
        std::string wordInStr = wordNow.str();

        mp[wordInStr] += 1;
    }
}

const std::map<std::string, int> &lab0::WordFind::getMap() const {
    return mp;
}

long long lab0::WordFind::getCount() const {
    return count;
}

