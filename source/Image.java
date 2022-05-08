package sources;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Image {
	public static BufferedImage load(String location) {
		try {
			return ImageIO.read(Image.class.getResource(location));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}