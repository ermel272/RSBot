package EzSmelt.tasks.banking;

import org.powerbot.script.rt4.ClientContext;

public class BankIronTask extends BankTask {

    // Element IDs
    private final static int IRON_BAR_ID = 2351;
    private final static int IRON_ID = 440;

    public BankIronTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public int getBarId() {
        return IRON_BAR_ID;
    }

    @Override
    public boolean withdraw() {
        return ctx.bank.withdraw(IRON_ID, 28);
    }
}
