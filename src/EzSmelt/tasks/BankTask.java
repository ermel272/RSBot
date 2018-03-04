package EzSmelt.tasks;

import EzSmelt.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class BankTask extends Task {

    private final static int BRONZE_BAR_ID = 2349;
    private final static int COPPER_ID = 436;
    private final static int TIN_ID = 438;

    public BankTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return needsToBank() && closeToBank();
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            if (ctx.bank.depositInventory()) {
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.inventory.select().count() == 0;
                    }
                }, 250, 16);
            }

            if (!ctx.bank.withdraw(COPPER_ID, 14) || !ctx.bank.withdraw(TIN_ID, 14)) {
                ctx.controller.stop();
            }

        } else {
            if (ctx.bank.inViewport()) {
                if (ctx.bank.open()) {
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.bank.opened();
                        }
                    }, 1000, 5);
                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }
    }

    private boolean closeToBank() {
        return ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6;
    }

    private boolean needsToBank() {
        return ctx.inventory.select().id(BRONZE_BAR_ID).count() >= 1 || ctx.inventory.select().count() == 0;
    }
}
