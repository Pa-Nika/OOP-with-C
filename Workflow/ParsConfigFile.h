#ifndef WORKFLOW_PARSCONFIGFILE_H
#define WORKFLOW_PARSCONFIGFILE_H
#include <iostream>
#include <fstream>
#include <vector>
#include <list>
#include <cstring>
#include "WorkflowException.h"
#include "Constants.h"

class ParsConfigFile {
private:
    std::string file_name;
    std::ifstream fin;
    std::list <int> order_of_block; //block execution order
    std::list <std::pair <int, std::list <std::string> > > data_workflow;

    bool breakIntoPieces (std::string &line);
    void findOrder (std::string &line);
public:
    ~ParsConfigFile();
    void setFileName (std::string &name) { file_name = name; }
    auto getWorkflow () { return data_workflow; }
    std::list <int> getOrder () { return order_of_block; }

    void readFile ();
};

#endif //WORKFLOW_PARSCONFIGFILE_H
