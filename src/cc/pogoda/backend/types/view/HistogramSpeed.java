package cc.pogoda.backend.types.view;

public class HistogramSpeed {

	/**
	 * How many records has been used to create this dataset
	 */
	public int totalRecords;
	
	/**
	 * UNIX Epoch timestamp of the oldest record in the input dataset. 
	 * @note this might be newer that a begin of a range requested by the user
	 */
	public long oldestRecordTimestamp;
	
	/**
	 * UNIX Epoch timestamp of the oldest record in the input dataset. 
	 * @note this might be older than an end of the range requested by the user
	 */
	public long newestRecordTimestamp;
	
	/**
	 * Number of records with wind speed from zero to one meter per second exclusive
	 * <0.0 m/s - 1.0ms)
	 */
	public int lessThanOne;
	
	/**
	 * Number of records with wind speed from one to two meter per second
	 * <1.0m/s - 2.0m/s)
	 */
	public int oneToTwo;
	
	public int twoToThree;
	
	public int threeToFour;
	
	public int fourToFive;
	
	public int fiveToSix;
	
	public int sixToSeven;
	
	public int sevenToEight;
	
	public int eightToNine;
	
	public int nineToTen;
	
	/**
	 * Everything equals and above 10m/s 
	 */
	public int overTen;
}
