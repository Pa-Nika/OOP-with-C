#ifndef WORKFLOW_WORKFLOWEXCEPTION_H
#define WORKFLOW_WORKFLOWEXCEPTION_H
#include "IException.h"

class WorkflowException : public IException {
public:
    explicit WorkflowException(const char* text_now);
    const char* what() override;
};

#endif //WORKFLOW_WORKFLOWEXCEPTION_H
