#ifndef WORKFLOW_WORKER_H
#define WORKFLOW_WORKER_H
#include <fstream>
#include <iostream>
#include <memory>
#include <list>
#include <vector>
#include "WorkerException.h"
#include "Constants.h"

class IWorker {
public:
    virtual std::list<std::string>& work (std::list<std::string>& answer_data) = 0;
    virtual void setArgs(const std::list<std::string>& block_data) = 0;
};


#endif //WORKFLOW_WORKER_H
