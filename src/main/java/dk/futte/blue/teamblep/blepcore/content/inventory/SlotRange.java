package dk.futte.blue.teamblep.blepcore.content.inventory;

/**
 * @author Kelan
 */
public class SlotRange
{
    protected int start;
    protected int end;
    protected boolean reverse;

    public SlotRange(int start, int end, boolean reverse)
    {
        this.start = Math.min(start, end);
        this.end = Math.max(start, end);
        this.reverse = reverse;
    }

    public SlotRange(SlotRange a, SlotRange b, boolean reverse)
    {
        this(Math.min(a.start, b.start), Math.max(a.end, b.end), reverse);
    }

    public SlotRange(SlotRange other)
    {
        this(other.start, other.end, other.reverse);
    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getEnd()
    {
        return end;
    }

    public void setEnd(int end)
    {
        this.end = end;
    }

    public boolean doReverse()
    {
        return reverse;
    }

    public void setReverse(boolean reverse)
    {
        this.reverse = reverse;
    }

    public boolean isSlotInRange(int index)
    {
        return index >= start && index < end;
    }

    public int getRelativeSlotIndex(int index)
    {
        if (isSlotInRange(index))
        {
            return index - start;
        }
        return -1;
    }
}
