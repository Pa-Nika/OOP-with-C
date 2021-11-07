#ifndef CLAB1_TRIT_H
#define CLAB1_TRIT_H

enum Trit {
    Unknown = 0, //00
    True = 1, //01
    False = 3 //11
};

Trit operator& (Trit a, Trit b);
Trit operator| (Trit a, Trit b);
Trit operator~ (Trit a);

#endif //CLAB1_TRIT_H
