package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockFunctionCall extends Block {
    private Variable callingVariable;
    private String function;
    private boolean newVariable = false;

    public BlockFunctionCall(int layer) {
        super(layer);
    }

    @Override
    public void randomize() {
        // selects random object
        Random gen = new Random();
        int i = gen.nextInt(getVariables().size());
        setCallingVariable(getVariables().get(i));
        newVariable = false;

        // Selects random function name
        setFunction(NameGenerator.getRandomMethodName());
    }

    public Variable getCallingVariable() {
        return callingVariable;
    }

    public void setCallingVariable(Variable callingVariable) {
        this.callingVariable = callingVariable;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        String beginning = "";

        if ( callingVariable.getType() == Type.Type_object ) {
            beginning += callingVariable.getName() + ".";
        } else {
            beginning += callingVariable.getName() + " = ";
        }
        return tab() + beginning + function + "();";
    }
}
