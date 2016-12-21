package curtis.toaster.Blocks;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public enum Type {
    Type_null,Type_int,Type_string,Type_boolean,Type_double,Type_object;

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
                return "object";
        }
    }
}
