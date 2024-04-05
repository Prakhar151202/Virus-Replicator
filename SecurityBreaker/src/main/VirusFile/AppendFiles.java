import java.io.*;
import java.util.*;

public class AppendFiles {

    public static void copyContent(File a, File b)
            throws Exception
    {
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);

        try {

            int n;

            while ((n = in.read()) != -1) {

                out.write(n);
            }
        }
        finally {
            if (in != null) {

                in.close();
            }

            if (out != null) {
                out.close();
            }
        }
    }
}