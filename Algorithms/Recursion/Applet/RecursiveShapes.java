/** Task: Recursively draw smaller polygons located on the vertices of the previously drawn, larger polygon
 * @author: Richard Szeto
 * @course: CS 111C
 * @date:   February 27, 2014
 * @asmt:   Lab C
 */

import java.awt.*;
import javax.swing.*;
import java.lang.Math.*;

// I used radius of circumscribed circle instead of side length
public class RecursiveShapes extends JApplet
{   
    // change nPoints to get Polygon with nPoints vertices
    // if triangle doesn't work, use nPoints = 4 to produce squares
    // or change nPoints to something greater than 3
    private static final int nPoints = 3;
    private static final double shrinkFactor = 2.0;
    private static final int recursiveNests = 7; // number of different sizes
    private int minRadius;

    private static final double pi = 3.141592653589793;

    @Override
    public void init()
    {
        setSize(1000, 1000);
    } // end init

    @Override
    public void paint(Graphics page)
    {
        final int radius = 285; // initial radius
        final int xCenter = 500; // initial xCenter
        int yCenter; // initial yCenter

        minRadius = (int)Math.ceil(radius * Math.pow((1/shrinkFactor),recursiveNests - 1));
        // minRadius = 10;

        if(nPoints == 3)
            yCenter = 600;
        else
            yCenter = 500;
         // end if
        
        if(nPoints >= 3)
            drawPolygon(xCenter, yCenter, radius, page);
         // end if
    } // end paint

    /** Task: Draw polygon based on coordinates of the center of circumscribed
     *        circle and its radius. Recursively draw smaller polygons located
     *        on vertices of larger polygon
     *
     *  @param xCenter x coordinate of the center of the circumscribed circle
     *         yCenter y coordinate of the center of the circumscribec circle
     *         radius  radius of the circumscribed circle
     *         page    Graphics object used to draw
     */
    private void drawPolygon(int xCenter, int yCenter, int radius, Graphics page)
    {
        final PointArray points = new PointArray(nPoints);
        final double angle = (2 * pi) / nPoints;

        double phaseChange;

        if(nPoints == 4)
            phaseChange = angle * 0.5;
        else
            phaseChange = (3 * pi) / 2;
         // end if

        for(int i = 0; i < nPoints; i++)
        {
                points.setPoint(i, (int)Math.ceil(xCenter + (radius * Math.cos((angle * i) + phaseChange))),
                    (int)Math.ceil(yCenter + (radius * Math.sin((angle * i) + phaseChange))));
        } // end for

        page.setColor(Color.gray);
        page.fillPolygon(points.getXArray(), points.getYArray(), nPoints);

        page.setColor(Color.black);
        page.drawPolygon(points.getXArray(), points.getYArray(), nPoints);

        if(radius > minRadius)
        {
            for(int i = 0; i < nPoints; i++)
            {
                drawPolygon(points.getX(i), points.getY(i), (int)Math.ceil(radius/shrinkFactor), page);
            } // end for
        } // end if
    } // end drawPolygon
} // end RecursiveShapes