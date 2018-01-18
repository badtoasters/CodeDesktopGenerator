package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockSetValue extends Block {
    private Variable variable;
    private String operator = "";
    private String value = "";

    public BlockSetValue() {
        super(0);
    }

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
            if ( gen.nextInt(10) > 7 &&
                    (variable.getType() == Type.Type_double || variable.getType() == Type.Type_int)) {
                // randomly generate a constant
                value = gen.nextInt(100) + "";
            }
            // otherwise set to an existing variable
            else {
                if ( variable.getType() == Type.Type_int || variable.getType() == Type.Type_double ) {
                    // sets testValue to simply the variable's name
                    int j = gen.nextInt(getVariables().size());
                    if ( getVariables().get(j).getType() == variable.getType() &&
                            getVariables().get(j).getName() != variable.getName()) {
                        value = getVariables().get(j) + "";
                    } else {
                        // try again
                        randomize();
                    }
                }
                else if ( variable.getType() == Type.Type_boolean ) {
                    value = gen.nextBoolean() ? "true":"false";
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
        String line = "";
        if ( variable.getType().equals(Type.Type_object)) {
            line += tab() + variable.getName() + " = " + NameGenerator.getRandomMethodName()+ "(" + value + ");";
        }
        else if ( variable.getType().equals(Type.Type_string)) {
            line += tab() + variable.getName() + " = " + variable.getName() + " + \"" + value + "\";";
        }
        else if ( variable.getType().equals(Type.Type_boolean)) {
            line += tab() + variable.getName() + " = " + value + ";";
        }
        else {
            line += tab() + variable.getName() + " " + operator + " " + value + ";";
        }

        return line;
    }
}
