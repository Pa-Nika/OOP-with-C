#include <iostream>
#include "list_filler.h"
#include "word_writer.h"
#include "word_find.h"

int main(int argc, char* argv[]) {

    if (argc < 3) {
        std::cout << "Arguments entered incorrectly";
        return  NOT_ENOUGH_ARGS;
    }

    lab0::WordFind findInMyFile(argv[1]);
    ERROR code = findInMyFile.readMyFile();
    if (code == FILE_NOT_FOUND)
        return -1;

    lab0::ListFiller addToMyList;
    addToMyList.addToList(findInMyFile.getMap());

    lab0::WordWriter writeOut(argv[2]);
    writeOut.writeFile(addToMyList.getSortList(), findInMyFile.getCount());

    return 0;
}

//Дружественные классы/функции