package utilities;

public class DateExtender {

	public static String leadingZeros(int time)
	{
		if(time < 10)
		{
			return "0" + time;
		} else {
			return ""+time;
		}
	}	
}
