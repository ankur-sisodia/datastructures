/** A class that represents a path via pursuit curves.
 *  @author You!
 */
public class Path {

    /** What to do, what to do... */
	Point currPoint;
	Point nextPoint;
	
	public Path (double x, double y){
		currPoint = new Point(0,0);
		nextPoint = new Point(x,y);
	}
	
	double getCurrX() {return currPoint.getX();}
	double getCurrY() {return currPoint.getY();}
	double getNextX() {return nextPoint.getX();}
	double getNextY() {return nextPoint.getY();}
	void iterate(double dx, double dy) {
		currPoint = nextPoint;
		nextPoint = new Point(currPoint.getX()+dx, currPoint.getY()+dy);
		}

}
