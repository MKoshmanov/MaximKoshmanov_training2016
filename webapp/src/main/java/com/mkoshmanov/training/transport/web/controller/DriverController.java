package com.mkoshmanov.training.transport.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.services.IDriverService;
import com.mkoshmanov.training.transport.web.model.DriverModel;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Inject
    private IDriverService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DriverModel>> getAll() {
        List<Driver> drivers = service.getAll();

        List<DriverModel> converted = new ArrayList<>();
        for (Driver driver : drivers) {
            converted.add(entity2model(driver));
        }

        return new ResponseEntity<List<DriverModel>>(converted,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{driverId}", method = RequestMethod.GET)
    public ResponseEntity<DriverModel> getById(
            @PathVariable Long driverId) {
    	Driver driver = service.get(driverId);
        return new ResponseEntity<DriverModel>(entity2model(driver),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createNewDriver(
            @RequestBody DriverModel driverModel) {
        service.save(model2entity(driverModel));
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{driverId}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateDriver(
            @RequestBody DriverModel driverModel,
            @PathVariable Long driverId) {
    	Driver driver = model2entity(driverModel);
        driver.setId(driverId);
        service.save(driver);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @RequestMapping(value = "/{driverId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long driverId) {
        service.delete(driverId);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    private DriverModel entity2model(Driver driver) {
    	DriverModel e = new DriverModel();
        e.setFirstName(driver.getFirstName());
        e.setId(driver.getId());
        e.setLastName(driver.getLastName());
        return e;
    }

    private Driver model2entity(DriverModel driverModel) {
    	Driver e = new Driver();
        e.setFirstName(driverModel.getFirstName());
        e.setId(driverModel.getId());
        e.setLastName(driverModel.getLastName());
        return e;
    }

}
