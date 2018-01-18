package curtis.toaster;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class NameGenerator {
    // each hashmap contains the name of the function and whether or not it has been used in the
    private static HashMap<String,Boolean> methodNames = new HashMap<>();
    private static HashMap<String,Boolean> variableNames = new HashMap<>();
    private static HashMap<String,Boolean> objectNames = new HashMap<>();

    // generates all the possible names
    public static void init() {

        // generate method names
        methodNames = new HashMap<>();
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
        methodNames.put("generate",false);
        methodNames.put("start",false);
        methodNames.put("cancel",false);
        methodNames.put("getBackground",false);
        methodNames.put("coolBackground",false);
        methodNames.put("flush",false);
        methodNames.put("randomize",false);
        methodNames.put("heapifyDown",false);
        methodNames.put("push",false);
        methodNames.put("empty",false);
		methodNames.put("transferToFile",false);
		methodNames.put("parseArgs",false);
		methodNames.put("connectToServer",false);
		methodNames.put("verbToString",false);
		methodNames.put("createMessage",false);
		methodNames.put("writeFile", false);
		methodNames.put("readHeader",false);
		methodNames.put("malloc",false);
		methodNames.put("printStats", false);
		methodNames.put("loadHistory", false);
		methodNames.put("saveHistory",false);
		methodNames.put("startsWith",false);
		methodNames.put("shell", false);
		methodNames.put("vectorDestroy", false);

		// generate variable names
        variableNames = new HashMap<>();
        variableNames.put("x",false);
        variableNames.put("y",false);
        variableNames.put("z",false);
        variableNames.put("a",false);
        variableNames.put("b",false);
        variableNames.put("c",false);
        variableNames.put("i",false);
        variableNames.put("j",false);
        variableNames.put("k",false);
        variableNames.put("time",false);
        variableNames.put("temp",false);
        variableNames.put("number",false);
        variableNames.put("done",false);
        variableNames.put("iterator",false);
        variableNames.put("numerations",false);
        variableNames.put("num",false);
        variableNames.put("finish",false);
        variableNames.put("counter",false);
        variableNames.put("min",false);
        variableNames.put("max",false);
        variableNames.put("counter",false);
        variableNames.put("payment",false);
        variableNames.put("timesPerYear",false);
        variableNames.put("counter",false);
        variableNames.put("alpha",false);
        variableNames.put("beta",false);
        variableNames.put("delta",false);
        variableNames.put("theta",false);
        variableNames.put("cosx",false);
        variableNames.put("cosy",false);
        variableNames.put("startTime",false);
        variableNames.put("currentIdx",false);
		variableNames.put("quit ",false);
		variableNames.put("pageDir",false);
		variableNames.put("pdEntry",false);
		variableNames.put("optval",false);

        // generate object names
        objectNames.put("Apple",false);
        objectNames.put("Block",false);
        objectNames.put("NameGenerator",false);
        objectNames.put("Candidate",false);
        objectNames.put("State",false);
        objectNames.put("Graph",false);
        objectNames.put("Plant",false);
        objectNames.put("Font",false);
        objectNames.put("Background",false);
        objectNames.put("GradientMaker",false);
        objectNames.put("Color",false);
        objectNames.put("flush",false);
        objectNames.put("PNG",false);
        objectNames.put("RBGAPixel",false);
        objectNames.put("AstarGraph",false);
        objectNames.put("KDTree",false);
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

    public static String getObjectName() {
        Random gen = new Random();

        String varName = "";
        boolean done = false;
        while ( ! done ) {
            int i = gen.nextInt(objectNames.size());
            varName = (String) objectNames.keySet().toArray()[i];
            if ( !objectNames.get(varName) ) {
                objectNames.put(varName, true);
                done = true;
            }
        }

        return varName;
    }
}
