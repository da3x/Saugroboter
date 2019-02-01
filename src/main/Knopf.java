package main;

public class Knopf {

    private boolean init    = false;
    private Thread  one;
    private int     running;
    private boolean pressed = false;

    private ButtonReader reader;
    
    public Knopf(ButtonReader reader) {
        this.reader = reader;
    }
    
    public void activateReader() {
        one = new Thread() {

            public void run() {
                running = 1;
                while (running == 1) {
                    if (reader.readButtons() > 0) {
                        pressed = true;
                    }
                    if (!init) {
                        System.out.println("Buttonreader: Initialized");
                        init = true;
                    }
                }
            }

        };
        one.start();
    }

    public void deactiveReader() {
        running = 0;
    }

    // ADDED
    public boolean isRunning() {
        return running == 1;
    }
    
    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

}
