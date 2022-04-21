package com.backend.dev;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.dev.errors.ErrorResponse;
import com.backend.dev.errors.InvalidEndpointException;

@RunWith(SpringRunner.class)
@SpringBootTest
class ErrorResponseTest {

	@Test
	void errorResponseConstructorTest() {
		ErrorResponse objError = new ErrorResponse();
		objError.setStatus(404);
		objError.setMessage("error");
		int outputStatus = objError.getStatus();
		String outputMsg = objError.getMessage();
		assertEquals(404, outputStatus);
		assertEquals("error", outputMsg);
	}

	@Test
	void testParamConstruction() {
		ErrorResponse obj = new ErrorResponse(418, "bad credentials");
		int outputStatus = obj.getStatus();
		String outputMsg = obj.getMessage();
		assertEquals(418, outputStatus);
		assertEquals("bad credentials", outputMsg);
	}
	
	@Test
	void testToString() {
		ErrorResponse mainObj = new ErrorResponse(418, "bad credentials");
		ErrorResponse obj2 = new ErrorResponse(418, "bad credentials");
		int outputStatus = obj2.getStatus();
		String outputMsg = obj2.getMessage();
		assertEquals(418, outputStatus);
		assertEquals("ErrorResponse{" +
                "status=" + outputStatus +
                ", message='" + outputMsg + '\'' +
                '}', mainObj.toString());
	}
	
	@Test
	void testInvalidPtException() {
		InvalidEndpointException invalidObj = new InvalidEndpointException("Endpoint does not exist");
		String outputMsg = invalidObj.getMessage();
		assertEquals("Endpoint does not exist", outputMsg);
	}
	
	@Test
	void testInvalidPtConst() {
		InvalidEndpointException constObj = new InvalidEndpointException("Endpoint does not exist", catchThrowable(null));
		String outputMsg = constObj.getMessage();
		constObj.getCause();
		assertEquals("Endpoint does not exist", outputMsg);
	}
	
	@Test
	void testCause() {
		Throwable obj = new Throwable("test");
		InvalidEndpointException constObj = new InvalidEndpointException(obj);
		String outputMsg = constObj.getMessage();
		constObj.getCause();
		assertEquals("java.lang.Throwable: test", outputMsg);
	}
}

