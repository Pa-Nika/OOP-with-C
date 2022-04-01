package com.Main;

public class RecordWord {
    private String name_of_operation;
    private String first_arg;
    private String second_arg;

    public String getNameOfOperation () {
        return name_of_operation;
    }

    public String getFirstArg () {
        return first_arg;
    }

    public String getSecondArg () {
        return second_arg;
    }

    public void setFirstArg (String first_arg) {
        this.first_arg = first_arg;
    }

    public void setSecondArg(String second_arg) {
        this.second_arg = second_arg;
    }

    public void setNameOfOperation (String name_of_operation) {
        this.name_of_operation = name_of_operation;
    }
}
