package EzSmelt.tasks.smelting;


import org.powerbot.script.rt4.ClientContext;

public class SteelSmeltingTask extends SmeltingTask {

    // Element IDs
    private final static int IRON_ID = 440;
    private final static int COAL_ID = 453;
    private final static int STEEL_BAR_BUTTON_ID = 17;

    public SteelSmeltingTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean needToSmelt() {
        return ctx.inventory.select().id(IRON_ID).count() > 0
                && ctx.inventory.select().id(COAL_ID).count() > 1;
    }

    @Override
    public int getBarButton() {
        return STEEL_BAR_BUTTON_ID;
    }
}
