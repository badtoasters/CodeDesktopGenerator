package curtis.toaster;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class NameGenerator {
    private static HashMap<String,Boolean> methodNames = new HashMap<>();
    private static HashMap<String,Boolean> variableNames = new HashMap<>();

    public static void init() {
        // generate method names
        methodNames.put("foo",false);
        methodNames.put("init",false);
        methodNames.put("func",false);
        methodNames.put("calc",false);
        methodNames.put("update",false);
        methodNames.put("heuristicCostEstimate",false);
        methodNames.put("getStart",false);
        methodNames.put("findDistance",false);
        methodNames.put("writeUpdates",false);
        methodNames.put("reveal",false);
        methodNames.put("writeUpdates",false);
        methodNames.put("getElementType",false);
        methodNames.put("getEquation",false);

        // generate variable names
        variableNames.put("x",false);
        variableNames.put("y",false);
        variableNames.put("z",false);
        variableNames.put("a",false);
        variableNames.put("b",false);
        variableNames.put("c",false);
        variableNames.put("i",false);
        variableNames.put("j",false);
        variableNames.put("k",false);
        variableNames.put("temp",false);
        variableNames.put("number",false);
        variableNames.put("done",false);
        variableNames.put("iterator",false);
        variableNames.put("numerations",false);
        variableNames.put("num",false);
        variableNames.put("finish",false);
        variableNames.put("counter",false);
    }

    public static String getRandomMethodName() {
        Random gen = new Random();

        String varName = "";
        boolean done = false;
        while ( ! done ) {
            int i = gen.nextInt(methodNames.size());
            varName = (String) methodNames.keySet().toArray()[i];
            if ( !methodNames.get(varName) ) {
                methodNames.put(varName, true);
                done = true;
            }
        }

        return varName;
    }

    public static String getRandomVariableName() {
        Random gen = new Random();

        String varName = "";
        boolean done = false;
        while ( ! done ) {
            int i = gen.nextInt(variableNames.size());
            varName = (String) variableNames.keySet().toArray()[i];
            if ( !variableNames.get(varName) ) {
                variableNames.put(varName, true);
                done = true;
            }
        }

        return varName;
    }
}
