#ifndef WORKFLOW_BLOCKSFACTORY_H
#define WORKFLOW_BLOCKSFACTORY_H
#include "ReadFile.h"
#include "WriteFile.h"
#include "GrepFile.h"
#include "SortFile.h"
#include "ReplaceFile.h"
#include "DumpFile.h"
#include "IFactory.h"

class ReadFileFactory: public IFactory {
public:
    std::unique_ptr<IWorker> createWorker() override { return std::make_unique<ReadFile>(); }
};

class WriteFileFactory: public IFactory {
public:
    std::unique_ptr<IWorker> createWorker() override { return std::make_unique<WriteFile>(); }
};

class GrepFileFactory: public IFactory {
public:
    std::unique_ptr<IWorker> createWorker() override { return std::make_unique<GrepFile>(); }
};

class SortFileFactory: public IFactory {
public:
    std::unique_ptr<IWorker> createWorker() override { return std::make_unique<SortFile>(); }
};

class ReplaceFileFactory: public IFactory {
public:
    std::unique_ptr<IWorker> createWorker() override { return std::make_unique<ReplaceFile>(); }
};

class DumpFileFactory: public IFactory {
public:
    std::unique_ptr<IWorker> createWorker() override { return std::make_unique<DumpFile>(); }
};

#endif //WORKFLOW_BLOCKSFACTORY_H
