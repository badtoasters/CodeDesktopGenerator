package curtis.toaster.Blocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public abstract class Block {
    private ArrayList<Block> children = new ArrayList<>();
    private ArrayList<Variable> variables = new ArrayList<>();
    private int layer = 0;

    public Block( int layer ) {
        this.layer = layer;
    }

    public ArrayList<Block> getChildren() {
        return children;
    }

    public ArrayList<Variable> getVariables() {
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

    public void setVariable(ArrayList<Variable> vars) {
        variables = vars;
    }

    public void addVariable(Variable var) {
        variables.add(var);
    }

    public void addVariable(ArrayList<Variable> vars) {
        variables.addAll((Collection<? extends Variable>) vars.clone());
    }

    public void setLayer( int layer ) {
        this.layer = layer;
    }

    public abstract void randomize();

    /**
     * this method finalizes the block and finds the most common variable
     */
    public HashMap<Variable,Integer> findOccurrence() {
        // holds the numOccurrence for each of the children variables
        HashMap<Variable,Integer> numOccurrence = new HashMap<Variable, Integer>();

        // adds the variables in the children of this block
        for ( Block block : getChildren() ) {
            HashMap<Variable,Integer> childOccurrence = block.findOccurrence();
            for ( Variable var : childOccurrence.keySet() ) {
                // increments the number of occurrences
                int number = childOccurrence.get(var);
                // combines the occurrences
                if ( numOccurrence.containsKey(var) ) {
                    number += numOccurrence.get(var);
                    numOccurrence.put( var, number );
                }
                // otherwise grab the occurrences of the child
                else {
                    numOccurrence.put( var, number );
                }
            }
        }

        // finds the occurrences of variable this Block contains
        for ( Variable var : getVariables() ) {
            int number = 1;
            if ( numOccurrence.containsKey(var) ) {
                number += numOccurrence.get(var);
                numOccurrence.put( var, number );
            }
            else {
                numOccurrence.put( var, number );
            }
        }

        return numOccurrence;
    }

    /**
     *
     * @return the correct tab length for the line
     */
    public String tab() {
        String tab = "";
        for ( int t = 0 ; t < getLayer() ; t ++ ) {
            tab += "\t";
        }
        return tab;
    }

    @Override
    public abstract String toString();
}