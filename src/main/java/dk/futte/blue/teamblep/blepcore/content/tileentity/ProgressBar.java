package dk.futte.blue.teamblep.blepcore.content.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Kelan
 */
public class ProgressBar
{
    private String name;
    private int ticksRequired;
    private int ticksElapsed;
    private boolean reversed;

    public ProgressBar(String name, int ticksRequired, int ticksElapsed, boolean reversed)
    {
        this.name = name;
        this.ticksRequired = ticksRequired;
        this.ticksElapsed = ticksElapsed;
        this.reversed = reversed;
    }

    public ProgressBar(String name, int ticksRequired, int ticksElapsed)
    {
        this(name, ticksRequired, ticksElapsed, false);
    }

    public ProgressBar(String name, int ticksRequired)
    {
        this(name, ticksRequired, 0);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getTicksRequired()
    {
        return ticksRequired;
    }

    public void setTicksRequired(int ticksRequired)
    {
        this.ticksRequired = ticksRequired;
    }

    public int getTicksElapsed()
    {
        return ticksElapsed;
    }

    public void setTicksElapsed(int ticksElapsed)
    {
        this.ticksElapsed = ticksElapsed;
    }

    public boolean isReversed()
    {
        return reversed;
    }

    public void setReversed(boolean reversed)
    {
        this.reversed = reversed;
    }

    public void tick()
    {
        if (reversed)
        {
            ticksElapsed--;
        } else
        {
            ticksElapsed++;
        }
    }

    public void reset()
    {
        if (reversed)
        {
            ticksElapsed = ticksRequired;
        } else
        {
            ticksElapsed = 0;
        }
    }

    public boolean isDone()
    {
        if (reversed)
        {
            return ticksElapsed <= 0;
        } else
        {
            return ticksElapsed >= ticksRequired;
        }
    }

    public int getProgressScaled(int pixels)
    {
        if (ticksElapsed <= 0 || ticksRequired <= 0)
        {
            return 0;
        }

        return (int) Math.ceil((float) (ticksElapsed * pixels) / (float) ticksRequired);
    }

    public ProgressBar copy()
    {
        ProgressBar progressBar = new ProgressBar(this.name, this.ticksRequired, this.ticksElapsed, this.reversed);

        NBTTagCompound nbt = this.writeToNBT(new NBTTagCompound());
        progressBar.readFromNBT(nbt);

        return progressBar;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgressBar that = (ProgressBar) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("ticksRequired", ticksRequired);
        compound.setInteger("ticksElapsed", ticksElapsed);
        compound.setBoolean("reversed", reversed);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("ticksRequired") && compound.hasKey("ticksElapsed") && compound.hasKey("reversed"))
        {
            ticksRequired = compound.getInteger("ticksRequired");
            ticksElapsed = compound.getInteger("ticksElapsed");
            reversed = compound.getBoolean("reversed");
        }
    }
}
