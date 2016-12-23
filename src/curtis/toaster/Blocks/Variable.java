package curtis.toaster.Blocks;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class Variable {
    private Type type = null;
    private String name;
    private String value;

    public Variable(Type type , String name, String value ) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    /**
     * null variable constructor
     */
    public Variable() {
        this.type = Type.Type_null;
        this.name = "null";
        this.value = "null";
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
