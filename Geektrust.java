import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Geektrust {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("Please specify a path for input file!");
        }
        else {
            new Geektrust().processFile(filePath);
        }
    }

    private void processFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        StockProcessor processor = new StockProcessorImpl();
        processor.process(br);
        br.close();
        fileReader.close();
    }
}
