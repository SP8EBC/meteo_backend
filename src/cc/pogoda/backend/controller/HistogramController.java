package cc.pogoda.backend.controller;

import java.time.Instant;
import java.util.List;
import java.util.function.ToDoubleFunction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationDataDao;
import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.dao.TelemetryDao;
import cc.pogoda.backend.types.NotFoundException;
import cc.pogoda.backend.types.TooLongException;
import cc.pogoda.backend.types.model.StationDataModel;
import cc.pogoda.backend.types.view.HistogramDirection;
import cc.pogoda.backend.types.view.HistogramSpeed;

@RestController
public class HistogramController {
	@Autowired
	StationDataDao dataDao;

	@Autowired
	TelemetryDao telemetry;

	@Autowired
	StationsDefinitionDao stationsDefinitionDao;

	private static Logger logger = LogManager.getLogger();

	/**
	 * Longest time range, in seconds, a histogram can be requested for
	 */
	private static final long MAX_TIME_RANGE = 24 * 3600;

	/**
	 * Throws an exception (translated to HTTP 416) if requested time range is longer than allowed
	 */
	private static void checkTimeRange(long from, long to) throws TooLongException {
		if (to - from > MAX_TIME_RANGE) {
			throw new TooLongException();
		}
	}

	/**
	 * Throws an exception (translated to HTTP 404) if station with given name isn't defined in the system
	 */
	private void checkStationExists(String stationName) throws NotFoundException {
		if (stationsDefinitionDao.getStationByName(stationName) == null) {
			throw new NotFoundException();
		}
	}

	@RequestMapping(value = "/station/{stationName}/histogram/direction", produces = "application/json;charset=UTF-8")
	public HistogramDirection getHistogramDirectionPerStationName(@PathVariable(required = true) String stationName,
			@RequestParam(value = "from", defaultValue = "0") long from,
			@RequestParam(value = "to", defaultValue = "0") long to) throws NotFoundException, TooLongException {
		HistogramDirection out = new HistogramDirection();

		checkStationExists(stationName);

		// if time range hasn't been fully provided
		if (from == 0 || to == 0) {
			// read data for last 2 hours, so set from and to respectively
			to = Instant.now().getEpochSecond();
			from = to - 2 * 3600;
		}

		checkTimeRange(from, to);

		// get station data for chosen time range
		List<StationDataModel> stationData = dataDao.getStationData(stationName, from, to);

		// if there is any data
		if (stationData.size() > 0) {
			// set this to maximum allowed value for long, to be able to find lowest value
			out.oldestRecordTimestamp = Long.MAX_VALUE;

			// set this to how many records we will process
			out.totalRecords = stationData.size();

			// go through every record
			for (StationDataModel record : stationData) {
				// extract this for convenient
				final short direction = record.winddir;

				// update oldest and newest timestamp found so far
				if (record.epoch < out.oldestRecordTimestamp) {
					out.oldestRecordTimestamp = record.epoch;
				}

				if (record.epoch > out.newestRecordTimestamp) {
					out.newestRecordTimestamp = record.epoch;
				}

				// each bin is 22.5 degrees wide
				if (direction >= 0 && direction < 22.5) {
					out.nToNne++;
				} else if (direction >= 22.5 && direction < 45) {
					out.nneToNe++;
				} else if (direction >= 45 && direction < 67.5) {
					out.neToEne++;
				} else if (direction >= 67.5 && direction < 90) {
					out.eneToE++;
				} else if (direction >= 90 && direction < 112.5) {
					out.eToEse++;
				} else if (direction >= 112.5 && direction < 135) {
					out.eseToSe++;
				} else if (direction >= 135 && direction < 157.5) {
					out.seToSse++;
				} else if (direction >= 157.5 && direction < 180) {
					out.sseToS++;
				} else if (direction >= 180 && direction < 202.5) {
					out.sToSsw++;
				} else if (direction >= 202.5 && direction < 225) {
					out.sswToSw++;
				} else if (direction >= 225 && direction < 247.5) {
					out.swToWsw++;
				} else if (direction >= 247.5 && direction < 270) {
					out.wswToW++;
				} else if (direction >= 270 && direction < 292.5) {
					out.wToWnw++;
				} else if (direction >= 292.5 && direction < 315) {
					out.wnwToNw++;
				} else if (direction >= 315 && direction < 337.5) {
					out.nwToNnw++;
				} else if (direction >= 337.5 && direction <= 360) {
					out.nnwToN++;
				} else {
					logger.error(
							"[getHistogramDirectionPerStationName] invalid wind direction {} for station {}, record epoch {}",
							direction, stationName, record.epoch);
				}
			}
		} else {
			// if there is no data for this station, clear records counter and return
			out.totalRecords = 0;
		}

		return out;

	}

