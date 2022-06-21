package ru.nsu.panova.lab5.client.client.observer;

public interface ObserverChat{
    void updateChat(String msg);
    void updateMember(String stringMembers);
    void setMember(String stringMembers);
}
