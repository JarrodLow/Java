/** Task: Stores x,y coordinates in two arrays
 * @author: Richard Szeto
 * @course: CS 111C
 * @date:   February 27, 2014
 * @asmt:   Lab C
 */

public class PointArray
{
    private int[] xPoints;
    private int[] yPoints;
    private int nPoints;

    PointArray(int numPoints)
    {
        if(numPoints > 0)
        {
            nPoints = numPoints;
            xPoints = new int[nPoints];
            yPoints = new int[nPoints];
        }
        else
        {
            nPoints = 0;
            xPoints = null;
            yPoints = null;
        }
    }

    public int getX(int index)
    {
        if(index >= 0 && index < nPoints)
        {
            return xPoints[index];
        }
        else
            return 0;
    }

    public int getY(int index)
    {
        if(index >= 0 && index < nPoints)
        {
            return yPoints[index];
        }
        else
            return 0;
    }

    public void setPoint(int index, int x, int y)
    {
        if(index >= 0 && index < nPoints)
        {
            xPoints[index] = x;
            yPoints[index] = y;
        }
    }

    public int[] getXArray()
    {
        return xPoints;
    }

    public int[] getYArray()
    {
        return yPoints;
    }
}