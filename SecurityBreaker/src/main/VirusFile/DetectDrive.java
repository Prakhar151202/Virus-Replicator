import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileSystemView;

public class DetectDrive
{
    public String USBDetect() {
        String driveLetter = "";
        FileSystemView fsv = FileSystemView.getFileSystemView();

        File[] f = File.listRoots();
        for (int i = 0; i < f.length; i++) {
            String drive = f[i].getPath();
            String displayName = fsv.getSystemDisplayName(f[i]);
            String type = fsv.getSystemTypeDescription(f[i]);
            boolean isDrive = fsv.isDrive(f[i]);
            boolean isFloppy = fsv.isFloppyDrive(f[i]);
            boolean canRead = f[i].canRead();
            boolean canWrite = f[i].canWrite();

            if (canRead && canWrite && !isFloppy && isDrive && (type.toLowerCase().contains("removable") || type.toLowerCase().contains("rimovibile"))) {
                //log.info("Detected PEN Drive: " + drive + " - "+ displayName);
                driveLetter = drive;
                break;
            }
        }

        /*if (driveLetter.equals(""))
        {
            System.out.println("Not found!");
        } 
        else 
        {
            System.out.println(driveLetter);
        }
        */

        //System.out.println(driveLetter);
        return driveLetter;
    }
}