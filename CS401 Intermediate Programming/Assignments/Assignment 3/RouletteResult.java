public class RouletteResult
{
	private RColors color;
	private RRanges range;
	private RParities evo;
	private int value;
	
	public RouletteResult(RColors col, RRanges ran, RParities eo, int val)
	{
		color = col;
		range = ran;
		evo = eo;
		value = val;
	}
	
	public String toString()
	{
		return new String("Value:" + value + "  Color:" + color + "  Range:" + range + "  Parity:" + evo);
	}
}