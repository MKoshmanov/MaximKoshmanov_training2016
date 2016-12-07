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

	@Override
	public List<Driver> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, Driver> map = new HashMap<Long, Driver>();
		Driver driver = null;
		while (rs.next()) {
			Long id = rs.getLong("id");
			driver = map.get(id);
			if (driver == null) {
				driver = new Driver();
				driver.setId(id);
				driver.setFirstName(rs.getString("first_name"));
				driver.setLastName(rs.getString("last_name"));
				driver.setBirthday(rs.getDate("birthday"));
				driver.setLicenseCategory(rs.getString("licence_category"));
				map.put(id, driver);
			}
			Long transportId = rs.getLong("id");
			if (transportId != null) {
				Transport transport = new Transport();
				transport.setId(transportId);
				transport.setVehicleType(rs.getString("vehicle_type"));
				transport.setDriverId(rs.getLong("driver_id"));
				transport.setRouteId(rs.getLong("route_Id"));
				driver.addTransport(transport);
			}
		}
		return new ArrayList<Driver>(map.values());
	}
}
