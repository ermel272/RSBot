package EzSmelt.tasks.banking;

import org.powerbot.script.rt4.ClientContext;


public class BankSteelTask extends BankTask {

    // Element IDs
    private final static int STEEL_BAR_ID = 2353;
    private final static int IRON_ID = 440;
    private final static int COAL_ID = 453;

    public BankSteelTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public int getBarId() {
        return STEEL_BAR_ID;
    }

    @Override
    public boolean withdraw() {
        return ctx.bank.withdraw(IRON_ID, 9) && ctx.bank.withdraw(COAL_ID, 18);
    }
}
