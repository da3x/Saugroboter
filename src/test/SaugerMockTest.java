package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.verification.AtLeast;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import lejos.remote.ev3.RemoteRequestPilot;
import main.Knopf;
import main.Sauger;
import robot.EasyRequestSampleProvider;
import robot.Saugbot;

@ExtendWith({ MockitoExtension.class, MyExtension.class} )
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

        // Wir generieren uns erst mal das IST...
        trace(knopf, "order.verify(knopf).activateReader();").activateReader();
        trace(knopf, "order.verify(knopf).deactiveReader();").deactiveReader();
        trace(knopf, "order.verify(knopf).isPressed();").isPressed();
        // NOT USED: trace(knopf, "knopf.isRunning()").isRunning();
        
        trace(pilot, "order.verify(pilot).setLinearSpeed(anyDouble());").setLinearSpeed(anyDouble());
        trace(pilot, "order.verify(pilot).close();").close();
        trace(pilot, "order.verify(pilot).forward();").forward();
        trace(pilot, "order.verify(pilot).stop();").stop();
        trace(pilot, "order.verify(pilot).travel(anyDouble());").travel(anyDouble());
        trace(pilot, "order.verify(pilot).rotate(anyDouble());").rotate(anyDouble());

// DOOF: Bei ConstructorInjection kann er die beiden Sample nicht auseinanderhalten
//       und gibt beiden das gleiche Mock! Man müsste auf FieldInjection verwenden.
//        trace(sample, "sample.close()").close();
//        trace(sample, "sample.getSample()").getSample();
        
        trace(sampleIR, "order.verify(sampleIR).close();").close();
        trace(sampleIR, "order.verify(sampleIR).getSample();").getSample();

        trace(saugbot, "order.verify(saugbot).createInstance();").createInstance();
        trace(saugbot, "order.verify(saugbot).shutDown();").shutDown();
        
        sauger.main();
        
        InOrder order = inOrder(knopf, pilot, sample, sampleIR, saugbot);
        order.verify(saugbot).createInstance();
        order.verify(pilot).setLinearSpeed(anyDouble());
        order.verify(knopf).activateReader();
        order.verify(knopf).isPressed();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(knopf, times(2)).isPressed();
//        order.verify(knopf).isPressed();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(knopf, times(2)).isPressed();
//        order.verify(knopf).isPressed();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(knopf, times(2)).isPressed();
//        order.verify(knopf).isPressed();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(knopf,times(2)).isPressed();
//        order.verify(knopf).isPressed();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(knopf, times(2)).isPressed();
//        order.verify(knopf).isPressed();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).forward();
        order.verify(knopf).isPressed();
        order.verify(sampleIR).getSample();
        order.verify(pilot).stop();
        order.verify(knopf).isPressed();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).travel(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(pilot).rotate(anyDouble());
        order.verify(sampleIR).getSample();
        order.verify(knopf).isPressed();
        order.verify(knopf).deactiveReader();
        order.verify(sampleIR).close();
        order.verify(pilot).close();
        order.verify(sampleIR).close();
        order.verify(saugbot).shutDown();
    }

    private <T> T trace(T mock, String msg) {
        return doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                System.out.println(msg);
                return null;
            }
        }).when(mock);
    }

}
