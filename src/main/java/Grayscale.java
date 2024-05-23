import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Grayscale {

    public static void main(String[] args) throws IOException {

        BufferedImage bi = read("C:\\Users\\bulat\\Downloads\\picture4.jpg");
        grayscale(bi);
        blackWhite(bi);


    }

    public static BufferedImage read(String file) throws IOException {
        BufferedImage picture = ImageIO.read(new File(file));
        return picture;
    }

    public static void grayscale(BufferedImage picture) throws IOException {

        BufferedImage out = new BufferedImage(picture.getWidth(), picture.getHeight(), TYPE_INT_RGB);
        Thread[] threads = new Thread[picture.getWidth()];
        for(int i = 0; i < threads.length; ++i) {
            int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < picture.getHeight(); ++j) {
                    int alpha = (picture.getRGB(row,j) & 0xFF000000) >> 24;
                    int red = (picture.getRGB(row,j) & 0x00FF0000) >> 16;
                    int green = (picture.getRGB(row,j) & 0x0000FF00) >> 8;
                    int blue = picture.getRGB(row,j) & 0x000000FF;
                    int grayscale = (int)(0.3 * red + 0.59 * green + 0.11 * blue);

                    int rgb = (alpha & 0xff) << 24 | (grayscale & 0xff) << 16 | (grayscale & 0xff) << 8 | (grayscale & 0xff);
                    out.setRGB(row, j, rgb);
                }
            });
            threads[i].start();
        }
        ImageIO.write(out, "JPG", new FileOutputStream(new File("C:\\Users\\bulat\\Downloads\\test1.jpg")));
    }
    public static void blackWhite(BufferedImage picture) throws IOException {
        BufferedImage out = new BufferedImage(picture.getWidth(), picture.getHeight(), TYPE_INT_RGB);
        Thread[] threads = new Thread[picture.getWidth()];
        for(int i = 0; i < threads.length; ++i) {
            int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < picture.getHeight(); ++j) {
                    int alpha = (picture.getRGB(row,j) & 0xFF000000) >> 24;
                    int red = (picture.getRGB(row,j) & 0x00FF0000) >> 16;
                    int green = (picture.getRGB(row,j) & 0x0000FF00) >> 8;
                    int blue = picture.getRGB(row,j) & 0x000000FF;
                    int grayscale = (int)(0.3 * red + 0.59 * green + 0.11 * blue) < 128 ? 0 : 255;
                    int rgb = (alpha & 0xff) << 24 | (grayscale & 0xff) << 16 | (grayscale & 0xff) << 8 | (grayscale & 0xff);
                    out.setRGB(row, j, rgb);
                }
            });
            threads[i].start();
        }
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        ImageIO.write(out, "JPG", new FileOutputStream(new File("C:\\Users\\bulat\\Downloads\\test2.jpg")));

    }

}
