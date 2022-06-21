package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command;

import ru.nsu.panova.lab5.client.client.Constants;
import ru.nsu.panova.lab5.client.client.exeption.FactoryExceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactoryClientCommand {
    private final Map<String, CommandInterface> commandMap = new HashMap<>();

    public void configureFabric() throws FactoryExceptions {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FABRIC_CONFIGURATION_FILE_NAME))){
            Pattern pattern = Pattern.compile(Constants.REGEX_FOR_CONFIGURATION_FABRIC);
            String str = reader.readLine();
            while (str != null) {
                Matcher matcher = pattern.matcher(str);
                if (matcher.find()) {
                    String workerKey = str.substring(matcher.start(), matcher.end());
                    if (matcher.find()) {
                        Class<?> executor = Class.forName(str.substring(matcher.start(), matcher.end()));
                        CommandInterface command = (CommandInterface) executor.getDeclaredConstructor().newInstance();

                        commandMap.put(workerKey, command);
                        str = reader.readLine();
                    } else {
                        throw (new FactoryExceptions(Constants.EXCEPTION_FABRIC_CONFIGURATION_FILE));
                    }
                } else {
                    throw (new FactoryExceptions(Constants.EXCEPTION_FABRIC_CONFIGURATION_FILE));
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw (new FactoryExceptions(Constants.EXCEPTION_FABRIC_CONFIGURATION_FILE));
        }
    }

    public CommandInterface getCommand(String strCommand) {
        return commandMap.get(strCommand);
    }
}

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//public class FabricClientCommand {
//    private final Map<String, CommandInterface> commandMap;
//    private final InputStream fileFactory;
//    private final Properties properties;
//    private final ArrayList<String> allCommands;
//
//    public FabricClientCommand() {
//        commandMap = new HashMap<>();
//        properties = new Properties();
//        allCommands = new ArrayList<>();
//
//        fileFactory = FabricClientCommand.class.getClassLoader().getResourceAsStream("factoryClient.properties");
//    }
//
//    private String FillProperty (Properties properties, String key){
//        String value = properties.getProperty(key);
//        return value;
//    }
//
//    public void configureFabric() {
//        try {
//            properties.load(fileFactory);
//
//            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/ru/nsu/panova/lab5/chat/client/allCommands"));
//
//            String str = reader.readLine();
//
//            while (str != null) {
//                allCommands.add(str);
//                str = reader.readLine();
//            }
//
//            for (String nameCommand : allCommands) {
//                String propertyName = FillProperty(properties, nameCommand);
//                CommandInterface command = (CommandInterface) Class.forName(propertyName).newInstance();
//                commandMap.put(nameCommand, command);
//                System.out.println(" AAA  " + nameCommand);
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public CommandInterface getCommand(String nameCommand) {
//        return commandMap.get(nameCommand);
//    }
//
//}

