package ru.nsu.panova.lab5.server.server;

public class Constants {
    public static final int SOCKET = 11111;
    public static final String FABRIC_CONFIGURATION_FILE_NAME = "Server/src/main/java/ru/nsu/panova/lab5/server/server/fileConfiguration";
    public static final String REGEX_FOR_CONFIGURATION_FABRIC = "([\\S]+)";
    public static final String EXCEPTION_FABRIC_CONFIGURATION_FILE = "incorrect configuration file for the factory\n";

    public static final String COMMAND_MASSAGE = "message";
    public static final String COMMAND_LOGIN = "login";
    public static final String COMMAND_LOGOUT = "logout";
    public static final String COMMAND_LIST_USERS = "listUsers";
    public static final String COMMAND_FIRST_MESSAGES = "bufferMessages";
    public static final String COMMAND_USER_LOGIN = "userLogin";
    public static final String COMMAND_USER_LOGOUT = "userLogout";
    public static final String COMMAND_ANSWER = "answer";
    public static final int BUFFER_SIZE = 10;
}
