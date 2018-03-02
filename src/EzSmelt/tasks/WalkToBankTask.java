package EzSmelt.tasks;

import EzSmelt.Task;
import EzSmelt.Walker;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WalkToBankTask extends Task {

    // Paths
    public static final Tile[] PATH_TO_BANK = {new Tile(2975, 3369, 0), new Tile(2978, 3373, 0), new Tile(2983, 3373, 0), new Tile(2988, 3372, 0), new Tile(2992, 3369, 0), new Tile(2996, 3365, 0), new Tile(3001, 3363, 0), new Tile(3006, 3363, 0), new Tile(3011, 3360, 0), new Tile(3013, 3355, 0)};
    private final Walker walker = new Walker(ctx);

    // Element IDs
    private final static int COPPER_ID = 436;
    private final static int TIN_ID = 438;

    public WalkToBankTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(COPPER_ID, TIN_ID).count() <= 1;
    }

    @Override
    public void execute() {
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL)
                || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {

            walker.walkPath(PATH_TO_BANK);
        }
    }
}
