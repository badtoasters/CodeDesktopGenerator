package curtis.toaster.Blocks;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public abstract class Block {
    private ArrayList<Block> children = new ArrayList<>();
    private ArrayList<Object> variables = new ArrayList<>();
    private int layer = 0;

    public Block( int layer ) {
        this.layer = layer;
    }

    public ArrayList<Block> getChildren() {
        return children;
    }

    public ArrayList<Object> getVariables() {
        return variables;
    }

    public int getLayer() {
        return layer;
    }

    public void addChildren(Block child) {
        child.addVariable(this.getVariables());
        child.setLayer(getLayer() + 1);
        children.add(child);
    }

    public void addChildren(BlockDeceleration child) {
        child.addVariable(this.getVariables());
        child.setLayer(getLayer() + 1);
        children.add(child);
        if( child.getVariableToCreate() != null ) {
            this.addVariable(child.getVariableToCreate());
        }
    }

    public void setVariable(ArrayList<Object> vars) {
        variables = vars;
    }

    public void addVariable(Object var) {
        variables.add(var);
    }

    public void addVariable(ArrayList<Object> vars) {
        variables.addAll((Collection<? extends Object>) vars.clone());
    }

    public void setLayer( int layer ) {
        this.layer = layer;
    }

    public abstract void randomize();

    @Override
    public abstract String toString();
}