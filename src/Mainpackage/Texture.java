package Mainpackage;
import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * a 2d texture
 * 
 * @author Nicolas Winkler
 * 
 */
public class Texture {
	/** texture index set by opengl */
	public int index;

	/** width of the texture */
	public int width;

	/** height of the texture */
	public int height;

	public enum Quality {
		LINEAR, NEAREST
	}

	/**
	 * default constructor
	 */
	private Texture() {
	}

	/**
	 * texture with width and height
	 * 
	 * @param width
	 * @param height
	 */
	private Texture(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * "directisizes" a {@link ByteBuffer}
	 * 
	 * @param bb
	 *            the {@link ByteBuffer} to create direct
	 * @return a {@link ByteBuffer} which you can invoke {@code isDirect()} on
	 *         and it returns {@code true}
	 */
	private static ByteBuffer getDirect(ByteBuffer bb) {
		if (bb.isDirect())
			return bb;
		ByteBuffer direct = ByteBuffer.allocateDirect(bb.remaining());
		direct.put(bb);
		return direct;
	}

	/**
	 * creates a {@link FullTexture} from the specified arguments
	 * 
	 * @param width
	 *            the width of the texture
	 * @param height
	 *            the height of the texture
	 * @param pixels
	 *            pixel data
	 * @param colorCode
	 *            GL_ALPHA, GL_RGB, GL_RGBA
	 * @return a texture
	 * 
	 */
	public static Texture createTexture(int width, int height, byte[] pixels,
			int colorMode) {
		Texture tex = new Texture(width, height);

		ByteBuffer dir = getDirect(ByteBuffer.wrap(pixels));

		dir.flip();

		glEnable(GL_TEXTURE_2D);
		tex.index = glGenTextures();

		glBindTexture(GL_TEXTURE_2D, tex.index);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, // Immer GL_TEXTURE_2D
				0, // keine Mipmaps
				colorMode, // Das gewünschte Format (GL_RGB, GL_RGBA...)
				width, height, // Breite und Höhe
				0, // Grenze
				colorMode, // Das Format der Pixels.
				GL_UNSIGNED_BYTE, // GL_UNSIGNED_BYTE, weil die Daten als
				// unsigned bytes gespeichert sind
				dir // Die Pixeldaten
		);

		glDisable(GL_TEXTURE_2D);
		return tex;
	}

	public static Texture createTexture(BufferedImage image) {
		int colorMode = GL_RGB;
		ColorModel cm = image.getColorModel();
		//System.out.println("Image Colormodel: " + cm);
		if (cm.hasAlpha()) {
			colorMode = GL_RGBA;
		}

		int width = image.getWidth();
		int height = image.getHeight();

		// byte[] pixels = new byte[texture.width * texture.height];
		DataBuffer dataBuffer = image.getRaster().getDataBuffer();
		byte[] pixels = null;

		if (dataBuffer instanceof DataBufferByte) {
			pixels = ((DataBufferByte) image.getRaster().getDataBuffer())
					.getData();
		} else {
			throw new RuntimeException(
					"Das Bildformat wird nöd untrschtüzt! Gopf!");
		}

		if (colorMode == GL_RGBA)
			for (int i = 0; i < pixels.length; i += 4) {
				byte temp = pixels[i + 0];
				pixels[i + 0] = pixels[i + 3];
				pixels[i + 3] = temp;

				temp = pixels[i + 1];
				pixels[i + 1] = pixels[i + 2];
				pixels[i + 2] = temp;
			}
		else
			for (int i = 0; i < pixels.length; i += 3) {
				byte temp = pixels[i + 0];
				pixels[i + 0] = pixels[i + 2];
				pixels[i + 2] = temp;
			}

		/*
		 * for (int i = 0; i < pixels.length / 2; i++) { byte temp = pixels[i];
		 * pixels[i] = pixels[pixels.length - i - 1]; pixels[pixels.length - i -
		 * 1] = temp; }
		 */

		return Texture.createTexture(width, height, pixels, colorMode);
	}

