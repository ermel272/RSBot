package EzSmelt.tasks.walking;

import org.powerbot.script.rt4.ClientContext;

public class BronzeWalkTask extends WalkTask {

    // Element IDs
    private final static int COPPER_ID = 436;
    private final static int TIN_ID = 438;

    public BronzeWalkTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean haveOre() {
        return ctx.inventory.select().id(TIN_ID).count() >= 1
                && ctx.inventory.select().id(COPPER_ID).count() >= 1;
    }

    @Override
    public boolean walkToBank() {
        return ctx.inventory.select().id(COPPER_ID, TIN_ID).count() <= 1;
    }
}
