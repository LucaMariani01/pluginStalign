import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //String path = "/Users/diego/Downloads/siamobraviragazzi";
        String aasFilesPath = "/Users/filipporeucci/Desktop/STAlign-main/examples/aas2"; //TODO CAMBIARE PATH
        String stalignPath = "/Users/filipporeucci/Desktop/STAlign-main/download";
        String distanceFileName = "StalignDistance.tsv";
        File pathInputDirectory = new File(aasFilesPath);
        File distanceFile = new File(aasFilesPath+"/"+distanceFileName);
        ArrayList<String> filesToBeAnalyze = new ArrayList<>(); //aas files to be analyzed
        File[] listOfFiles = pathInputDirectory.listFiles();  //files contained in the given directory

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.getName().compareTo(distanceFileName) == 0) { //if distance file already exists it will be deleted
                    try {
                        file.delete();
                    } catch (SecurityException e) {
                        System.err.println("Errore di sicurezza durante l'eliminazione del file: " + e.getMessage());
                    }
                }
                if ((file.isFile()) && (file.getName().contains(".aas"))) filesToBeAnalyze.add(file.getName());
            }
        } else System.out.println("Errore nella creazione della lista dei nomi dei file.");

        try {
            FileWriter writer = new FileWriter(distanceFile, true); //writing heading
            writer.write("File1\tFile2\tDistance\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (File file : listOfFiles) {
            if(file.getName().contains(".aas")) {
                CommandExecutor command = new CommandExecutor(file.getName());
                filesToBeAnalyze.remove(file.getName());
                for (String fileN : filesToBeAnalyze)
                    if (fileN.contains(".aas")) command.execCommand(fileN, distanceFile, aasFilesPath, stalignPath);
            }
        }
    }

}