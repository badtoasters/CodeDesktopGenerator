package curtis.toaster.stuff;

import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockIfStatement extends Block {
    private String operator;
    private String expectedValue;
    private Object variable;

    public BlockIfStatement(int layer) {
        super(layer);
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

    public Object getVariableToCheck() {
        return variable;
    }

    public void setVariableToCheck(Object variableToCheck) {
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

        expectedValue = gen.nextInt(100) + "";
    }

    @Override
    public String toString() {
        String tab = "";
        for ( int t = 0 ; t < getLayer() ; t ++ ) {
            tab += "\t";
        }

        String line = tab + "if("+variable+" "+operator+" "+expectedValue+") { \n";

        // prints children
        if ( getChildren().size() > 0 ) {
            for ( int i = 0 ; i < getChildren().size() ; i++ ) {
                line = line + getChildren().get(i).toString() + "\n";
            }
        }
        line = line + tab + "}";

        return line;
    }
}
