#ifndef WORKFLOW_REPLACEFILE_H
#define WORKFLOW_REPLACEFILE_H
#include "IWorker.h"

class ReplaceFile : public IWorker {
private:
    std::string word1, word2;
    std::vector<std::string> data;
public:
    void setArgs (const std::list<std::string>& block_data) override;
    Text& work (Text&) override;
};

#endif //WORKFLOW_REPLACEFILE_H
