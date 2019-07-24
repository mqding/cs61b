import synthesizer.GuitarString;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* create guitar strings array.*/
        synthesizer.GuitarString[] CONCERT = new synthesizer.GuitarString[37];
        for(int i = 0; i < 37; i += 1) {
            CONCERT[i] = new GuitarString(440 * Math.pow(2,(i-24)/12));
        }

        while (true) {
            /* check if the user has typed a key; if so, compare it to the keyboard String. */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    CONCERT[index].pluck();
                }
            }

        /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < CONCERT.length; i += 1) {
                sample += CONCERT[i].sample();
            }
        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < CONCERT.length; i += 1) {
                CONCERT[i].tic();
            }
        }
    }
}

