package dk.futte.blue.teamblep.blepcore.content.inventory;

import net.minecraftforge.items.SlotItemHandler;

/**
 * @author Kelan
 */
public class SlotData<T extends SlotItemHandler> implements Comparable<SlotData<T>>
{
    protected String name;
    protected int id;
    protected int x;
    protected int y;
    protected EnumSlotType slotType;
    protected Class<T> slotClass;

    public SlotData(String name, int id, int x, int y, EnumSlotType slotType, Class<T> slotClass)
    {
        this.name = name;
        this.id = id;
        this.x = x;
        this.y = y;
        this.slotType = slotType;
        this.slotClass = slotClass;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public EnumSlotType getSlotType()
    {
        return slotType;
    }

    public Class<T> getSlotClass()
    {
        return slotClass;
    }

    @Override
    public int compareTo(SlotData<T> that)
    {
        return Integer.compare(this.id, that.id);
    }
}
