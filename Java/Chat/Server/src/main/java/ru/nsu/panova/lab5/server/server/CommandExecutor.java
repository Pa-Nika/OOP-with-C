package ru.nsu.panova.lab5.server.server;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.server.server.Exeption.FactoryExceptions;
import ru.nsu.panova.lab5.server.server.Command.*;

import java.text.CompactNumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    private final FactoryServerCommand factoryCommand = new FactoryServerCommand();
    private СommunicatorForClients communicator;

    public CommandExecutor() {
        try {
            factoryCommand.configurateFactory();
        } catch (FactoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void setCommunicator(СommunicatorForClients communicator) {
        this.communicator = communicator;
    }

    public void clientConnect(Login login) {
        Answer answer = new Answer();
        answer.setError(false);
        communicator.sendSpecificClient(answer);

        communicator.setUserName(login.getUserName());
        UserLogin userLogin = new UserLogin();
        userLogin.setUserLogin(login.getUserName());
        communicator.sendAll(userLogin);
    }

    public void clientLogout(Logout logout) {
        communicator.setActiveFalse();
        Server.delMember(communicator);

        UserLogout userLogout = new UserLogout();
        userLogout.setUserLogout(logout.getUserName());
        communicator.sendEveryoneExceptMyself(userLogout);
    }

    public void jsonAdapter(String json) {
        Gson gson = new Gson();
        factoryCommand.getCommand(gson.fromJson(json, CommandReader.class).getTypeCommand()).runCommand(this, json);
    }

    public void otherClientConnect(UserLogin userLogin) {
        communicator.sendAll(userLogin);
    }

    public void addMassage(Message msg) {
        Answer answer = new Answer();
        answer.setError(false);
        communicator.sendSpecificClient(answer);

        communicator.sendAll(msg);


        ////////////////
        Server.addMessage(msg);



    }

//    //TODO: aaaaaaaaaaaaaaaaaaaaaaaaaaa
//    public void fillServerMessageList() {
//        String firstMessages = String.valueOf(Server.messageList);
//        if (firstMessages.length() != 10) {
//
//        }
//
//
//    }

    public void sendFirstMessages() {
        List<Message> messages = new ArrayList<>();
        BufferMessages bufferMessages = new BufferMessages();
        for (Message msg : Server.messageList) {
            messages.add(msg);
        }

        bufferMessages.setBufferMessages(messages);
        communicator.sendSpecificClient(bufferMessages);
    }

    public void otherClientDisconnect(UserLogout userLogout) {
        communicator.sendEveryoneExceptMyself(userLogout);
    }

    public void sendListUsers() {
        ListUsers listUsers = new ListUsers();
        List<String> listsName = new ArrayList<>();
        for (СommunicatorForClients vr : Server.serverList) {
            listsName.add(vr.getUserName());
        }
        listUsers.setListUsers(listsName);
        communicator.sendSpecificClient(listUsers);
    }



    public void sendListMessages() {
        BufferMessages bufferMessages = new BufferMessages();
        List<String> listMessages = new ArrayList<>();

    }

}
