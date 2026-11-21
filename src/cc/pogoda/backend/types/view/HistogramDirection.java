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
	 * NNE to NE
	 */
	public int nneToNe;

	/**
	 * NE to ENE
	 */
	public int neToEne;

	/**
	 * ENE to E
	 */
	public int eneToE;

	/**
	 * E to ESE
	 */
	public int eToEse;

	/**
	 * ESE to SE
	 */
	public int eseToSe;

	/**
	 * SE to SSE
	 */
	public int seToSse;

	/**
	 * SSE to S
	 */
	public int sseToS;

	/**
	 * S to SSW
	 */
	public int sToSsw;

	/**
	 * SSW to SW
	 */
	public int sswToSw;

	/**
	 * SW to WSW
	 */
	public int swToWsw;

	/**
	 * WSW to W
	 */
	public int wswToW;

	/**
	 * W to WNW
	 */
	public int wToWnw;

	/**
	 * WNW to NW
	 */
	public int wnwToNw;

	/**
	 * NW to NNW
	 */
	public int nwToNnw;

	/**
	 * NNW to N
	 */
	public int nnwToN;
}
