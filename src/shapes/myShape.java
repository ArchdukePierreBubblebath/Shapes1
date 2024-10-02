package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class myShape extends Shape {
    private final double sideLength; // This will be interpreted as the outer radius of the star

    public myShape(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public String getAuthor() {
        return "Ansh";
    }

    @Override
    public double getPerimiter() {
        // Approximate the perimeter as 10 sides of the star (5 inner, 5 outer)
        double innerRadius = sideLength / 2;
        return 5 * (2 * sideLength + 2 * innerRadius);
    }

    @Override
    public double getArea() {
        // Approximate area calculation for the star (complex, simplified formula)
        double innerRadius = sideLength / 2;
        return 5 * (sideLength * innerRadius) / 2;
    }

    @Override
    public void renderAsJpeg(File fileToJpeg) throws Exception {
        int width = 256;
        int height = 256;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(Color.WHITE); // Background color
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(getColor()); // Star color

        // Coordinates for star
        int centerX = width / 2;
        int centerY = height / 2;
        double outerRadius = sideLength;
        double innerRadius = outerRadius / 2;
        int numPoints = 10;
        double angle = Math.PI / 5;

        int[] xPoints = new int[numPoints];
        int[] yPoints = new int[numPoints];

        // Calculate the points of the star
        for (int i = 0; i < numPoints; i++) {
            double currentAngle = i * angle;
            double radius = (i % 2 == 0) ? outerRadius : innerRadius;
            xPoints[i] = (int) (centerX + radius * Math.sin(currentAngle));
            yPoints[i] = (int) (centerY - radius * Math.cos(currentAngle));
        }

        // Draw the star
        g2d.fillPolygon(xPoints, yPoints, numPoints);

        addAuthorText(g2d, width, height); // Add author text at the bottom
        g2d.dispose();

        ImageIO.write(image, "jpg", fileToJpeg);
        System.out.println("Star image saved as JPEG");
    }
}

