package io.brainmachine.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import io.brainmachine.Application;
import io.brainmachine.entities.Driver;
import io.brainmachine.entities.PaymentMethod;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
public class DriverRepositoryTest {

	@Autowired
	private DriverRepository driverRepository;

	@Test
	public void whenSavingDriverThenAvailableOnRetrieval() throws Exception {
		final Driver driver = new Driver(UUID.randomUUID().toString(), this.allPaymentMethods());
		this.driverRepository.save(driver);
		final Driver retrievedDriver = this.driverRepository.findOne(driver.getUuid());
		assertEquals(driver.getUuid(), retrievedDriver.getUuid());
	}

	@Test
	public void whenUpdatingDriverThenAvailableOnRetrieval() throws Exception {
		final Driver driver = new Driver(UUID.randomUUID().toString(), this.allPaymentMethods());
		this.driverRepository.save(driver);
		driver.setPaymentMethods(StringUtils.arrayToDelimitedString(new PaymentMethod[] { PaymentMethod.CASH }, ";"));
		this.driverRepository.save(driver);
		final Driver retrievedDriver = this.driverRepository.findOne(driver.getUuid());
		assertEquals(driver.getPaymentMethods(), retrievedDriver.getPaymentMethods());
	}

	@Test
	public void whenDeletingDriverThenNotAvailableOnRetrieval() throws Exception {
		final Driver driver = new Driver(UUID.randomUUID().toString(), this.allPaymentMethods());
		this.driverRepository.save(driver);
		this.driverRepository.delete(driver.getUuid());
		final Driver retrievedDriver = this.driverRepository.findOne(driver.getUuid());
		assertNull(retrievedDriver);
	}

	private String allPaymentMethods() {
		return StringUtils.arrayToDelimitedString(PaymentMethod.values(), ";");
	}
}
