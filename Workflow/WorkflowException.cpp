#include "WorkflowException.h"

WorkflowException::WorkflowException(const char *text_now) {
    text = text_now;
}

const char* WorkflowException::what() {
    return text;
}
