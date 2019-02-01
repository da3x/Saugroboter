package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import lejos.remote.ev3.RemoteRequestPilot;
import lejos.robotics.Color;
import main.Knopf;
import main.Sauger;
import robot.EasyRequestSampleProvider;
import robot.Saugbot;

class MainTest {
	
	//SUT
	private Sauger sauger;
	
	//MOCKS
	private RemoteRequestPilot pilot;
	private EasyRequestSampleProvider sampleIR;
	private EasyRequestSampleProvider sample;
	private Knopf knopf;
	private Saugbot saugbot;
	
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test

	public void saugerFaehrtEineSpurUndSiehtDannNurNochSchwarz() {

		InOrder inOrder = Mockito.inOrder(saugbot, pilot, knopf, sample, sampleIR);

		Mockito.when(saugbot.createPilot()).thenReturn(pilot);

		Mockito.when(saugbot.createSampleProviderForColorSensor()).thenReturn(sample);

		Mockito.when(saugbot.createSensorModeForInfraredSensor()).thenReturn(sampleIR);

		Mockito.when(sample.getSample()).thenReturn((float) Color.WHITE, (float) Color.BLACK);

		Mockito.when(sampleIR.getSample()).thenReturn(30f);

		Mockito.when(knopf.isPressed()).thenReturn(false);

		float requestedSpeed = 15f;

		int GRADZAHL = 85;

		int WENDEWEG = 5;

		sauger.main();

		// start()

		inOrder.verify(saugbot).createPilot();

		inOrder.verify(pilot).setLinearSpeed(requestedSpeed);

		inOrder.verify(saugbot).createSampleProviderForColorSensor();

		inOrder.verify(saugbot).createSensorModeForInfraredSensor();

		inOrder.verify(knopf).activateReader();

		// forward()

		inOrder.verify(pilot).forward();

		inOrder.verify(sample).getSample();

		inOrder.verify(sampleIR).getSample();

		inOrder.verify(sample).getSample();

		inOrder.verify(pilot).stop();

		// turn()

		inOrder.verify(pilot).rotate(GRADZAHL * -1);

		inOrder.verify(sample).getSample();

		inOrder.verify(pilot).travel(WENDEWEG);

		inOrder.verify(sample).getSample();

		inOrder.verify(pilot).rotate(GRADZAHL * -1);

		inOrder.verify(sample).getSample();

		// forward()

		inOrder.verify(pilot).forward();

		inOrder.verify(sample).getSample();

		inOrder.verify(pilot).stop();

		// turn_RIGHT()

		inOrder.verify(pilot).rotate(GRADZAHL);

		inOrder.verify(sample).getSample();

		inOrder.verify(pilot).travel(WENDEWEG);

		inOrder.verify(sample).getSample();

		inOrder.verify(pilot).rotate(GRADZAHL);

		inOrder.verify(sample).getSample();

		// main()

		inOrder.verify(knopf).deactiveReader();

		// end()

		inOrder.verify(sample).close();

		inOrder.verify(pilot).close();

		inOrder.verify(sampleIR).close();

		inOrder.verify(saugbot).shutDown();

	}

}
