import java.io.BufferedReader;
import java.util.*;

public class MyRectangle
{
	public MyRectangle()
	{
		startX = 0;
		startY = 0;
		width = 0;
		height = 0;
	}
	public MyRectangle(int x, int y, int w, int h)
	{
		height = h;
		width = w;
		startY = y;
		startX = x;
	}
	public int area()
	{
		return = height * width;
	}
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append("Width: " + width);
		S.append(" Height: " + height);
		S.append(" X: " + startX);
		S.append(" Y: " + startY);
		return S.toString();
	}
	public boolean isInside(int x, int y)
	{
		return startX < width && startY < height;
	}
	public void setSize(int newWidth, int newHeight)
	{
		newWidth = width;
		newHeight = height;
	}
	public void setPosition(int newX, int newY)
	{
		newX = startX;
		newY = startY;
	}
}