package EzSmelt.tasks.banking;

import EzSmelt.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public abstract class BankTask extends Task {

    BankTask(ClientContext ctx) {
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

            if (!withdraw()) {
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

    private boolean needsToBank() {
        return ctx.inventory.select().id(getBarId()).count() >= 1 || ctx.inventory.select().count() == 0;
    }

    private boolean closeToBank() {
        return ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6;
    }

    public abstract int getBarId();
    public abstract boolean withdraw();
}
