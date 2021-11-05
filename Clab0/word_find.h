#ifndef CLAB0_WORD_FIND_H
#define CLAB0_WORD_FIND_H

#include <fstream>
#include <iostream>
#include <string>
#include <regex>

typedef enum ERROR {
    FILE_NOT_FOUND,
    NOT_ENOUGH_ARGS,
    OK,
} ERROR;

namespace lab0 {

    class WordFind {
    private:
        std::ifstream fin;
        std::string strOfFile;
        std::map <std::string, int> mp ;
        long long count = 0;

        void breakIntoWord (std::string fileLine);

    public:
        explicit WordFind(const std::string &fileName);
        ~WordFind();

        ERROR readMyFile ();

        long long getCount () const;
        const std::map<std::string, int>& getMap() const;
    };
}

#endif //CLAB0_WORD_FIND_H
