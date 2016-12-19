package com.mkoshmanov.training.transport.daodb.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.datamodel.Transport;

public class DriverExtractor implements ResultSetExtractor<List<Driver>> {

	private boolean transportExist;

	@Override
	public List<Driver> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, Driver> map = new HashMap<Long, Driver>();
		checkColumn(rs);
		Driver driver = null;
		while (rs.next()) {
			Long id = rs.getLong("id");
			driver = map.get(id);
			if (driver == null) {
				driver = fillDriver(rs, id);
				map.put(id, driver);
			}
			if (transportExist) {
				Long transportId = rs.getLong("transportId");
				if (transportId != null) {
					Transport transport = fillTransport(rs, transportId);
					driver.addTransport(transport);
				}
			}
		}
		return new ArrayList<Driver>(map.values());
	}

	private Driver fillDriver(ResultSet rs, Long id) throws SQLException {
		Driver driver;
		Transport transport = new Transport();
		transport.setId(rs.getLong("transport_id"));
		driver = new Driver();
		driver.setId(id);
		driver.setFirstName(rs.getString("first_name"));
		driver.setLastName(rs.getString("last_name"));
		driver.setBirthday(rs.getDate("birthday"));
		driver.setTransport(transport);
		return driver;
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
			if (columnName.equals("transportId")) {
				transportExist = true;
			}
		}
	}
}
