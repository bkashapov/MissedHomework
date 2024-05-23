import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class TestSingleton {
    @Test
    public void testIsClassSingleton() {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        Assert.assertEquals(singleton1, singleton2);
    }
    @Test
    public void testWrite() {
        File file = new File("testFile.txt");
        Singleton singleton = Singleton.getInstance();
        singleton.write(file, "Hello");
        char[] chars = {'H', 'e', 'l', 'l', 'o'};
        int i = 0;
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {
            while(reader.ready()) {
                Assert.assertEquals(chars[i], (char)reader.read());
                i++;
            }
            Assert.assertEquals(i, chars.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch(ArrayIndexOutOfBoundsException e) {
            Assert.fail();
        }
    }

    @Test
    public void testRead() {
        File file = new File("testFile.txt");
        Singleton singleton = Singleton.getInstance();
        char[] text = {'A', 'b', 'b', 'a', 'o'};
        try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file))) {
            writer.write(text);
            writer.flush();
            List<Character> list = singleton.read(file);
            Assert.assertEquals(list.size(), text.length);
            for(int i = 0; i < list.size(); i++) {
                Assert.assertEquals(text[i], (char)list.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            Assert.fail();
        }



    }
}
