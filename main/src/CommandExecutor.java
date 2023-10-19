import java.util.ArrayList;

public class CommandExecutor {

    private final String file1;
    public CommandExecutor(String file1) {
        this.file1 = file1;
    }
    public ArrayList<String> commandBuilder(){
        ArrayList<String> comando = new ArrayList<>();
        comando.add("java");
        comando.add("-jar");
        comando.add("/Users/diego/Downloads/STAlign-main/STAlign-main/download/STAlign.jar");
        comando.add("-edm");
        comando.add(file1);
        return comando;
    }
    public void execCommand(String file2){
        ArrayList<String> comando = commandBuilder();
        comando.add(file2);

        ProcessBuilder processBuilder = new ProcessBuilder(comando);
        try {
            Process processo1 = processBuilder.start();
            processo1.waitFor(); // Attendere il completamento del processo (se necessario)
        } catch (Exception e) {
            System.out.println("Errore durante l'esecuzione del comando "+e);
        }
    }
}
