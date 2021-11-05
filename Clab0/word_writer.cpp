#include "word_writer.h"

lab0::WordWriter::WordWriter(const std::string& fileName) {
    fout.open(fileName);
}

lab0::WordWriter::~WordWriter() {
    fout.close();
}

void lab0::WordWriter::writeFile(const std::list<std::pair<std::string, int>>& word, long long count) {
    double perc;

    for(auto & it : word) {
        perc = percOfWords(count, it.second);

        fout << it.first << ";" << it.second << ";" << round((perc * 10))/10.0 << "%" << std::endl;
    }
}

double lab0::WordWriter::percOfWords(const long long int count, int myCount) {
    //long long allWords = *count;
    return (double)(myCount * 100) * 1.0 / (double)count;
}