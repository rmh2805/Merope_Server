package Common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public abstract class Merope_Protocol {

    protected String NextLine (InputStream stream, Scanner reader) {
        String toReturn = "";
        try {
            if (stream.available () > 0) {
                toReturn = reader.nextLine ();
            }
        } catch (IOException e) {
            // Ignore this
        }

        return toReturn + "\n";
    }
}
