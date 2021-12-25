#include "IWorker.h"

class WriteFile : public  IWorker{
private:
    std::vector<std::string> data;
    std::string file_name;
    std::ofstream fout;

public:
    void setArgs (const std::list<std::string>& block_data) override;
    Text& work (Text&) override;
    ~WriteFile();
};
