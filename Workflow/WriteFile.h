#ifndef WORKFLOW_WRITEFILE_H
#define WORKFLOW_WRITEFILE_H
#include "IWorker.h"

class WriteFile : public  IWorker{
private:
    std::vector<std::string> data;
    std::string file_name;
    std::ofstream fout;

public:
    void setArgs (const std::list<std::string>& block_data) override;
    std::list<std::string>& work (std::list<std::string>&) override;
    ~WriteFile();
};


#endif //WORKFLOW_WRITEFILE_H
