package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockMethod extends Block {
    private String methodName;
    // holds the parameters for the methods
    private ArrayList<Object> parameterVariables = new ArrayList<>();
    // return type
    private Type returnType;

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public BlockMethod(int layer) {
        super(layer);
    }

    @Override
    public void randomize() {
        Random gen = new Random();
        int numParams = gen.nextInt(4) + 2;
        for ( int i = 0 ; i < numParams ; i++ ) {
            Object var = new Object(Type.Type_int, NameGenerator.getRandomVariableName(), "10");
            addVariable(var);
            parameterVariables.add(var);
        }

        returnType = Type.Type_int;
    }

    @Override
    public String toString() {
        String tab = "";
        for ( int t = 0 ; t < getLayer() ; t ++ ) {
            tab = "\t";
        }

        String line = tab + "public " + returnType + " " + methodName + "(";
        for (int var = 0; var < parameterVariables.size() ; var ++ ) {
            line += " " + parameterVariables.get(var).getType() +
                    " " + parameterVariables.get(var).getName() +
                    (var == parameterVariables.size() - 1 ? " ":" ,");
        }
        line += ") {\n";

        // prints children
        if ( getChildren().size() > 0 ) {
            for ( int i = 0 ; i < getChildren().size() ; i++ ) {
                line = line + getChildren().get(i).toString() + "\n";
            }
        }

        line = line + tab + "\treturn " + parameterVariables.get(0) + ";\n";
        line = line + tab + "}";

        return line;
    }
}
