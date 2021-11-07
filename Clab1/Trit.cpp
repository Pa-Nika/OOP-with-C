#include "Trit.h"

Trit operator& (Trit a, Trit b){
    if (a == True && b == True)
        return True;
    if (a == False || b == False)
        return False;
    return Unknown;
}

Trit operator| (Trit a, Trit b) {
    if (a == True || b == True)
        return True;
    if (a == False && b == False)
        return False;
    return Unknown;
}

Trit operator~ (Trit a) {
    if (a == False)
        return True;
    if (a == True)
        return False;
    return Unknown;
}

