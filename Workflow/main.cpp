#include "WorkflowExecutor.h"

int main(int argc, char* argv[]) {

    try {
        if (argc != 2) {
            throw WorkflowException("Bad count of args");
        }
        std::list<std::string> answer_data;
        WorkflowExecutor workflow(argv[1]);
        workflow.callParser();
        workflow.callBlocks(answer_data);
    }
    catch (WorkerException& ex) {
        std::cout << ex.what() << std::endl;
        return PROGRAM_STOP;
    }
    catch (WorkflowException& ex) {
        std::cout << ex.what() << std::endl;
        return PROGRAM_STOP;
    }

    return 0;
}
