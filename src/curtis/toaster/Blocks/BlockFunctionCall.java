package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockFunctionCall extends Block {
    private Variable callingVariable;
    private String function;
    private boolean newVariable = false;
    private ArrayList<Variable> parameterVariables = new ArrayList<>();

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

        int numParams = gen.nextInt(4);
        for ( int num = 0 ; num < numParams ; num++ ) {
            i = gen.nextInt(getVariables().size());
            Variable toAdd = getVariables().get(i);
            if ( !parameterVariables.contains( toAdd )) {
                parameterVariables.add(getVariables().get(i));
            }
        }

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
        String end = tab() + beginning + function + "(";

        for (int var = 0; var < parameterVariables.size() ; var ++ ) {
            end += " " + parameterVariables.get(var).getName() +
                    (var == parameterVariables.size() - 1 ? " ":" ,");
        }
        end += ");";
        return end;
    }
}
