package curtis.toaster.Blocks;

import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockSetValue extends Block {
    private Variable variable;
    private String operator = "";
    private String value = "";

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BlockSetValue(int layer) {
        super(layer);
    }

    public void randomize() {
        Random gen = new Random();
        int i = gen.nextInt(getVariables().size());
        variable = getVariables().get(i);
        if ( getVariables().size() > 0 ) {
            if ( gen.nextInt(10) > 7 ) {
                // randomly generate a constant
                value = gen.nextInt(100) + "";
            }
            // otherwise grab an existing variable
            else {
                if ( variable.getType() == Type.Type_int || variable.getType() == Type.Type_double ) {
                    // sets testValue to simply the variable's name
                    int j = gen.nextInt(getVariables().size());
                    value = getVariables().get(j)+"";
                }
                else {
                    // if the variable was of a bad type
                    // randomly generate a constant
                    value = gen.nextInt(100) + "";
                }
            }
        } else {
            Variable newVar = new Variable(Type.Type_int,"a",gen.nextInt(100)+"");
            variable = newVar;
            value = gen.nextInt(100)+"";
        }

        int op = gen.nextInt(3);
        switch (op) {
            case 0:
                operator = "=";
                break;
            case 1:
                operator = "-=";
                break;
            case 2:
                operator = "+=";
                break;
        }
    }

    @Override
    public String toString() {
        String tab = "";
        for ( int t = 0 ; t < getLayer() ; t ++ ) {
            tab += "\t";
        }

        String line = tab + variable.getName() + " " + operator + " " + value + ";";

        return line;
    }
}
