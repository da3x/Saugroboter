package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import main.ButtonReader;
import main.Knopf;

@ExtendWith(MockitoExtension.class)
class KnopfMockTest {

    @Mock
    ButtonReader reader;

    @InjectMocks
    Knopf knopf;

    @Test
    void testActivateReaderOFF() {

        Mockito.when(reader.readButtons()).thenReturn(0);

        knopf.activateReader();

        assertFalse(knopf.isPressed());
    }

    @Test
    void testActivateReaderON() throws InterruptedException {

        Mockito.when(reader.readButtons()).thenReturn(1);

        knopf.activateReader();

        Thread.sleep(200);

        assertTrue(knopf.isPressed());
    }

    @Test
    void testDeactivateReader() throws InterruptedException {

        knopf.activateReader();

        Thread.sleep(200);

        assertTrue(knopf.isRunning());

        knopf.deactiveReader();

        assertFalse(knopf.isRunning());

    }
}