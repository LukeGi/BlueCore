package dk.futte.blue.teamblep.blepcore.content.tileentity.machine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kelan
 */
public class ProgressTracker
{
    private List<ProgressBar> progressBars = new ArrayList<>();

    private ProgressTracker() {}

    public static ProgressTracker create()
    {
        return new ProgressTracker();
    }

    public ProgressTracker addProgressBar(ProgressBar progressBar)
    {
        if (progressBar != null && !progressBars.contains(progressBar))
        {
            progressBars.add(progressBar);
        }

        return this;
    }

    public ProgressBar getProgressBar(String name)
    {
        for (ProgressBar progressBar : progressBars)
        {
            if (progressBar.getName().equals(name))
            {
                return progressBar;
            }
        }

        return null;
    }

    public List<ProgressBar> tick()
    {
        List<ProgressBar> ticked = new ArrayList<>();

        for (ProgressBar progressBar : progressBars)
        {
            if (progressBar.tick())
            {
                ticked.add(progressBar);
            }
        }

        return ticked;
    }
}
