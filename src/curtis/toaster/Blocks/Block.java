package curtis.toaster.Blocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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

    private void removeChild(Block block) {
        children.remove(block);
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
            for ( Object varObj : childOccurrence.keySet().toArray() ) {
                Variable var = (Variable)varObj;
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
        // depends on what type of block it is
        Variable toAdd = null;
        if ( this instanceof BlockDeceleration ) {
            BlockDeceleration block = (BlockDeceleration) this;
            toAdd = block.getVariableToCreate();
        } else if ( this instanceof BlockForLoop ) {
            BlockForLoop block = (BlockForLoop) this;
            toAdd = block.getLoopingVariable();
        } else if ( this instanceof BlockFunctionCall ) {
            BlockFunctionCall block = (BlockFunctionCall) this;
            toAdd = block.getCallingVariable();
        } else if ( this instanceof BlockIfStatement ) {
            BlockIfStatement block = (BlockIfStatement) this;
            toAdd = block.getVariableToCheck();
        } else if ( this instanceof BlockSetValue ) {
            BlockSetValue block = (BlockSetValue) this;
            toAdd = block.getVariable();
        }

        // increments variable amount
        if ( toAdd != null ) {
            // combines the occurrences
            int number = 1;
            if ( numOccurrence.containsKey(toAdd) ) {
                number += numOccurrence.get(toAdd);
                numOccurrence.put( toAdd, number );
            }
            // otherwise grab the occurrences of the child
            else {
                numOccurrence.put( toAdd, number );
            }
        }

        return numOccurrence;
    }

    /**
     * this trims off some of the useless blocks in the code block
     */
    public void trimDown() {
        HashMap<Variable,Integer> occurrences = findOccurrence();

        Iterator<Block> iter = children.iterator();
        ArrayList<Block> toRemove = new ArrayList<>();

        while ( iter.hasNext() ) {
            Block block = iter.next();
            if ( block instanceof BlockDeceleration ) {
                BlockDeceleration blockDeceleration = (BlockDeceleration) block;
                // finds occurrences of that variable
                int num = occurrences.get( blockDeceleration.getVariableToCreate() );
                if ( num <= 1 ) {
                    toRemove.add(block);
                }
            }
            else if ( block instanceof  BlockIfStatement || block instanceof BlockForLoop ) {
                // recursive trim of all sub blocks
                block.trimDown();
                // removes any sub blocks that have no children after trim
                if ( block.getChildren().size() == 0 ) {
                    this.children.remove(block);
                }

            }
        }

        children.removeAll(toRemove);

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