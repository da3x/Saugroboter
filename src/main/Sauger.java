package main;

import external_libs.EdgeReverse;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import robot.EasyRequestSampleProvider;
import robot.Saugbot;

public class Sauger {
	
	/*
	 * Lejos Staubsauger-Roboter v1.0.21 
	 * 14.04.2017 by John Doe 
	 * * * last change: 03.09.2017 
	 * * * TODO: + variable for MaxPaths 
	 * - constants for turning * -
	 * Erkennung f�r "Kein Untergrund" 
	 * - Sensor f�r Hindernisse und *
	 * Hindernissumfahrung *
	 */
	private final int GRADZAHL = 85;
	private final int WENDEWEG = 5;
	private final int MIN_DISTANCE = 25;

	private final Knopf knopf;

	// Robot Connection
    private final RemoteRequestPilot        pilot;
    private final EasyRequestSampleProvider sample;
    private final EasyRequestSampleProvider sampleIR;
    private final Saugbot                   saugbot;

    /** private RegulatedMotor motor; **/

    private boolean stopWeilKeinUntergrundErkannt = false;

    public Sauger(Knopf knopf,
            RemoteRequestPilot pilot,
            EasyRequestSampleProvider cp,
            EasyRequestSampleProvider ip,
            Saugbot bot) {
        this.knopf    = knopf;
        this.pilot    = pilot;
        this.sample   = cp;
        this.sampleIR = ip;
        this.saugbot  = bot;
    }

	private String farbenname(int color) {
		String answer = "";
		switch (color) {
		// case Color.BLACK:
		// answer = "BLACK";
		// break;
		// case Color.GREEN:
		// answer = "GREEN";
		// break;
		case Color.NONE:
			answer = "NONE";
			break;
		case Color.WHITE:
			answer = "WHITE";
			break;
		// case Color.YELLOW:
		// answer = "YELLOW";
		// break;
		default:
			answer = Integer.toString(color);
		}

		return answer;
	}

	private int start() {
		/* Initialisierung vom Roboter */
		try {
			
			// neue Instanz von EV3 muss erstellt werden:
			saugbot.createInstance();
			// Piloten erstellen:
			// Wird f�r Bewegungen des Roboters gebraucht
//			pilot = saugbot.createPilot();
			// Gesdchwindigkeit vom Pilot runtersetzen:
			pilot.setLinearSpeed(15f);
			// SampleProvider erstellen:
			// Wird f�r das Auslesen des Farbsensors gebraucht
//			sample = saugbot.createSampleProviderForColorSensor();
//			sampleIR = saugbot.createSensorModeForInfraredSensor();
			// Zusatzmotor erstellen:
			// Wird zum Saugen gebraucht
			/** motor = saugbot.createMotor('D'); **/
			// Bei Erfolg wird eins returned:
			return 1;
		} catch (Exception e) {
			// Fehlermeldung in Konsole ausgeben:
			System.out.println(e.getMessage());
			// Roboter beenden:
			end();
		}
		// Wird nur erreicht, wenn etwas fehlschl�gt:
		return -1;
	}

	private int end() {
		/* Schliessen der Roboterverbindung */
		try {
			// SampleProvider schlie�en:
			// Wird close() nicht ausgef�hrt, muss der Roboter
			// neu gestartet werden.
			sample.close();
		} catch (Exception e) {
			// Fehlermeldung in Konsole ausgeben:
			System.out.println(e.getMessage());
		}
		try {
			// Piloten schlie�en:
			// Wird close() nicht ausgef�hrt, muss der Roboter
			// neu gestartet werden.
			pilot.close();
		} catch (Exception e) {
			// Fehlermeldung in Konsole ausgeben:
			System.out.println(e.getMessage());
		}
		try {
			// Motor schlie�en:
			// Wird close() nicht ausgef�hrt, muss der Roboter
			// neu gestartet werden.
			/** motor.close(); **/
		} catch (Exception e) {
			// Fehlermeldung in Konsole ausgeben:
			System.out.println(e.getMessage());
		}
		try {
			sampleIR.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			saugbot.shutDown();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Wird nur erreicht, wenn etwas fehlschl�gt:
		return -1;
	}

	private void forward() {
		/* Roboter f�hrt bis zum Ende des Raums */
		pilot.forward();
		float gesehen, distance;
		while (true) {
			if (knopf.isPressed()) {
				pilot.stop();
				break;
			}
			
			gesehen = sample.getSample();
			if (!farbenname((int) gesehen).equals("WHITE")) {
				pilot.stop();
				stopWeilKeinUntergrundErkannt = farbenname((int) gesehen).equals("NONE");
				break;
			}
			distance = sampleIR.getSample();
			if (checkWall(distance)) {
				pilot.stop();
				turnRight();
				pilot.travel(30);
				pilot.rotate(-85);
				sampleIR.getSample();
				if (checkWall(distance)) {
					break;
				} else {
					pilot.forward();
				}
			}
		}

		if (stopWeilKeinUntergrundErkannt) {
			while (!EdgeReverse.run(pilot))
				;
			gesehen = sample.getSample();
			if (farbenname((int) gesehen).equals("WHITE"))
				stopWeilKeinUntergrundErkannt = false;
		}
	}

	private boolean checkWall(float distance) {
		return distance < MIN_DISTANCE;
	}

	private void turnRight() {
		if (!knopf.isPressed()) {
			pilot.rotate(GRADZAHL);
		}
	}

	private boolean checkKante() {
		float gesehen = sample.getSample();
		if (gesehen == -1.0f)
			stopWeilKeinUntergrundErkannt = true;
		return stopWeilKeinUntergrundErkannt;
	}

	private void turn() {
		/* Roboter wendet (linksdrehung) */
		if (!knopf.isPressed()) {
			pilot.rotate(GRADZAHL * -1);
			checkKante();
			pilot.travel(WENDEWEG);
			checkKante();
			pilot.rotate(GRADZAHL * -1);
			checkKante();
		}
	}

	private void turn_RIGHT() {
		/* Roboter wendet nach Rechts */
		if (!knopf.isPressed()) {
			pilot.rotate(GRADZAHL);
			checkKante();
			pilot.travel(WENDEWEG);
			checkKante();
			pilot.rotate(GRADZAHL);
			checkKante();
		}
	}

	public void main() {

		int maximale_wege_des_roboters_bevor_stopp = 6;

		if (start() > 0) {
			
			knopf.activateReader();

			/** motor.forward(); **/
			do {

				if (stopWeilKeinUntergrundErkannt || knopf.isPressed())
					maximale_wege_des_roboters_bevor_stopp = -1;

				maximale_wege_des_roboters_bevor_stopp--;

				forward();
				if (!stopWeilKeinUntergrundErkannt) {
					turn();
					if (stopWeilKeinUntergrundErkannt)
						break;
					forward();
					if (!stopWeilKeinUntergrundErkannt)
						turn_RIGHT();
				}

				if (stopWeilKeinUntergrundErkannt || knopf.isPressed())
					maximale_wege_des_roboters_bevor_stopp = -1;
			} while (maximale_wege_des_roboters_bevor_stopp > 0);
			/** motor.stop(); **/

			knopf.deactiveReader();
			end();
		}
	}

	

}
