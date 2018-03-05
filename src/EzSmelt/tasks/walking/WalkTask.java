package EzSmelt.tasks.walking;

import EzSmelt.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public abstract class WalkTask extends Task {

    // Paths
    private static final Tile[] PATH_TO_BANK = {new Tile(2975, 3369, 0), new Tile(2978, 3373, 0), new Tile(2983, 3373, 0), new Tile(2988, 3372, 0), new Tile(2992, 3369, 0), new Tile(2996, 3365, 0), new Tile(3001, 3363, 0), new Tile(3006, 3363, 0), new Tile(3011, 3360, 0), new Tile(3013, 3355, 0)};
    private final Walker walker = new Walker(ctx);

    // Element IDs
    private final static int FURNACE_ID = 24009;

    WalkTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return walkToBank() || walkToFurnace();
    }

    @Override
    public void execute() {
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL)
                || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (walkToBank()) {
                walker.walkPath(PATH_TO_BANK);
            } else {
                walker.walkPathReverse(PATH_TO_BANK);
            }
        }
    }

    private boolean walkToFurnace() {
        return haveOre()
                && ctx.objects.select().id(FURNACE_ID).poll().tile().distanceTo(ctx.players.local()) >= 4;
    }

    public abstract boolean walkToBank();
    public abstract boolean haveOre();
}
