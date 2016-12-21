package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockForLoop extends Block {
    private Object loopingVariable;
    private String operator;
    private String testValue;

    public BlockForLoop(int layer) {
        super(layer);
    }

    public Object getLoopingVariable() {
        return loopingVariable;
    }

    public void setLoopingVariable(Object loopingVariable) {
        this.loopingVariable = loopingVariable;
    }

    public String getTestValue() {
        return testValue;
    }

    public void settestValue(String testValue) {
        this.testValue = testValue;
    }

    @Override
    public void randomize() {
        // generates the var that is looped
        Random gen = new Random();
        loopingVariable = new Object(Type.Type_int, NameGenerator.getRandomVariableName(), gen.nextInt(100)+"");

        // generates the operator for the for loop
        int op = gen.nextInt(2);
        switch (op) {
            case 0:
                operator = "<";
                break;
            case 1:
                operator = " <= ";
                break;
        }

        // generates the testValue to be compared against
        // if true then use random value
        if ( gen.nextInt(10) > 7 ) {
            testValue = gen.nextInt(100) + "";
        }
        // otherwise grab an existing variable
        else {
            int i = gen.nextInt(getVariables().size());
            Object variable = getVariables().get(i);
            if ( variable.getType() == Type.Type_int ) {
                // sets testValue to simply the variable's name
                testValue = variable.getName();
            }
            else if ( variable.getType() == Type.Type_double ) {
                // sets testValue to the cast of the variables name
                testValue = "(int)" + variable.getName();
            }
            else if ( variable.getType() == Type.Type_string ) {
                // sets testValue to the cast of the variables name
                testValue = "Integer.parseInt(" + variable.getName() + ")";
            }
        }
    }

    @Override
    public String toString() {
        String tab = "";
        for ( int t = 0 ; t < getLayer() ; t ++ ) {
            tab += "\t";
        }

        String line = tab + "for( " + loopingVariable.getType()+ " " +
                loopingVariable.getName() + " = " + 0 + " ; " +
                loopingVariable.getName() + operator + testValue + " ; " +
                loopingVariable.getName() + "++ ) {" + "\n";

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
