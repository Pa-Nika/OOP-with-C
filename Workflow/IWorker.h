#ifndef WORKFLOW_WORKER_H
#define WORKFLOW_WORKER_H
#include <fstream>
#include <iostream>
#include <memory>
#include <list>
#include <vector>
#include "WorkerException.h"
#include "Text.h"
#include "Constants.h"

class IWorker {
public:
    virtual Text& work (Text&) = 0;
    virtual void setArgs(const std::list<std::string>& block_data) = 0;
};
#endif //WORKFLOW_WORKER_H
