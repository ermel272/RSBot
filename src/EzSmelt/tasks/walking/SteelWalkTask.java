package EzSmelt.tasks.walking;

import org.powerbot.script.rt4.ClientContext;

public class SteelWalkTask extends WalkTask {

    // Element IDs
    private final static int IRON_ID = 440;
    private final static int COAL_ID = 453;

    public SteelWalkTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean haveOre() {
        return ctx.inventory.select().id(IRON_ID).count() >= 1
                && ctx.inventory.select().id(COAL_ID).count() >= 2;
    }

    @Override
    public boolean walkToBank() {
        return ctx.inventory.select().id(IRON_ID).count() < 1
                || ctx.inventory.select().id(COAL_ID).count() < 2;
    }
}
