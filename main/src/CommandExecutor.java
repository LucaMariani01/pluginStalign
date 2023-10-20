import java.io.*;
import java.util.ArrayList;

public class CommandExecutor {
    private final String file1;

    public CommandExecutor(String file1) {this.file1 = file1;}

    public ArrayList<String> commandBuilder(String aasFilesPath, String stalignPath){
        ArrayList<String> comando = new ArrayList<>();
        comando.add("java");
        comando.add("-jar");
        //comando.add("/Users/diego/Downloads/STAlign-main/STAlign-main/download/STAlign.jar");
        //comando.add("/Users/filipporeucci/Desktop/STAlign-main/download/STAlign.jar"); //TODO CAMBIARE PATH
        comando.add(stalignPath+"/STAlign.jar");
        comando.add("-edm");
        //comando.add("/Users/filipporeucci/Desktop/STAlign-main/examples/aas2/"+file1); //TODO CAMBIARE PATH
        comando.add(aasFilesPath+"/"+file1);
        return comando;
    }

    public void execCommand(String file2, File distanceFile, String aasFilesPath, String stalignPath){
        ArrayList<String> comando = commandBuilder(aasFilesPath, stalignPath);
        String distanceValue = "", linea = "";

        //comando.add("/Users/filipporeucci/Desktop/STAlign-main/examples/aas2/"+file2); //TODO CAMBIARE PATH
        comando.add(aasFilesPath+"/"+file2);
        ProcessBuilder processBuilder = new ProcessBuilder(comando);

        try {
            Process processo1 = processBuilder.start();
            InputStream inputStream = processo1.getInputStream(); // Ottieni l'inputStream del processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); // Leggi l'output usando un BufferedReader

            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                if (linea.contains("Distance")) {
                    String[] parts = linea.split("=");
                    if (parts.length == 2)
                        distanceValue = parts[1].trim();
                }
            }

            try {
                FileWriter writer = new FileWriter(distanceFile, true); //writing distance
                writer.write(this.file1+"\t"+file2+"\t"+distanceValue+"\n");
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            processo1.waitFor(); // Attendere il completamento del processo (se necessario)
        } catch (Exception e) {
            System.out.println("Errore durante l'esecuzione del comando "+e);
        }
    }
}