	@RequestMapping(value = "/station/{stationName}/histogram/speed", produces = "application/json;charset=UTF-8")
	public HistogramSpeed getHistogramWindspeedPerStationName(@PathVariable(required = true) String stationName,
			@RequestParam(value = "from", defaultValue = "0") long from,
			@RequestParam(value = "to", defaultValue = "0") long to) throws NotFoundException, TooLongException {
		return getHistogramSpeed(stationName, from, to, record -> record.windspeed,
				"getHistogramWindspeedPerStationName");
	}

	@RequestMapping(value = "/station/{stationName}/histogram/gusts", produces = "application/json;charset=UTF-8")
	public HistogramSpeed getHistogramWindgistsPerStationName(@PathVariable(required = true) String stationName,
			@RequestParam(value = "from", defaultValue = "0") long from,
			@RequestParam(value = "to", defaultValue = "0") long to) throws NotFoundException, TooLongException {
		return getHistogramSpeed(stationName, from, to, record -> record.windgusts,
				"getHistogramWindgistsPerStationName");
	}

	/**
	 * Common implementation for wind speed and wind gusts histograms. Counts records in
	 * 1 m/s wide bins, from 0 m/s up to 10 m/s, with everything equal or above 10 m/s
	 * in the last bin. Throws {@link NotFoundException} (HTTP 404) if station
	 * doesn't exist and {@link TooLongException} (HTTP 416) if time range is longer than 24 hours.
	 *
	 * @param stationName	station to get the data for
	 * @param from			begin of time range, UNIX epoch in seconds
	 * @param to			end of time range, UNIX epoch in seconds
	 * @param valueGetter	extracts the value (wind speed or wind gusts) from a record
	 * @param methodName	name of the calling method, used in log messages
	 * @return histogram for given station and time range
	 */
	private HistogramSpeed getHistogramSpeed(String stationName, long from, long to,
			ToDoubleFunction<StationDataModel> valueGetter, String methodName) throws NotFoundException, TooLongException {
		HistogramSpeed out = new HistogramSpeed();

		checkStationExists(stationName);

		// if time range hasn't been fully provided
		if (from == 0 || to == 0) {
			// read data for last 2 hours, so set from and to respectively
			to = Instant.now().getEpochSecond();
			from = to - 2 * 3600;
		}

		checkTimeRange(from, to);

		// get station data for chosen time range
		List<StationDataModel> stationData = dataDao.getStationData(stationName, from, to);

		// if there is any data
		if (stationData.size() > 0) {
			// set this to maximum allowed value for long, to be able to find lowest value
			out.oldestRecordTimestamp = Long.MAX_VALUE;

			// set this to how many records we will process
			out.totalRecords = stationData.size();

			// go through every record
			for (StationDataModel record : stationData) {
				// extract this for convenient
				final double speed = valueGetter.applyAsDouble(record);

				// update oldest and newest timestamp found so far
				if (record.epoch < out.oldestRecordTimestamp) {
					out.oldestRecordTimestamp = record.epoch;
				}

				if (record.epoch > out.newestRecordTimestamp) {
					out.newestRecordTimestamp = record.epoch;
				}

				// each bin is 1 m/s wide
				if (speed >= 0 && speed < 1) {
					out.lessThanOne++;
				} else if (speed >= 1 && speed < 2) {
					out.oneToTwo++;
				} else if (speed >= 2 && speed < 3) {
					out.twoToThree++;
				} else if (speed >= 3 && speed < 4) {
					out.threeToFour++;
				} else if (speed >= 4 && speed < 5) {
					out.fourToFive++;
				} else if (speed >= 5 && speed < 6) {
					out.fiveToSix++;
				} else if (speed >= 6 && speed < 7) {
					out.sixToSeven++;
				} else if (speed >= 7 && speed < 8) {
					out.sevenToEight++;
				} else if (speed >= 8 && speed < 9) {
					out.eightToNine++;
				} else if (speed >= 9 && speed < 10) {
					out.nineToTen++;
				} else if (speed >= 10) {
					out.overTen++;
				} else {
					// negative or NaN value
					logger.error("[{}] invalid wind speed {} for station {}, record epoch {}", methodName, speed,
							stationName, record.epoch);
				}
			}
		} else {
			// if there is no data for this station, clear records counter and return
			out.totalRecords = 0;
		}

		return out;
	}

}
