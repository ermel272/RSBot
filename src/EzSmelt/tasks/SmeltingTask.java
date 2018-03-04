package EzSmelt.tasks;

import EzSmelt.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class SmeltingTask extends Task {

    // Animation statuses
    private final static int IDLE = -1;
    private final static int SMELTING = 899;

    // Element IDs
    private final static int COPPER_ID = 436;
    private final static int TIN_ID = 438;
    private final static int FURNACE_ID = 24009;
    private final static int SMELT_WINDOW_ID = 270;
    private final static int BRONZE_BAR_BUTTON_ID = 14;

    public SmeltingTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        if (ctx.players.local().animation() == IDLE) {
            // We could be briefly in between bars - wait a bit
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.players.local().animation() == SMELTING;
                }
            }, 500, 4);
        }

        return !(ctx.players.local().animation() == SMELTING)
                && ctx.inventory.select().id(COPPER_ID, TIN_ID).count() > 1
                && ctx.objects.select().id(FURNACE_ID).poll().tile().distanceTo(ctx.players.local()) < 4;
    }

    @Override
    public void execute() {
        GameObject furnace = ctx.objects.select().id(FURNACE_ID).poll();
        furnace.interact("Smelt");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.widgets.component(SMELT_WINDOW_ID, BRONZE_BAR_BUTTON_ID) != null;
            }
        }, 1000, 2);

        Component bronzeBarButton = ctx.widgets.component(SMELT_WINDOW_ID, BRONZE_BAR_BUTTON_ID);
        if (bronzeBarButton == null) { return; }
        bronzeBarButton.interact("Smelt");
    }
}
