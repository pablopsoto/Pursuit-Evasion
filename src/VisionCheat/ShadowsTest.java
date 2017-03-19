package VisionCheat;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ShadowsTest
{
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createAndShowGUI();
            }
        });
    }    

    private static void createAndShowGUI()
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new ShadowsTestPanel());
        f.setSize(500,500);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}

class ShadowsTestPanel extends JPanel
    implements MouseMotionListener
{
    private final List<Shape> shapes;
    private final Point2D lightPosition;
    private final List<Line2D> borderLineSegments;
    private final List<List<Line2D>> shapesLineSegments;
    private final BufferedImage smileyImage;
    private final BufferedImage skullImage;
    private final BufferedImage blendedImage;

    ShadowsTestPanel()
    {
        addMouseMotionListener(this);

        shapes = new ArrayList<Shape>();

        shapes.add(new Rectangle2D.Double(160, 70, 80, 50));
        shapes.add(new Ellipse2D.Double(290, 120, 50, 30));
        AffineTransform at0 = 
            AffineTransform.getRotateInstance(
                Math.toRadians(45), 320, 290);
        shapes.add(
            at0.createTransformedShape(
                new Rectangle2D.Double(300, 270, 40, 40)));
        shapes.add(new Ellipse2D.Double(60, 240, 80, 110));

        shapesLineSegments = new ArrayList<List<Line2D>>();
        for (Shape shape : shapes)
        {
            shapesLineSegments.add(Shapes.computeLineSegments(shape, 1.0));
        }
        borderLineSegments = new ArrayList<Line2D>();
        shapesLineSegments.add(borderLineSegments);

        lightPosition = new Point2D.Double();

        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                borderLineSegments.clear();
                borderLineSegments.add(
                    new Line2D.Double(0,0,getWidth(),0));
                borderLineSegments.add(
                    new Line2D.Double(getWidth(),0,getWidth(),getHeight()));
                borderLineSegments.add(
                    new Line2D.Double(getWidth(),getHeight(),0,getHeight()));
                borderLineSegments.add(
                    new Line2D.Double(0,getHeight(),0,0));
            }
        });

        smileyImage = createSmileyImage();
        skullImage = createSkullImage();
        blendedImage = createSmileyImage();
    }

    private static BufferedImage createSmileyImage()
    {
        BufferedImage image = 
            new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(5));
        g.setColor(Color.YELLOW);
        g.fill(new Ellipse2D.Double(5, 5, 140, 140));
        g.setColor(Color.BLACK);
        g.draw(new Ellipse2D.Double(5, 5, 140, 140));
        g.fill(new Ellipse2D.Double( 50-15,  50-15, 30, 30));
        g.fill(new Ellipse2D.Double(100-15,  50-15, 30, 30));
        g.draw(new Arc2D.Double(25, 25, 100, 100, 190, 160, Arc2D.OPEN));
        g.dispose();
        return image;
    }

    private static BufferedImage createSkullImage()
    {
        BufferedImage image = 
            new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(5));
        g.setColor(Color.WHITE);
        g.fill(new Ellipse2D.Double(5, 5, 140, 140));
        g.setColor(Color.BLACK);
        g.draw(new Ellipse2D.Double(5, 5, 140, 140));
        g.fill(new Ellipse2D.Double( 50-15,  50-15, 30, 30));
        g.fill(new Ellipse2D.Double(100-15,  50-15, 30, 30));
        Shape mouth = 
            new Arc2D.Double(25, 25, 100, 100, 190, 160, Arc2D.OPEN);
        List<Line2D> lineSegments = Shapes.computeLineSegments(mouth, 2);
        for (int i=0; i<lineSegments.size(); i++)
        {
            Line2D line = lineSegments.get(i);
            Rectangle b = line.getBounds();
            Rectangle r = new Rectangle(b.x, b.y-8, b.width, 16);
            g.setColor(Color.WHITE);
            g.fill(r);
            g.setColor(Color.BLACK);
            g.draw(r);
        }
        g.dispose();
        return image;
    }



    @Override
    protected void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0,getWidth(),getHeight());
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        for (Shape shape : shapes)
        {
            g.draw(shape);
        }

        List<Line2D> rays = createRays(lightPosition);
        //paintRays(g, rays);

        List<Point2D> closestIntersections = 
            computeClosestIntersections(rays);
        Collections.sort(closestIntersections, 
            Points.byAngleComparator(lightPosition));

        //paintClosestIntersections(g, closestIntersections);
        //paintLinesToIntersections(g, closestIntersections);

        Shape lightShape = createLightShape(closestIntersections);
        g.setColor(Color.WHITE);
        g.fill(lightShape);

        g.drawImage(smileyImage, 150, 150, null);

        blend(skullImage, 150, 150, lightShape, blendedImage);
        g.drawImage(blendedImage, 150, 150, null);

        g.setColor(Color.YELLOW);
        double r = 10;
        g.fill(new Ellipse2D.Double(
            lightPosition.getX()-r, lightPosition.getY()-r, 
            r+r, r+r));
    }

    private static void blend(
        BufferedImage image, int x, int y, 
        Shape lightShape, BufferedImage result) 
    {
        int w = image.getWidth();
        int h = image.getHeight();
        Graphics2D g = result.createGraphics();
        g.setComposite(AlphaComposite.SrcOver);
        g.setColor(new Color(0,0,0,0));
        g.fillRect(0,0,w,h);
        g.drawImage(image, 0, 0, null);
        g.translate(-x, -y);
        g.setComposite(AlphaComposite.SrcOut);
        g.fill(lightShape);
        g.dispose();
    }

    private Shape createLightShape(
        List<Point2D> closestIntersections)
    {
        Path2D shadowShape = new Path2D.Double();
        for (int i=0; i<closestIntersections.size(); i++)
        {
            Point2D p = closestIntersections.get(i);
            double x = p.getX();
            double y = p.getY();
            if (i == 0)
            {
                shadowShape.moveTo(x, y);
            }
            else
            {
                shadowShape.lineTo(x, y);
            }
        }
        shadowShape.closePath();
        return shadowShape;
    }




    private void paintRays(Graphics2D g, List<Line2D> rays)
    {
        g.setColor(Color.YELLOW);
        for (Line2D ray : rays)
        {
            g.draw(ray);
        }
    }

    private void paintClosestIntersections(Graphics2D g,
        List<Point2D> closestIntersections)
    {
        g.setColor(Color.RED);
        double r = 3;
        for (Point2D p : closestIntersections)
        {
            g.fill(new Ellipse2D.Double(
                p.getX()-r, p.getY()-r, r+r, r+r));
        }
    }

    private void paintLinesToIntersections(Graphics2D g,
        List<Point2D> closestIntersections)
    {
        g.setColor(Color.RED);
        for (Point2D p : closestIntersections)
        {
            g.draw(new Line2D.Double(lightPosition, p));
        }
    }



    private List<Point2D> computeClosestIntersections(List<Line2D> rays)
    {
        List<Point2D> closestIntersections = new ArrayList<Point2D>();
        for (Line2D ray : rays)
        {
            Point2D closestIntersection =
                computeClosestIntersection(ray);
            if (closestIntersection != null)
            {
                closestIntersections.add(closestIntersection);
            }
        }
        return closestIntersections;
    }


    private List<Line2D> createRays(Point2D lightPosition)
    {
        final double deltaRad = 0.0001;
        List<Line2D> rays = new ArrayList<Line2D>();
        for (List<Line2D> shapeLineSegments : shapesLineSegments)
        {
            for (Line2D line : shapeLineSegments)
            {
                Line2D ray0 = new Line2D.Double(lightPosition, line.getP1());
                Line2D ray1 = new Line2D.Double(lightPosition, line.getP2());
                rays.add(ray0);
                rays.add(ray1);

                rays.add(Lines.rotate(ray0, +deltaRad, null));
                rays.add(Lines.rotate(ray0, -deltaRad, null));
                rays.add(Lines.rotate(ray1, +deltaRad, null));
                rays.add(Lines.rotate(ray1, -deltaRad, null));
            }
        }
        return rays;
    }


    private Point2D computeClosestIntersection(Line2D ray)
    {
        final double EPSILON = 1e-6;
        Point2D relativeLocation = new Point2D.Double();
        Point2D absoluteLocation = new Point2D.Double();
        Point2D closestIntersection = null;
        double minRelativeDistance = Double.MAX_VALUE;
        for (List<Line2D> lineSegments : shapesLineSegments)
        {
            for (Line2D lineSegment : lineSegments)
            {
                boolean intersect =
                    Intersection.intersectLineLine(
                        ray, lineSegment, relativeLocation, absoluteLocation);
                if (intersect)
                {
                    if (relativeLocation.getY() >= -EPSILON &&
                        relativeLocation.getY() <= 1+EPSILON)
                    {
                        if (relativeLocation.getX() >= -EPSILON &&
                            relativeLocation.getX() < minRelativeDistance)
                        {
                            minRelativeDistance =
                                relativeLocation.getX();
                            closestIntersection = 
                                new Point2D.Double(
                                    absoluteLocation.getX(),
                                    absoluteLocation.getY());
                        }
                    }
                }
            }
        }
        return closestIntersection;
    }


    @Override
    public void mouseMoved(MouseEvent e)
    {
        lightPosition.setLocation(e.getPoint());
        repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

}


