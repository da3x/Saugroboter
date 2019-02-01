package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lejos.remote.ev3.RemoteRequestPilot;
import main.Knopf;
import main.Sauger;
import robot.EasyRequestSampleProvider;
import robot.Saugbot;

@ExtendWith(MockitoExtension.class)
class SaugerMockTest {

    @Mock
    Knopf                     knopf;
    @Mock
    RemoteRequestPilot        pilot;
    @Mock
    EasyRequestSampleProvider sample;
    @Mock
    EasyRequestSampleProvider sampleIR;
    @Mock
    Saugbot                   saugbot;

    @InjectMocks
    Sauger sauger;

    @Test
    void testMain() {
        
        sauger.main();
    }

}
