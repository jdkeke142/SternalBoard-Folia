package com.xism4.sternalboard.managers.animation.tasks;

import com.xism4.sternalboard.SternalBoardHandler;
import com.xism4.sternalboard.SternalBoardPlugin;
import com.xism4.sternalboard.managers.ScoreboardManager;
import com.xism4.sternalboard.managers.animation.AnimationManager;
import com.xism4.sternalboard.utils.TextUtils;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;

import java.util.List;
import java.util.function.Consumer;

public class TitleUpdateTask implements Consumer<ScheduledTask> {
    private final String[] lines;
    private final AnimationManager animationManager;
    private final ScoreboardManager scoreboardManager;
    private final SternalBoardPlugin plugin;
    int index;

    public TitleUpdateTask(SternalBoardPlugin plugin, AnimationManager animationManager, List<String> lines) {
        this.plugin = plugin;
        this.scoreboardManager = plugin.getScoreboardManager();
        this.animationManager = animationManager;
        this.lines = lines.toArray(new String[0]);
        this.index = 0;
    }

    @Override
    public void accept(ScheduledTask scheduledTask) {
        animationManager.setTitle(lines[index]);
        index++;

        if (index == lines.length) {
            index = 0;
        }

        for (SternalBoardHandler sb : scoreboardManager.getBoardsHandler().values()) {
            String line = TextUtils.processPlaceholders(plugin, sb.getPlayer(), animationManager.getTitle());
            sb.updateTitle(line);
        }
    }
}
