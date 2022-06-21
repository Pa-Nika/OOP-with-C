package ru.nsu.panova.lab5.server.server.Command;


import ru.nsu.panova.lab5.server.server.Constants;
import ru.nsu.panova.lab5.server.server.Exeption.FabricExceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactoryServerCommand {
    private Map<String, CommandInterface> commandMap = new HashMap<>();

    public void configurateFabric() throws FabricExceptions {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FABRIC_CONFIGURATION_FILE_NAME))){
            Pattern pattern = Pattern.compile(Constants.REGEX_FOR_CONFIGURATION_FABRIC);
            String str = reader.readLine();
            while (str != null) {
                Matcher matcher = pattern.matcher(str);
                if (matcher.find()) {
                    String workerKey = str.substring(matcher.start(), matcher.end());
                    if (matcher.find()) {
                        Class<?> executor = Class.forName(str.substring(matcher.start(), matcher.end()));
                        CommandInterface worker = (CommandInterface) executor.getDeclaredConstructor().newInstance();

                        commandMap.put(workerKey, worker);
                        str = reader.readLine();
                    } else {
                        throw (new FabricExceptions(Constants.EXCEPTION_FABRIC_CONFIGURATION_FILE));
                    }
                } else {
                    throw (new FabricExceptions(Constants.EXCEPTION_FABRIC_CONFIGURATION_FILE));
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw (new FabricExceptions(Constants.EXCEPTION_FABRIC_CONFIGURATION_FILE));
        }
    }

    public CommandInterface getCommand(String strCommand) {
        return commandMap.get(strCommand);
    }
}
