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
import com.mkoshmanov.training.transport.datamodel.Transport;

public class StopExtractor implements ResultSetExtractor<List<Stop>> {

	private boolean routeStationExist;
	private boolean transportExist;

	@Override
	public List<Stop> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, Stop> map = new HashMap<Long, Stop>();
		checkColumn(rs);
		Stop stop = null;
		while (rs.next()) {
			Long id = rs.getLong("stopId");
			stop = map.get(id);
			if (stop == null) {
				stop = fillStop(rs, id);
				map.put(id, stop);
			}
			if (routeStationExist) {
				Long routeStationId = rs.getLong("routeStationId");
				if (routeStationId != null) {
					RouteStation routeStation = fillRouteStation(rs, routeStationId);
					stop.addRouteStation(routeStation);
				}
			}
			if (transportExist) {
				Long transportId = rs.getLong("routeId");
				if (transportId != null) {
					Transport transport = fillTransport(rs, transportId);
					stop.addTransport(transport);
				}
			}
		}
		return new ArrayList<Stop>(map.values());
	}

	private Stop fillStop(ResultSet rs, Long id) throws SQLException {
		Stop stop = new Stop();
		stop.setId(id);
		stop.setName(rs.getString("name"));
		
		return stop;
	}

	private RouteStation fillRouteStation(ResultSet rs, Long routeStationId) throws SQLException {
		Stop stop = new Stop();
		stop.setId(rs.getLong("stop_id"));
		Timetable timetable = new Timetable();
		timetable.setId(rs.getLong("timetable_id"));
		RouteStation routeStation = new RouteStation();
		routeStation.setId(routeStationId);
		routeStation.setStop(stop);
		routeStation.setTransportId(rs.getLong("transport_id"));
		routeStation.setSequence(rs.getInt("sequence"));
		routeStation.setTimetable(timetable);
		return routeStation;
	}

	private Transport fillTransport(ResultSet rs, Long transportId) throws SQLException {
		Transport transport = new Transport();
		transport.setId(transportId);
		transport.setVehicleType(rs.getString("vehicle_type"));
		transport.setRouteNumber(rs.getInt("route_number"));
		transport.setRouteName(rs.getString("route_name"));
		return transport;
	}

	private void checkColumn(ResultSet rs) throws SQLException {
		int columnNumber = rs.getMetaData().getColumnCount();
		for (int i = 1; i <= columnNumber; i++) {
			String columnName = rs.getMetaData().getColumnName(i);
			if (columnName.equals("routeStationId")) {
				routeStationExist = true;
			}
			if (columnName.equals("transportId")) {
				transportExist = true;
			}
		}
	}
}