class Points
{
    /**
     * Creates a comparator that compares points by the
     * angle of the line between the point and the given
     * center
     * 
     * @param center The center
     * @return The comparator
     */
    public static Comparator<Point2D> byAngleComparator(
        final Point2D center)
    {
        return new Comparator<Point2D>()
        {
            @Override
            public int compare(Point2D p0, Point2D p1)
            {
                double dx0 = p0.getX() - center.getX();
                double dy0 = p0.getY() - center.getY();
                double dx1 = p1.getX() - center.getX();
                double dy1 = p1.getY() - center.getY();
                double angle0 = Math.atan2(dy0, dx0);
                double angle1 = Math.atan2(dy1, dx1);
                return Double.compare(angle0, angle1);
            }
        };
    }
}


class Lines
{
    /**
     * Rotate the given line around its starting point, by
     * the given angle, and stores the result in the given
     * result line. If the result line is <code>null</code>,
     * then a new line will be created and returned.
     * 
     * @param line The line
     * @param angleRad The rotation angle
     * @param The result line
     * @return The result line
     */
    static Line2D rotate(Line2D line, double angleRad, Line2D result)
    {
        double x0 = line.getX1();
        double y0 = line.getY1();
        double x1 = line.getX2();
        double y1 = line.getY2();
        double dx = x1 - x0;;
        double dy = y1 - y0;
        double sa = Math.sin(angleRad);
        double ca = Math.cos(angleRad);
        double nx = ca * dx - sa * dy;
        double ny = sa * dx + ca * dy;
        if (result == null)
        {
            result = new Line2D.Double();
        }
        result.setLine(x0, y0, x0+nx, y0+ny);
        return result;
    }

}

