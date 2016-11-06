package com.mkoshmanov.training.transport.services;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkoshmanov.training.transport.daodb.DriverDao;
import com.mkoshmanov.training.transport.daodb.impl.DriverDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class Application {
	public static void main(String[] args) {
		DriverDao driver = new DriverDaoImpl();
		driver.getAll();
}

}