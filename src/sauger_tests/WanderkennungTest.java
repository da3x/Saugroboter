package sauger_tests;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import lejos.remote.ev3.RemoteRequestSampleProvider;
import robot.EasyRequestSampleProvider;
import robot.Saugbot;
import robot.SaugbotEV3;

class WanderkennungTest {
	
	//SUT
	@Mock
	private EasyRequestSampleProvider ersp;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		ersp = Mockito.mock(EasyRequestSampleProvider.class);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		//arrange
		Mockito.when(ersp.getSample()).thenReturn(5f);
		
		//act
		float result = ersp.getSample();
		
		//assert
		assertEquals(result,  5f, "result ist nicht 5");
	}
	
	/*
	 System.out.println("<Main>");

		int durchgaenge = 300;
		SaugbotEV3.createInstanceEV3();
		System.out.println(" Instance created.");
		EasyRequestSampleProvider sm = SaugbotEV3.createSensorModeForInfraredSensor();
		System.out.println(" SampleProvider created.");
		System.out.println(" Distanz lesen:");
		for(int i = 0 ; i < durchgaenge ; i++) {
			float gesehen = sm.getSample();
			System.out.println("  "+i+": "+gesehen);
		}
		sm.close();
		System.out.println(" SampleProvider closed.");
		SaugbotEV3.shutDown();
		System.out.println(" Down shut.");
		
		System.out.println("</Main>");
	 * */

}
