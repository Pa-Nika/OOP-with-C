package ru.nsu.panova.lab5.client.client.mainWindow;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.client.client.Client;
import ru.nsu.panova.lab5.client.client.Constants;
import ru.nsu.panova.lab5.client.client.exeption.FactoryExceptions;
import ru.nsu.panova.lab5.client.client.entranceWindow.LoaderEntranceWindow;
import ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.ReadMsg;
import ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.WriteMsg;
import ru.nsu.panova.lab5.client.client.observer.ObservableChat;
import ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command.*;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.javafx.application.PlatformImpl.exit;

public class ModelMainWindow extends ObservableChat {
    private Socket clientSocket;
    private WriteMsg writeMsg;
    private ReadMsg readMsg;
    private final FactoryClientCommand factoryCommand = new FactoryClientCommand();
    private String nameUser;
    private List<String> listAllUsers = new ArrayList<>();
    private List<Message> listFirstMessages = new ArrayList<>();

    public void setClientSocketAndUserName(Socket clientSocket, String nameUser) {
        this.clientSocket = clientSocket;
        this.nameUser = nameUser;
        writeMsg = new WriteMsg(clientSocket, this);
        try {
            factoryCommand.configureFactory();
        } catch (FactoryExceptions e) {
            System.out.println(e.getMessage());
        }
        readMsg = new ReadMsg(clientSocket, this);
        readMsg.start();

        login();
        sendRequest();
        sendMessagesRequest();
    }

    public void sendMsg(String textMsg) {
        Date date = new Date();
        Message msg = new Message(Constants.COMMAND_MASSAGE, nameUser, textMsg, date.getTime());
        writeMsg.sender(msg);
    }

    public void jsonAdapter(String json) {
        Gson gson = new Gson();
        factoryCommand.getCommand(gson.fromJson(json, CommandReader.class).getTypeCommand()).runCommand(this, json);
    }

    public void login() {
        Login login = new Login();
        login.setLogin(Constants.COMMAND_LOGIN, nameUser);
        writeMsg.sender(login);
    }

    public void sendNotificationLogout() {
        Logout logout = new Logout();
        logout.setLogout(nameUser);
        writeMsg.sender(logout);
        disconnect();
    }

    public void addAMassageToChat(Message msg) {
        Date date = new Date(msg.getTimeSend());
        String dateStr = String.valueOf(date.getDate() + "." + (date.getMonth() + 1) + "." + (date.getYear() + 1900) +
                "  " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
        notifyOfUpdateObserverChat("(" + dateStr + ") " + msg.getNameSender() + ": " + msg.getMessage());
    }

    public void addNewMemberToChat(UserLogin listUsers) {
        listAllUsers.add(listUsers.getUserName());
        notifyOfUpdateObserverMember(listUsers.getUserName());
    }

    public void delMemberToChat(UserLogout userLogout) {
        System.out.println(userLogout.getUserName());
        listAllUsers.remove(userLogout.getUserName());
        StringBuilder allMemberStr = new StringBuilder();
        for (String name : listAllUsers) {
            allMemberStr.append(name);
            allMemberStr.append("\n");
        }
        notifyOfSetObserverMember(String.valueOf(allMemberStr));
    }

    public void loadAllMemberToChat(ListUsers userLogin) {
        listAllUsers.clear();
        listAllUsers = userLogin.getNameUsers();
        StringBuilder allMemberStr = new StringBuilder();
        for (String name : listAllUsers) {
            allMemberStr.append(name).append("\n");
        }
        notifyOfSetObserverMember(String.valueOf(allMemberStr));
    }

    public void loadFirstMessagesToChat(BufferMessages messagesList) {
        listFirstMessages.clear();
        listFirstMessages = messagesList.getMessageList();
        for (Message msg : listFirstMessages) {
            addAMassageToChat(msg);
        }
    }

    public void sendRequest() {
        ListUsers listUsers = new ListUsers();
        listUsers.setTypeCommand(Constants.COMMAND_LIST_USERS);
        writeMsg.sender(listUsers);
    }

    public void sendMessagesRequest() {
        BufferMessages bufferMessages = new BufferMessages();
        bufferMessages.setTypeCommand(Constants.COMMAND_FIRST_MESSAGES);
        writeMsg.sender(bufferMessages);
    }

    public void readAnswer(Answer answer) {
        if (answer.isError())
            System.err.println(answer.getErrorMassage());
    }

    public void serverEndWork() {
        try {
            readMsg.stopRead();
            writeMsg.closeBuffer();
            readMsg.closeBuffer();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            exit();
        }
        loadEntranceWindow();
    }

    public void disconnect() {
        try {
            writeMsg.closeBuffer();
            readMsg.closeBuffer();
            readMsg.stopRead();
            readMsg.interrupt();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            exit();
        }
    }

    public void loadEntranceWindow() {
        Client.setNewLoader(new LoaderEntranceWindow());
    }
}