class Intersection
{
    /**
     * Epsilon for floating point computations
     */
    private static final double EPSILON = 1e-6;


    /**
     * Computes the intersection of the given lines.
     * 
     * @param line0 The first line
     * @param line1 The second line
     * @param relativeLocation Optional location that stores the 
     * relative location of the intersection point on 
     * the given line segments
     * @param absoluteLocation Optional location that stores the 
     * absolute location of the intersection point
     * @return Whether the lines intersect
     */
    public static boolean intersectLineLine( 
        Line2D line0, Line2D line1,
        Point2D relativeLocation,
        Point2D absoluteLocation)
    {
        return intersectLineLine(
            line0.getX1(), line0.getY1(), 
            line0.getX2(), line0.getY2(),
            line1.getX1(), line1.getY1(), 
            line1.getX2(), line1.getY2(),
            relativeLocation, absoluteLocation);
    }

    /**
     * Computes the intersection of the specified lines.
     * 
     * Ported from 
     * http://www.geometrictools.com/LibMathematics/Intersection/
     *     Wm5IntrSegment2Segment2.cpp
     * 
     * @param s0x0 x-coordinate of point 0 of line segment 0
     * @param s0y0 y-coordinate of point 0 of line segment 0
     * @param s0x1 x-coordinate of point 1 of line segment 0
     * @param s0y1 y-coordinate of point 1 of line segment 0
     * @param s1x0 x-coordinate of point 0 of line segment 1
     * @param s1y0 y-coordinate of point 0 of line segment 1
     * @param s1x1 x-coordinate of point 1 of line segment 1
     * @param s1y1 y-coordinate of point 1 of line segment 1
     * @param relativeLocation Optional location that stores the 
     * relative location of the intersection point on 
     * the given line segments
     * @param absoluteLocation Optional location that stores the 
     * absolute location of the intersection point
     * @return Whether the lines intersect
     */
    public static boolean intersectLineLine( 
        double s0x0, double s0y0,
        double s0x1, double s0y1,
        double s1x0, double s1y0,
        double s1x1, double s1y1,
        Point2D relativeLocation,
        Point2D absoluteLocation)
    {
        double dx0 = s0x1 - s0x0;
        double dy0 = s0y1 - s0y0;
        double dx1 = s1x1 - s1x0;
        double dy1 = s1y1 - s1y0;

        double invLen0 = 1.0 / Math.sqrt(dx0*dx0+dy0*dy0); 
        double invLen1 = 1.0 / Math.sqrt(dx1*dx1+dy1*dy1); 

        double dir0x = dx0 * invLen0;
        double dir0y = dy0 * invLen0;
        double dir1x = dx1 * invLen1;
        double dir1y = dy1 * invLen1;

        double c0x = s0x0 + dx0 * 0.5;
        double c0y = s0y0 + dy0 * 0.5;
        double c1x = s1x0 + dx1 * 0.5;
        double c1y = s1y0 + dy1 * 0.5;

        double cdx = c1x - c0x;
        double cdy = c1y - c0y;

        double dot = dotPerp(dir0x, dir0y, dir1x, dir1y);
        if (Math.abs(dot) > EPSILON)
        {
            if (relativeLocation != null || absoluteLocation != null)
            {
                double dot0 = dotPerp(cdx, cdy, dir0x, dir0y);
                double dot1 = dotPerp(cdx, cdy, dir1x, dir1y);
                double invDot = 1.0/dot;
                double s0 = dot1*invDot;
                double s1 = dot0*invDot;
                if (relativeLocation != null)
                {
                    double n0 = (s0 * invLen0) + 0.5;
                    double n1 = (s1 * invLen1) + 0.5;
                    relativeLocation.setLocation(n0, n1);
                }
                if (absoluteLocation != null)
                {
                    double x = c0x + s0 * dir0x;
                    double y = c0y + s0 * dir0y;
                    absoluteLocation.setLocation(x, y);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the perpendicular dot product, i.e. the length
     * of the vector (x0,y0,0)x(x1,y1,0).
     * 
     * @param x0 Coordinate x0
     * @param y0 Coordinate y0
     * @param x1 Coordinate x1
     * @param y1 Coordinate y1
     * @return The length of the cross product vector
     */
    private static double dotPerp(double x0, double y0, double x1, double y1)
    {
        return x0*y1 - y0*x1;
    }

}



class Shapes
{
    /**
     * Create a list containing line segments that approximate the given 
     * shape.
     * 
     * @param shape The shape
     * @param flatness The allowed flatness
     * @return The list of line segments
     */
    static List<Line2D> computeLineSegments(Shape shape, double flatness)
    {
        List<Line2D> result = new ArrayList<Line2D>();
        PathIterator pi =
            new FlatteningPathIterator(
                shape.getPathIterator(null), flatness);
        double[] coords = new double[6];
        double previous[] = new double[2];
        double first[] = new double[2];
        while (!pi.isDone())
        {
            int segment = pi.currentSegment(coords);
            switch (segment)
            {
                case PathIterator.SEG_MOVETO:
                    previous[0] = coords[0];
                    previous[1] = coords[1];
                    first[0] = coords[0];
                    first[1] = coords[1];
                    break;

                case PathIterator.SEG_CLOSE:
                    result.add(new Line2D.Double(
                        previous[0], previous[1],
                        first[0], first[1]));
                    previous[0] = first[0];
                    previous[1] = first[1];
                    break;

                case PathIterator.SEG_LINETO:
                    result.add(new Line2D.Double(
                        previous[0], previous[1],
                        coords[0], coords[1]));
                    previous[0] = coords[0];
                    previous[1] = coords[1];
                    break;

                case PathIterator.SEG_QUADTO:
                    // Should never occur
                    throw new AssertionError(
                        "SEG_QUADTO in flattened path!");

                case PathIterator.SEG_CUBICTO:
                    // Should never occur
                    throw new AssertionError(
                        "SEG_CUBICTO in flattened path!");
            }
            pi.next();
        }
        return result;
    }
}