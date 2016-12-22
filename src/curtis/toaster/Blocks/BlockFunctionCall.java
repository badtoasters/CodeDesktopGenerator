package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockFunctionCall extends Block {
    private Object callingObject;
    private String function;

    public BlockFunctionCall(int layer) {
        super(layer);
    }

    @Override
    public void randomize() {
        // selects random object
        Random gen = new Random();
        int i = gen.nextInt(getVariables().size());
        setCallingObject(getVariables().get(i));

        // Selects random function name
        setFunction(NameGenerator.getRandomMethodName());
    }

    public String getCallingObject() {
        return callingObject;
    }

    public void setCallingObject(Object callingObject) {
        this.callingObject = callingObject;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return tab() + callingObject + "." + function + "();";
    }
}
