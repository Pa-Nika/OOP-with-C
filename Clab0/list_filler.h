#ifndef CLAB0_LIST_FILLER_H
#define CLAB0_LIST_FILLER_H

#include <iostream>
#include <string>
#include <map>
#include <list>

namespace lab0 {
    class ListFiller {
    private:
        std::list<std::pair<std::string, int>> words;

        class Comparator {
        public:
            bool operator()(const std::pair<std::string, int> &a, const std::pair<std::string, int> &b);
        };

    public:
        void addToList(const std::map<std::string, int> &mp);

        std::list<std::pair<std::string, int>> getSortList();
    };
}

#endif //CLAB0_LIST_FILLER_H
