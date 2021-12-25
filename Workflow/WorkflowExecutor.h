#ifndef WORKFLOW_WORKFLOWEXECUTOR_H
#define WORKFLOW_WORKFLOWEXECUTOR_H
#include <memory>
#include "ParsConfigFile.h"
#include "BlocksFactory.h"
#include "WorkflowException.h"
#include "Text.h"

class WorkflowExecutor {
private:
    std::string file_name;
    static std::list<std::string> findBlockList(const std::list <std::pair <int, std::list <std::string>>> &workflow, int ind);
    std::list<std::unique_ptr<IWorker>> order_worker;

    void fillOrderWorker(const std::string& block_name, const std::list<std::string>& block_data);

public:
    explicit WorkflowExecutor (const std::string& file_name);
    void callParser ();
    void callBlocks(std::list<std::string>& answer_data);
};

#endif //WORKFLOW_WORKFLOWEXECUTOR_H
