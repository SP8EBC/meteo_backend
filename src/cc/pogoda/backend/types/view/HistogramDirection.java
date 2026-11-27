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
	 * N to NNE
	 */
	public int nToNne;
	
	/**
	 * Average of averages of wind speed N to NNE
	 */
	public float averageSpeedNToNne;

	/**
	 * NNE to NE
	 */
	public int nneToNe;

	/**
	 * Average of averages of wind speed NNE to NE
	 */
	public float averageSpeedNneToNe;
	
	/**
	 * NE to ENE
	 */
	public int neToEne;

	/**
	 * Average of averages of wind speed NE to ENE
	 */
	public float averageSpeedNeToEne;
	
	/**
	 * ENE to E
	 */
	public int eneToE;

	/**
	 * Average of averages of wind speed ENE to E
	 */
	public float averageSpeedEneToE;
	
	/**
	 * E to ESE
	 */
	public int eToEse;

	/**
	 * Average of averages of wind speed E to ESE
	 */
	public float averageSpeedEToEse;
	
	/**
	 * ESE to SE
	 */
	public int eseToSe;

	/**
	 * Average of averages of wind speed ESE to SE
	 */
	public float averageSpeedEseToSe;
	
	/**
	 * SE to SSE
	 */
	public int seToSse;

	/**
	 * Average of averages of wind speed SE to SSE
	 */
	public float averageSpeedSeToSse;
	
	/**
	 * SSE to S
	 */
	public int sseToS;

	/**
	 * Average of averages of wind speed SSE to S
	 */
	public float averageSpeedSseToS;
	
	/**
	 * S to SSW
	 */
	public int sToSsw;

	/**
	 * Average of averages of wind speed S to SSW
	 */
	public float averageSpeedSToSsw;
	
	/**
	 * SSW to SW
	 */
	public int sswToSw;

	/**
	 * Average of averages of wind speed SSW to SW
	 */
	public float averageSpeedSswToSw;
	
	/**
	 * SW to WSW
	 */
	public int swToWsw;
	
	/**
	 * Average of averages of wind speed SW to WSW
	 */
	public float averageSpeedSwToWsw;

	/**
	 * WSW to W
	 */
	public int wswToW;

	/**
	 * Average of averages of wind speed WSW to W
	 */
	public float averageSpeedWswToW;
	
	/**
	 * W to WNW
	 */
	public int wToWnw;

	/**
	 * Average of averages of wind speed W to WNW
	 */
	public float averageSpeedWToWnw;
	
	/**
	 * WNW to NW
	 */
	public int wnwToNw;
	
	/**
	 * Average of averages of wind speed WNW to NW
	 */
	public float averageSpeedWnwToNw;

	/**
	 * NW to NNW
	 */
	public int nwToNnw;

	/**
	 * Average of averages of wind speed NE to NNW
	 */
	public float averageSpeedNwToNnw;
	
	/**
	 * NNW to N
	 */
	public int nnwToN;
	
	/**
	 * Average of averages of wind speed NNW to N
	 */
	public float averageSpeedNnwToN;
}
