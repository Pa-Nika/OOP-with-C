#ifndef WORKFLOW_TEXT_H
#define WORKFLOW_TEXT_H
#include <iostream>
#include <list>
#include <vector>

class Text {
private:
    bool is_visit = false;
    std::list<std::string> answer_data;
public:
    explicit Text(std::list<std::string>& data) { answer_data = data; }
    std::list<std::string> getText () { return answer_data; }
    void setText (std::list<std::string>& my_text) { answer_data = my_text; }
    [[nodiscard]] bool getStatus () const { return is_visit; }
    void setStatus (bool my_status) { is_visit = my_status; }
};


#endif //WORKFLOW_TEXT_H
