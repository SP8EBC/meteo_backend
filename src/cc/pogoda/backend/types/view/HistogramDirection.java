package cc.pogoda.backend.types.view;

public class HistogramDirection {
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
	 * N to NNW
	 */
	public int northToNorthNorthWest;
	
	/**
	 * NNW to NW
	 */
	public int northNorthWestToNorthWest;
	
	/**
	 * NW to WNW
	 */
	public int northWestToWestNorthWest;
	
	/**
	 * WNW to W
	 */
	public int westNorthWestToWest;
	
	/**
	 * W to WSW
	 */
	public int westToWestSouthWest;

	/**
	 * WSW to SW
	 */
	public int westSouthWestToSouthWest;

	/**
	 * SW to SSW
	 */
	public int southWestToSouthSouthWest;
	
	/**
	 * SSW to S
	 */
	public int southSouthWestToSouth;
	
	/**
	 * S to SSE
	 */
	public int southToSouthSouthEast;
	
	/**
	 * SSE to SE
	 */
	public int southSouthEastToSouthEast;
	
	/**
	 * SE to ESE
	 */
	public int southEastToEastSouthEast;
	
	/**
	 * ESE to E
	 */
	public int eastSouthEastToEast;
	
	/**
	 * E to ENE
	 */
	public int eastToEastNorthEast;
	
	/**
	 * ENE to NE
	 */
	public int eastNorthEastToNorthEast;
	
	/**
	 * NE to NNE
	 */
	public int northEastToNorthNorthEast;
	
	/**
	 * NNE to N
	 */
	public int northNorthEastToNorth;
}
