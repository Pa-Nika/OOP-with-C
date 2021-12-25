#ifndef WORKFLOW_SORTFILE_H
#define WORKFLOW_SORTFILE_H
#include "IWorker.h"
#include "Constants.h"

class SortFile : public IWorker {
private:
    std::vector<std::string> data;

    class Comparator {
    public:
        bool operator()(std::string &a, std::string &b) { return a < b; }
    };

public:
    void setArgs (const std::list<std::string>& block_data) override;
    Text& work (Text&) override;
};

#endif //WORKFLOW_SORTFILE_H
