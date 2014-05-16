package rent;

import java.util.Date;
import prs.Clock;

public class MockClock implements Clock { //COPY:ALL
	private Date currentTime; //COPY:ALL
	 //COPY:ALL
	public void setTime(Date time) { //COPY:ALL
		currentTime = time; //COPY:ALL
	} //COPY:ALL
	public Date getTime() { //COPY:ALL
		return currentTime; //COPY:ALL
	} //COPY:ALL
} //COPY:ALL
