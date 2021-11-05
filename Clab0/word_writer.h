#ifndef CLAB0_WORD_WRITER_H
#define CLAB0_WORD_WRITER_H

#include <fstream>
#include <iostream>
#include <string>
#include <list>
#include <cmath>

namespace lab0 {
    class WordWriter {
    private:
        std::ofstream fout;
        static double percOfWords (long long count, int myCount);

    public:
        explicit WordWriter (const std::string& fileName);
        ~WordWriter();

        void writeFile (const std::list <std::pair<std::string, int>>& word, long long count);
    };
}

#endif //CLAB0_WORD_WRITER_H
