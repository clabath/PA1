/**
 * Team members:
 * @author Kyle Bowden
 * @author Kyle Trom
 * @author Cassandra Labath
 * 
 * Endpoint class for Node.
 */
public class Endpoint {
	
	/**
	 * returns the endpoint value.  For example if the
	 * End point object represents the left end point of the 
	 * interval [1,3], this would return 1.
	 * @return
	 */
	int value;
	public Endpoint(int value)
	{
		this.value = value;
	}
	public int getValue() {
		return value;
	}
} // im so hot
