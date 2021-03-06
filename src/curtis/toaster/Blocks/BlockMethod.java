package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockMethod extends Block {
    private String methodName;
    // holds the parameters for the methods
    private ArrayList<Variable> parameterVariables = new ArrayList<>();
    // return type
    private Variable returnVar;

    public Variable getReturnVar() {
        return returnVar;
    }

    public void setReturnVar(Variable returnVar) {
        this.returnVar = returnVar;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public BlockMethod() {
        super(0);
    }

    public void calcReturnVar() {
        HashMap<Variable,Integer> numOccurrence = this.findOccurrence();

        // finds the most common variable in the method block
        int max = 0;
        Variable maxVar = new Variable();
        for ( Object varObj : numOccurrence.keySet().toArray() ) {
            Variable var = (Variable) varObj;
            if ( numOccurrence.get(var) > max ) {
                maxVar = var;
                max = numOccurrence.get(var);
            }
        }

        // sets the return variable to this most common return variable
        returnVar = maxVar;
    }

    @Override
    public void randomize() {
        Random gen = new Random();
        int numParams = gen.nextInt(4) + 2;
        for ( int i = 0 ; i < numParams ; i++ ) {
            Variable var = new Variable(Type.random(), NameGenerator.getRandomVariableName(), "10");
            addVariable(var);
            parameterVariables.add(var);
        }
    }

    @Override
    public String toString() {
        String tab = "";
        for ( int t = 0 ; t < getLayer() ; t ++ ) {
            tab = "\t";
        }

        String line = tab + "public " + returnVar.getType() + " " + methodName + "(";
        for (int var = 0; var < parameterVariables.size() ; var ++ ) {
            line += (var>0?" ":"") + parameterVariables.get(var).getType() +
                    " " + parameterVariables.get(var).getName() +
                    (var == parameterVariables.size() - 1 ? "":",");
        }
        line += ") {\n";

        // prints children
        if ( getChildren().size() > 0 ) {
            for ( int i = 0 ; i < getChildren().size() ; i++ ) {
                line = line + getChildren().get(i).toString() + "\n";
            }
        }

        line = line + tab + "\treturn " + returnVar + ";\n";
        line = line + tab + "}";

        return line;
    }
}
