package KNet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public abstract class KNetProtocol {
    protected final static String kNewLine = ":n:";
    protected final static long kDefaultWaitTimeoutMS = 5000;

    protected String NextLine(InputStream stream, Scanner reader) {
        String toReturn = "";
        try {
            if (stream.available() > 0) {
                toReturn = reader.nextLine();
            }
        } catch (IOException e) {
            // Ignore this
        }

        return toReturn.replaceAll("\n", kNewLine) + "\n";
    }

    public abstract void WriteOut(String message);

    public abstract String NextIn();

    public abstract String WaitForNext(long timeoutMS);

    public abstract String WaitForNext();
}
