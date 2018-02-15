package io.brainmachine.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("drivers")
public class Driver implements Serializable {

	private static final long serialVersionUID = -1944051637182261725L;

	@Id
	private String uuid;
	private String paymentMethods;
	
	public Driver(String uuid, String paymentMethods) {
		super();
		this.uuid = uuid;
		this.paymentMethods = paymentMethods;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(String paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

}
