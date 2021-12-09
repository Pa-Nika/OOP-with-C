#ifndef WORKFLOW_WORKEREXCEPTION_H
#define WORKFLOW_WORKEREXCEPTION_H
#include "IException.h"

class WorkerException :  public IException {
public:
    explicit WorkerException(const char* text);
    const char* what() override;
};

#endif //WORKFLOW_WORKEREXCEPTION_H
