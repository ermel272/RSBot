package EzSmelt;

import EzSmelt.tasks.banking.BankBronzeTask;
import EzSmelt.tasks.banking.BankIronTask;
import EzSmelt.tasks.banking.BankSteelTask;
import EzSmelt.tasks.smelting.BronzeSmeltingTask;
import EzSmelt.tasks.smelting.IronSmeltingTask;
import EzSmelt.tasks.smelting.SteelSmeltingTask;
import EzSmelt.tasks.walking.BronzeWalkTask;
import EzSmelt.tasks.walking.IronWalkTask;
import EzSmelt.tasks.walking.SteelWalkTask;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name= "EzSmelt", description = "Smelt in Falador", properties = "author=Chris; topic=999; client=4;")

public class EzSmelt extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        // Bring up option window
        String userOptions[] = {"Bronze", "Iron", "Steel"};
        String userChoice = (String) JOptionPane.showInputDialog(
                null,
                "Choose smelt type",
                "EzSmelt",
                JOptionPane.PLAIN_MESSAGE,
                null,
                userOptions,
                userOptions[0]
        );

        if (userOptions[0].equals(userChoice)) {
            taskList.add(new BankBronzeTask(ctx));
            taskList.add(new BronzeSmeltingTask(ctx));
            taskList.add(new BronzeWalkTask(ctx));

        } else if (userOptions[1].equals(userChoice)){
            taskList.add(new BankIronTask(ctx));
            taskList.add(new IronSmeltingTask(ctx));
            taskList.add(new IronWalkTask(ctx));

        } else if (userOptions[2].equals(userChoice)) {
            taskList.add(new BankSteelTask(ctx));
            taskList.add(new SteelSmeltingTask(ctx));
            taskList.add(new SteelWalkTask(ctx));
        } else {
            ctx.controller.stop();
        }
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
