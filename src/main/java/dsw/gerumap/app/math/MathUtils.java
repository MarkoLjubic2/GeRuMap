package dsw.gerumap.app.math;

import java.awt.*;

@SuppressWarnings("DuplicateExpressions")
public class MathUtils {

    public static MathUtils instance;

    private MathUtils() {}

    public double findDirection(Point A, Point B) {
        return (B.getY()-A.getY())/(B.getX()-A.getX());
    }

    public double findYIntercept(Point A, Point B) {
        double k = findDirection(A, B);
        return A.getY()-k*A.getX();
    }

    public boolean isPointInLine(Point X, Point A, Point B) {
        double a = findDirection(A, B), b = -1, c = findYIntercept(A, B);
        double d = Math.abs(a*X.getX() + b*X.getY() + c)/Math.sqrt(a*a + b*b);
        return (d < 2);

    }

    public double euclideanDistance(Point A, Point B) {
        return Math.sqrt(((A.getX()-B.getX()) * (A.getX()-B.getX())) + ((A.getY()-B.getY()) * (A.getY()-B.getY())));
    }

    public int[][] linearTransformTriangle(Point A, Point B) {
        double x1 = A.getX(), y1 = A.getY();
        double x2 = B.getX(), y2 = B.getY();

        int dx = (int)(x2 - x1), dy = (int)(y2 - y1);
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - 13, xn = xm, ym = 9, yn = -9, x;
        double sin = dy / D, cos = dx / D;
        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xPoints = {(int)x2+1, (int) xm, (int) xn};
        int[] yPoints = {(int)y2+1, (int) ym, (int) yn};

        return new int[][]{xPoints, yPoints};
    }

    public Point intersectionEllipseLine(double[] ellipse, double[] line, Point A, Point B, boolean isReverse) {
        double aSqr = ellipse[0]*ellipse[0], bSqr = ellipse[1]*ellipse[1];
        double m = ellipse[2], p = ellipse[3];
        double k = line[0], n = line[1];
        double alpha = bSqr+aSqr*k*k, beta = (2*k*n*aSqr)-(2*bSqr*m)-(2*k*p*aSqr), gama = (p*p*aSqr)+(n*n*aSqr)-(aSqr*bSqr)-(2*n*p*aSqr)+(bSqr*m*m);
        double D = (beta*beta - 4*alpha*gama);

        double x1, x2, y1, y2;
        if (D > 0) {
            x1 = ((-1)*beta + Math.sqrt(D))/(2*alpha);
            x2 = ((-1)*beta - Math.sqrt(D))/(2*alpha);
        }
        else if (D == 0) {
            x1 = x2 = ((-1)*beta + Math.sqrt(D))/(2*alpha);
        }
        else return null;

        y1 = k*x1+n;
        y2 = k*x2+n;
        Point X1 = new Point((int)x1, (int)y1), X2 = new Point((int)x2, (int)y2);
        if (isReverse) {
            if (euclideanDistance(X1, B) <= euclideanDistance(X2, B)) return X1;
            else return X2;
        }
        if (euclideanDistance(X1, A) <= euclideanDistance(X2, A)) return X1;
        else return X2;
    }

    public static MathUtils getInstance() {
        if (instance == null) {
            instance = new MathUtils();
        }
        return instance;
    }

}
