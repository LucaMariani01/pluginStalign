import org.apache.commons.cli.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //String aasFilesPath = "/Users/filipporeucci/Desktop/aas1"; //TODO CAMBIARE PATH
        //String stalignPath = "/Users/filipporeucci/Desktop/STAlign-main/download";
        String distanceFileName = "StalignDistance.csv";
        ArrayList<String> filesToBeAnalyze = new ArrayList<>(); //aas files to be analyzed
        Options options = new Options();

        Option aasFiles = new Option("a", "aas", false, "AAS files directory");
        options.addOption(aasFiles);

        Option stalignOption = new Option("s", "stalign", false, "Stalign jar directory");
        options.addOption(stalignOption);

        Option helpOption = new Option("h", "help", false, "Show help");
        options.addOption(helpOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("CommandLineExample", options);
            } else {
                if (cmd.hasOption("a") && cmd.hasOption("s")) {
                    String stalignPath = cmd.getOptionValue("s");
                    String aasFilesPath = cmd.getOptionValue("a");

                    File pathInputDirectory = new File(aasFilesPath);
                    File distanceFile = new File(aasFilesPath+"/"+distanceFileName);
                    File[] listOfFiles = pathInputDirectory.listFiles();  //files contained in the given directory

                    if (listOfFiles == null) {
                        System.out.println("Something went wrong while analyzing the given directory");
                        return;
                    }

                    for (File file : listOfFiles) {
                        if (file.getName().compareTo(distanceFileName) == 0) { //if distance file already exists it will be deleted
                            try {
                                if(!file.delete()) System.out.println("Something went wrong during creation new tsv file");
                            } catch (SecurityException e) { System.out.println("Something went wrong during creation new tsv file"); }
                        }
                        if ((file.isFile()) && (file.getName().contains(".aas"))) filesToBeAnalyze.add(file.getName());
                    }

                    try {
                        FileWriter writer = new FileWriter(distanceFile, true); //writing heading
                        writer.write("Id1\tId2\tDistance\n");
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
                } else System.out.println("ERROR: use -h to view the help.");
            }
        } catch (Exception e) {
            System.err.println("ERROR while parsing options: "+e.getMessage());
        }
    }
}