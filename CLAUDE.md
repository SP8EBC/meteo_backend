# CLAUDE.md

## Data guarantees

- Wind values read from the database (`windspeed`, `windgusts` in `StationDataModel`, table `data_station`) are guaranteed by the database to always be valid floating point numbers. Missing NaN or negative-value checks on them are not bugs and shouldn't be flagged in code reviews.
- NaN produced by calculations (for example 0/0 when averaging an empty histogram bin) is still a real bug: Jackson serializes it as a bare `NaN`, which is not valid JSON.