	/**
	 * reads an image from an {@link InputStream}
	 * 
	 * @param input
	 *            the image input
	 * @return the texture generated from the input image
	 * @throws IOException
	 */
	public static Texture loadImage(InputStream input) throws IOException {
		BufferedImage image = null;
		image = ImageIO.read(input);

		return createTexture(image);
	}

	/**
	 * test function for debug uses
	 * 
	 * @return a 2D texture (128 x 128)
	 */
	public static Texture createTest() {
		Texture tex = new Texture(128, 128);

		ByteBuffer dir = ByteBuffer.allocateDirect(128 * 128 * 4);

		Random rnd = new Random();
		while (dir.hasRemaining()) {
			dir.put((byte) (rnd.nextInt() & 0xFF));
		}

		dir.flip();

		glEnable(GL_TEXTURE_2D);
		tex.index = glGenTextures();

		glBindTexture(GL_TEXTURE_2D, tex.index);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

		glTexImage2D(GL_TEXTURE_2D, // Immer GL_TEXTURE_2D
				0, // keine Mipmaps
				GL_RGBA, // Das gewünschte Format (GL_RGB, GL_RGBA...)
				tex.width, tex.height, // Breite und Höhe
				0, // Grenze
				GL_RGBA, // Das Format der Pixels.
				GL_UNSIGNED_BYTE, // GL_UNSIGNED_BYTE, weil die Daten als
				// unsigned bytes gespeichert sind
				dir // Die Pixeldaten
		);

		glDisable(GL_TEXTURE_2D);
		return tex;
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, this.index);
	}

	/**
	 * changes the algorithm used to render the texture.
	 */
	public void setQuality(Quality q) {
		glEnable(GL_TEXTURE_2D);
		bind();

		int arg;
		switch (q) {
		case LINEAR:
			arg = GL_LINEAR;
			break;
		case NEAREST:
			arg = GL_NEAREST;
			break;
		default:
			arg = 0;
		}

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, arg);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, arg);

		glDisable(GL_TEXTURE_2D);
	}

	/**
	 * displays the texture at a specific position with specific bounds
	 */
	public void displayRect(float x, float y, float width, float height) {
		glEnable(GL_BLEND);
		// glDisable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);

		bind();

		glBegin(GL_TRIANGLE_STRIP);

		glTexCoord2f(0f, 0f);
		glVertex2f(x, y);

		glTexCoord2f(1f, 0f);
		glVertex2f(x + width, y);

		glTexCoord2f(0f, 1f);
		glVertex2f(x, y + height);

		glTexCoord2f(1f, 1f);
		glVertex2f(x + width, y + height);

		glEnd();

		glDisable(GL_TEXTURE_2D);
	}

	public void displayRect(float x, float y, float width, float height,
			float texx, float texy, float texsize) {
		glEnable(GL_BLEND);
		// glDisable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);

		bind();

		glBegin(GL_TRIANGLE_STRIP);

		glTexCoord2f(texx, texy);
		glVertex2f(x, y);

		glTexCoord2f(texx + texsize, texy);
		glVertex2f(x + width, y);

		glTexCoord2f(texx, texy + texsize);
		glVertex2f(x, y + height);

		glTexCoord2f(texx + texsize, texy + texsize);
		glVertex2f(x + width, y + height);

		glEnd();

		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
	}

	/**
	 * displays the texture at a specific position with specific bounds
	 */
	public void displayQuad(float x1, float y1, float x2, float y2, float x3,
			float y3, float x4, float y4) {
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);

		bind();

		glBegin(GL_TRIANGLE_STRIP);

		glTexCoord2f(0f, 0f);
		glVertex2f(x1, y1);

		glTexCoord2f(1f, 0f);
		glVertex2f(x2, y2);

		glTexCoord2f(0f, 1f);
		glVertex2f(x4, y4);

		glTexCoord2f(1f, 1f);
		glVertex2f(x3, y3);

		glEnd();

		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
	}

	/**
	 * When the texture isn't used anymore free it. Resources are freed.
	 */
	public void destroy() {
		glDeleteTextures(index);
	}

	public int getTextureIndex() {
		return index;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "FullTexture [height=" + height + ", index=" + index
				+ ", width=" + width + "]";
	}
}