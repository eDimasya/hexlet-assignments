package exercise;

// BEGIN
public class Segment {
    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        return new Point((this.beginPoint.getX() + this.endPoint.getX()) / 2,
                (this.beginPoint.getY() + this.endPoint.getY()) / 2);
    }

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    private Point beginPoint;
    private Point endPoint;

}
// END
