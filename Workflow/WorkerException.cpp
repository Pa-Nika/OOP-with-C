#include "WorkerException.h"

const char *WorkerException::what() {
    return text;
}

WorkerException::WorkerException(const char *text_now) {
    text = text_now;
}
