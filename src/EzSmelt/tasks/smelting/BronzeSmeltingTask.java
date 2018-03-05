package EzSmelt.tasks.smelting;

import org.powerbot.script.rt4.ClientContext;

public class BronzeSmeltingTask extends SmeltingTask {

    // Element IDs
    private final static int COPPER_ID = 436;
    private final static int TIN_ID = 438;
    private final static int BRONZE_BAR_BUTTON_ID = 14;

    public BronzeSmeltingTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean needToSmelt() {
        return ctx.inventory.select().id(COPPER_ID).count() > 0
                && ctx.inventory.select().id(TIN_ID).count() > 0;
    }

    @Override
    public int getBarButton() {
        return BRONZE_BAR_BUTTON_ID;
    }
}
