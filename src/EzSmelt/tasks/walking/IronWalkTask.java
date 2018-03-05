package EzSmelt.tasks.walking;

import org.powerbot.script.rt4.ClientContext;

public class IronWalkTask extends WalkTask {

    // Element IDs
    private final static int IRON_ID = 440;

    public IronWalkTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean haveOre() {
        return ctx.inventory.select().id(IRON_ID).count() >= 1;
    }

    @Override
    public boolean walkToBank() {
        return ctx.inventory.select().id(IRON_ID).count() < 1;
    }
}
