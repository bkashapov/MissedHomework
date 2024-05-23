import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton instance;
    private Singleton() {}

    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    public List<Character> read(File file) {
        List<Character> list = new ArrayList<>();
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {
            while(reader.ready()) {
                list.add((char)reader.read());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void write(File file, String message) {
        try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file))) {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
