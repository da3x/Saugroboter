package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import main.Knopf;
import main.Sauger;

class SaugerTest {
	
	//SUT
	Sauger sauger;
	
	//Mocks
	Knopf knopf;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		this.knopf = Mockito.mock(Knopf.class);
		this.sauger = new Sauger(knopf, null, null, null, null);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		sauger.main();
		//TODO: verify
	}

}
