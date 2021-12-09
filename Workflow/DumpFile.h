#ifndef WORKFLOW_DUMPFILE_H
#define WORKFLOW_DUMPFILE_H
#include "IWorker.h"

class DumpFile : public IWorker {
private:
    std::ofstream fout;
    std::string file_name;
    std::vector<std::string> data;

public:
    void setArgs (const std::list<std::string>& block_data) override;
    std::list<std::string>& work(std::list<std::string>&) override;
    ~DumpFile();
};


#endif //WORKFLOW_DUMPFILE_H
