package EzSmelt;

import EzSmelt.tasks.banking.BankIronTask;
import EzSmelt.tasks.smelting.IronSmeltingTask;
import EzSmelt.tasks.walking.IronWalkTask;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name= "EzSmelt", description = "Smelt in Falador", properties = "author=Chris; topic=999; client=4;")

public class EzSmelt extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        taskList.add(new BankIronTask(ctx));
        taskList.add(new IronSmeltingTask(ctx));
        taskList.add(new IronWalkTask(ctx));
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
