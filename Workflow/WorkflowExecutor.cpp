#include "WorkflowExecutor.h"

WorkflowExecutor::WorkflowExecutor(const std::string &file_name) {
    this->file_name = file_name;
}

std::list<std::string> WorkflowExecutor::findBlockList(const std::list<std::pair<int, std::list<std::string>>> &data_workflow, int ind) {
    for (const auto & it : data_workflow) {
        if (it.first == ind)
            return it.second;
    }

    throw WorkflowException("Can't find index of block");
}

void WorkflowExecutor::fillOrderWorker(const std::string &block_name, const std::list<std::string>& block_data) {
    if (block_name == "readfile") {
        auto factory = std::make_unique<ReadFileFactory>();
        auto block = factory->createWorker();
        block->setArgs(block_data);
        order_worker.push_back(move(block));
    }

    if (block_name == "writefile") {
        auto factory = std::make_unique<WriteFileFactory>();
        auto block = factory->createWorker();
        block->setArgs(block_data);
        order_worker.push_back(move(block));
    }

    if (block_name == "grep") {
        auto factory = std::make_unique<GrepFileFactory>();
        auto block = factory->createWorker();
        block->setArgs(block_data);
        order_worker.push_back(move(block));
    }

    if (block_name == "sort") {
        auto factory = std::make_unique<SortFileFactory>();
        auto block = factory->createWorker();
        block->setArgs(block_data);
        order_worker.push_back(move(block));
    }

    if (block_name == "replace") {
        auto factory = std::make_unique<ReplaceFileFactory>();
        auto block = factory->createWorker();
        block->setArgs(block_data);
        order_worker.push_back(move(block));
    }

    if (block_name == "dump") {
        auto factory = std::make_unique<DumpFileFactory>();
        auto block = factory->createWorker();
        block->setArgs(block_data);
        order_worker.push_back(move(block));
    }
}

void WorkflowExecutor::callParser() {
    ParsConfigFile pars_file;
    pars_file.setFileName(file_name);
    pars_file.readFile();

    std::list <int> order_of_blocks = pars_file.getOrder();
    std::list <std::pair <int, std::list <std::string>>> data_workflow = pars_file.getWorkflow();

    data_workflow.sort();
    std::list<std::string> block_data;
    std::string block_name;
    std::unique_ptr<IWorker> block_now;

    for (int & order_of_this_block : order_of_blocks) {
        //get a list of block data that needs to be executed now
        block_data = findBlockList(data_workflow, order_of_this_block);
        //find the name of the block that needs to be implemented from the list
        block_name = *block_data.begin();
        //pass it to a function that compares all the values of the string and completes "order_worker"
        fillOrderWorker(block_name, block_data);
    }
}

void WorkflowExecutor::callBlocks(std::list<std::string>& answer_data) {
//    for (auto& it : order_worker) {
//        answer_data = it->work(answer_data);
//    }

    Text my_text(answer_data);
    for (auto& it : order_worker) {
        my_text = it->work(my_text);
    }
}

