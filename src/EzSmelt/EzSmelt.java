package EzSmelt;

import EzSmelt.tasks.BankTask;
import EzSmelt.tasks.SmeltingTask;
import EzSmelt.tasks.WalkTask;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name= "EzSmelt", description = "Smelt in Falador", properties = "author=Chris; topic=999; client=4;")

public class EzSmelt extends PollingScript<ClientContext> {

    private final static int BRONZE_BAR_ID = 2349;

    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        taskList.add(new BankTask(ctx));
        taskList.add(new SmeltingTask(ctx));
        taskList.add(new WalkTask(ctx));
    }

    @Override
    public void stop() {

    }

    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
                break;
            }
        }
    }
}
