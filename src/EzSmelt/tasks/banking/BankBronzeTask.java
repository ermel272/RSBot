package EzSmelt.tasks.banking;

import org.powerbot.script.rt4.ClientContext;


public class BankBronzeTask extends BankTask {

    // Element IDs
    private final static int BRONZE_BAR_ID = 2349;
    private final static int COPPER_ID = 436;
    private final static int TIN_ID = 438;

    public BankBronzeTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public int getBarId() {
        return BRONZE_BAR_ID;
    }

    @Override
    public boolean withdraw() {
        return ctx.bank.withdraw(COPPER_ID, 14) && ctx.bank.withdraw(TIN_ID, 14);
    }
}
