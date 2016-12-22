package curtis.toaster.Blocks;

import curtis.toaster.NameGenerator;

import java.util.Random;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public enum Type {
    Type_null,Type_int,Type_string,Type_boolean,Type_double,Type_object;
    private String nameOfVarType;

    // used to get name of variable
    public String getNameOfVarType() {
        return nameOfVarType;
    }

    /*public void setName() {
        // can only check this way...for some reason
        // checks if the enum is a Type_object
        if ( this == Type_object )
            nameOfVarType = NameGenerator.getRandomVariableName();
        }
    }*/

    /**
     *
     * @return a random type of variable
     */
    public static Type random() {
        Random gen = new Random();

        switch (gen.nextInt(5)) {
            default:
            case 0:
                return Type_int;
            case 1:
                return Type_string;
            case 2:
                return Type_boolean;
            case 3:
                return Type_double;
            case 4:
                return Type_object;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            default:
            case Type_null:
                return "null";
            case Type_int:
                return "int";
            case Type_string:
                return "String";
            case Type_boolean:
                return "Boolean";
            case Type_double:
                return "double";
            case Type_object:
                return NameGenerator.getObjectName();
        }
    }
}
