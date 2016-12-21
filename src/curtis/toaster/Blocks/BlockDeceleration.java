package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockDeceleration extends Block {
    private Object variableToCreate;
    private String valueToSet = "null";

    public Object getVariableToCreate() {
        return variableToCreate;
    }

    public void setVariableToCreate(Object variable) {
        this.variableToCreate = variable;
    }

    public String getValueToSet() {
        return valueToSet;
    }

    public void setValueToSet(String valueToSet) {
        this.valueToSet = valueToSet;
    }

    public BlockDeceleration(int layer) {
        super(layer);
    }

    @Override
    public void randomize() {
        Random gen = new Random();
        setVariableToCreate( new Object(Type.Type_int, NameGenerator.getRandomVariableName() ,gen.nextInt(100)+"") );

        if (variableToCreate.getType() == Type.Type_int) {
            valueToSet = gen.nextInt(100) + "";
        } else if (variableToCreate.getType() == Type.Type_boolean) {
            valueToSet = gen.nextBoolean() + "";
        } else if (variableToCreate.getType() == Type.Type_double) {
            valueToSet = gen.nextDouble() + "";
        } else if (variableToCreate.getType() == Type.Type_null) {
            valueToSet = null;
        } else if (variableToCreate.getType() == Type.Type_object) {
            valueToSet = null;
        }

    }

    @Override
    public String toString() {
        String line = "";

        String tab = "";
        for ( int t = 0 ; t < getLayer() ; t ++ ) {
            tab += "\t";
        }

        line = tab + getVariableToCreate().getType() + " " + getVariableToCreate().getName() + " = " + getValueToSet();

        return line;
    }
}
