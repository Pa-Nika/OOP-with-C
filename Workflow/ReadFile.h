#ifndef WORKFLOW_READFILE_H
#define WORKFLOW_READFILE_H
#include "IWorker.h"

class ReadFile : public IWorker {
private:
    std::string file_name;
    std::ifstream fin;
    std::vector<std::string> data;

public:
    void setArgs (const std::list<std::string>& block_data) override;
    std::list<std::string>& work(std::list<std::string>&) override;
    ~ReadFile();
};

#endif //WORKFLOW_READFILE_H
