import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String path = "/Users/diego/Downloads/siamobraviragazzi";
        File pathInputDirectory = new File(path);

        ArrayList<String> filesToBeAnalyze = new ArrayList<>();

        File[] listOfFiles = pathInputDirectory.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    filesToBeAnalyze.add(file.getName());
                }
            }
        } else {
            System.out.println("Errore nella creazione della lista dei nomi dei file.");
        }
        int i = 1;
        //scorro la directory
        for (File file : listOfFiles) {
            System.out.println("Sono al giro: "+i);
            i++;
            CommandExecutor command = new CommandExecutor(file.getName());
            filesToBeAnalyze.remove(file.getName());
            for (String fileN : filesToBeAnalyze) {
                //System.out.println("Confronto file "+file.getName()+" con file"+fileN);
                command.execCommand(fileN);
            }
        }
    }

}