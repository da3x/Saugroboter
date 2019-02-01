package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import main.Knopf;

class KnopfWrapTest {

    @Test
    void testActivateReaderOFF() {

        Knopf knopf = new Knopf() {

            // geht auch wenn PROTECTED
            public int readButtons() {
                return 0;
            };
        };

        knopf.activateReader();

        assertFalse(knopf.isPressed());
    }

    @Test
    void testActivateReaderON() {

        Knopf knopf = new Knopf() {

            // geht auch wenn PROTECTED
            public int readButtons() {
                return 1;
            };

        };

        knopf.activateReader();

        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertTrue(knopf.isPressed());
    }

    @Test
    void testDeactivateReader() {

        Knopf knopf = new Knopf();

        knopf.activateReader();

        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertTrue(knopf.isRunning());

        knopf.deactiveReader();

        assertFalse(knopf.isRunning());

    }
}

// 0,005s!