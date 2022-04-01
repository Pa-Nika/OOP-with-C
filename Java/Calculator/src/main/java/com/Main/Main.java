package com.Main;

import java.io.IOException;
import java.util.logging.LogManager;

import Exception.MyException;

public class Main {
    public static void main(String[] args) {

        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/log4j.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }


        try {
           Creator creator;

           if (args.length == 0) {
               creator = new Creator();
               creator.work();
           }
           if (args.length == 1) {
               creator = new Creator(args[0]);
               creator.work();
           }
        }catch (MyException  e) {
            System.out.println(e.getMessage());
        }
    }
}


/*
* TODO
* Ошибка при несуществующей операции
* Иерархия исключений
* Сделать HashMap, а не обращаться каждый раз к файлу property
* */