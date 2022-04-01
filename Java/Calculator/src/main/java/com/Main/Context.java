package com.Main;

import Exception.ArgCountException;
import Exception.NotDefineVar;
import Exception.IncorrectDefineRecord;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Context {
    private final Stack<Double> stack;
    private final HashMap<String, Double> define;
    private static final Logger log = Logger.getLogger(Context.class.getName());

    public Context () {
        stack = new Stack<>();
        define = new HashMap<>();
    }

    public HashMap<String, Double> getDefine() {
        return define;
    }

    public Stack<Double> getStack () {
        return stack;
    }

    public void setDefineValue(String key, String value) throws ArgCountException, IncorrectDefineRecord {
        if (key == null || value == null) {
            log.log(Level.SEVERE, "Exception: incorrect number of arguments for the operation");
            throw new ArgCountException();
        }

        double tmp = 0.0;
        try {
            tmp = Double.parseDouble(value);
        }catch (NumberFormatException e) {
            log.log(Level.SEVERE, "Exception: incorrect record of DEFINE operation");
            throw new IncorrectDefineRecord();
        }

        define.put(key,tmp);
    }

    public void setStackNum (String num) throws ArgCountException, NotDefineVar {
        Double tmp = getArg(num);
        stack.push(tmp);
    }

    private Double getArg (String str) throws ArgCountException, NotDefineVar {
        double tmp = 0;

        if (str == null) {
            log.log(Level.SEVERE, "Exception: incorrect number of arguments for the operation");
            throw new ArgCountException();
        }
        else {
            try {
                tmp = Double.parseDouble(str);

            } catch (NumberFormatException e) {
                if (!define.containsKey(str)) {
                    log.log(Level.SEVERE, "Exception: variable is not defined");
                    throw new NotDefineVar();
                }

                tmp = define.get(str);
            }
        }

        return tmp;
    }
}
