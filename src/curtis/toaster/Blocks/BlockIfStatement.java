package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockIfStatement extends Block {
    private String operator;
    private String expectedValue;
    private Variable variable;

    public BlockIfStatement() {
        super(0);
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public Variable getVariableToCheck() {
        return variable;
    }

    public void setVariableToCheck(Variable variableToCheck) {
        this.variable = variableToCheck;
    }

    public void randomize() {
        Random gen = new Random();
        int i = gen.nextInt(getVariables().size());
        variable = getVariables().get(i);

        int op = gen.nextInt(5);
        switch (op) {
            case 0:
                operator = "==";
                break;
            case 1:
                operator = ">";
                break;
            case 2:
                operator = "<";
                break;
            case 3:
                operator = "<=";
                break;
            case 4:
                operator = ">=";
                break;
        }
        if ( variable.getType() == Type.Type_boolean ) {
            operator = gen.nextBoolean() ? "" : "!";
        }

        expectedValue = gen.nextInt(100) + "";
    }

    @Override
    public String toString() {
        String line = "";
        if ( variable.getType() == Type.Type_boolean ) {
            line = tab() + "if(" +operator+ variable + ") { \n";
        }
        else if ( variable.getType() == Type.Type_string ) {
            Random gen = new Random();
            int i = gen.nextInt(getVariables().size());
            Variable compare = getVariables().get(i);
            if ( compare.getType() == Type.Type_string ) {
                line += tab() + "if( " + compare + ".equals(" + variable + ") ) { \n";
            }
            else if ( compare.getType() == Type.Type_object ) {
                line += tab() + "if( " + variable + ".equals(" + compare + ".toString() ) ) { \n";
            }
            else {
                compare = new Variable(Type.Type_string,NameGenerator.getRandomVariableName(),"");
                line += tab() + "if( " + variable + ".equals(\"" + compare.getName() + "\" ) ) { \n";
            }
        }
        else {
            line = tab() + "if(" + variable + " " + operator + " " + expectedValue + ") { \n";
        }

        // prints children
        if ( getChildren().size() > 0 ) {
            for ( int i = 0 ; i < getChildren().size() ; i++ ) {
                line = line + getChildren().get(i).toString() + "\n";
            }
        }
        line = line + tab() + "}";

        return line;
    }
}
