package EzSmelt.tasks.smelting;

import org.powerbot.script.rt4.ClientContext;

public class IronSmeltingTask extends SmeltingTask {

    // Element IDs
    private final static int IRON_ID = 440;
    private final static int IRON_BAR_BUTTON_ID = 15;

    public IronSmeltingTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean needToSmelt() {
        return ctx.inventory.select().id(IRON_ID).count() > 0;
    }

    @Override
    public int getBarButton() {
        return IRON_BAR_BUTTON_ID;
    }


}
