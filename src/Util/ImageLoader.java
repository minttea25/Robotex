package Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String path) {
        try {
            File imgFile = new File(path);
            if (!imgFile.exists()) {
                return null;
            } else {
                return ImageIO.read(imgFile);
            }

        } catch (IOException e) {
            //System.out.println("Can not load image: " + path);
            //e.printStackTrace();
            return null;
        }
    }
}
