#ifndef WORKFLOW_IEXCEPTION_H
#define WORKFLOW_IEXCEPTION_H
#include <exception>

class IException : public std::exception {
protected:
    const char* text{};
public:
    virtual const char* what() = 0;
};

#endif //WORKFLOW_IEXCEPTION_H
