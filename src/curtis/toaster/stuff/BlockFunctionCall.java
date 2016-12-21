package curtis.toaster.stuff;

import java.util.ArrayList;

/**
 * Created by Badtoasters on 12/20/2016.
 */
public class BlockFunctionCall extends Block {
    private Type callingObject;
    private String function;
    private ArrayList<Object> objects;

    public BlockFunctionCall(int layer) {
        super(layer);
    }

    @Override
    public void randomize() {
        // selects random object to be call

    }

    @Override
    public String toString() {
        return null;
    }
}
