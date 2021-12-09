#ifndef WORKFLOW_IFACTORY_H
#define WORKFLOW_IFACTORY_H
#include "IWorker.h"

class IFactory {
public:
    virtual std::unique_ptr<IWorker> createWorker() = 0;
};

#endif //WORKFLOW_IFACTORY_H
