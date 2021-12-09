#ifndef WORKFLOW_GREPFILE_H
#define WORKFLOW_GREPFILE_H
#include "IWorker.h"

class GrepFile : public IWorker {
private:
    std::vector<std::string> data;
    std::string word;

public:
    void setArgs (const std::list<std::string>& block_data) override;
    std::list<std::string>& work(std::list<std::string>&) override;
};


#endif //WORKFLOW_GREPFILE_H
