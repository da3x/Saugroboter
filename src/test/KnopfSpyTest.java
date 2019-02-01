package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import main.Knopf;

@ExtendWith(MockitoExtension.class)
class KnopfSpyTest {

    @Spy
    Knopf knopf;

    @Test
    void testActivateReader() {

        // geht nur wenn PUBLIC
        Mockito.when(knopf.readButtons()).thenReturn(0);

        knopf.activateReader();
    }

}

// 3,317s!