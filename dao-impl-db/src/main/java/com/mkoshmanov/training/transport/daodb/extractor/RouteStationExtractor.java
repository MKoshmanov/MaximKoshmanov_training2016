package com.mkoshmanov.training.transport.daodb.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.mkoshmanov.training.transport.datamodel.RouteStation;
import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.datamodel.Timetable;

public class RouteStationExtractor implements ResultSetExtractor<List<RouteStation>> {

	private boolean timetableExist;
	private boolean stopExist;

	@Override
	public List<RouteStation> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, RouteStation> map = new HashMap<Long, RouteStation>();
		checkColumn(rs);
		RouteStation routeStation = null;
		while (rs.next()) {
			Long id = rs.getLong("routeStationId");
			routeStation = map.get(id);
			if (routeStation == null) {
				routeStation = new RouteStation();
				Stop stop = new Stop();
				stop.setId(rs.getLong("stop_id"));
				/*Timetable timetable = new Timetable();
				timetable.setId(rs.getLong("timetable_id"));*/
				routeStation.setId(id);
				routeStation.setStop(stop);
				//routeStation.setTimetable(timetable);
				routeStation.setSequence(rs.getInt("sequence"));
				routeStation.setTransportId(rs.getLong("transport_id"));
				map.put(id, routeStation);
			}
			if (stopExist) {
				Long stopId = rs.getLong("stop_id");
				if (stopId != null) {
					Stop stop = new Stop();
					stop.setId(stopId);
					stop.setName(rs.getString("name_en"));
					routeStation.addStop(stop);
				}
			}
			/*if (timetableExist) {
				Long timetableId = rs.getLong("timetable_id");
				if (timetableId != null) {
					Timetable timetable = new Timetable();
					timetable.setId(timetableId);
					timetable.setArrivalTime(rs.getTime("arrival_time"));
					routeStation.addTimetable(timetable);
				}*/
			//}
		}
		return new ArrayList<RouteStation>(map.values());
	}

	private void checkColumn(ResultSet rs) throws SQLException {
		int columnNumber = rs.getMetaData().getColumnCount();
		for (int i = 1; i <= columnNumber; i++) {
			String columnName = rs.getMetaData().getColumnName(i);
			if (columnName.equals("stopId")) {
				stopExist = true;
			}
			if (columnName.equals("timetableId")) {
				timetableExist = true;
			}
		}
	}
}
