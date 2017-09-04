package dk.futte.blue.teamblep.blepcore.content.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

/**
 * @author Kelan
 */
public class ProgressBar
{
    private String name;
    private int ticksRequired;
    private int ticksElapsed;

    public ProgressBar(String name, int ticksRequired, int ticksElapsed)
    {
        this.name = name;
        this.ticksRequired = ticksRequired;
        this.ticksElapsed = ticksElapsed;
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

    public boolean tick()
    {
        ticksElapsed++;

        if (ticksElapsed >= ticksRequired)
        {
            ticksElapsed = 0;
            return true;
        }

        return false;
    }

    public int getProgressScaled(int pixels)
    {
        if (ticksElapsed <= 0 || ticksRequired <= 0)
        {
            return 0;
        }

        return ticksElapsed * pixels / ticksRequired;
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
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("ticksRequired") && compound.hasKey("ticksElapsed"))
        {
            ticksRequired = compound.getInteger("ticksRequired");
            ticksElapsed = compound.getInteger("ticksElapsed");
        }
    }
}
