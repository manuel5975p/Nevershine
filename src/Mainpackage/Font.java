package Mainpackage;
import static org.lwjgl.opengl.GL11.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

import Mainpackage.*;

/**
 * Font class
 * 
 * @author Faruk Acibal, Richard Dziambor, Christian Holzreuter, Nicolas Winkler
 */
public class Font {
	/**
	 * name of the font (e.g. "Arial")
	 */
	private String name;

	/**
	 * the size of the font bitmaps
	 */
	private short size;

	/**
	 * texture that contains every letter
	 */
	private Texture texture;

	/**
	 * an array that stores for every character its position e.g. {@code
	 * positions((int) 'c')} is the position of the small letter c;
	 */
	private LRect[] positions;

	/**
	 * private constructor
	 */
	private Font() {
		name = "";
	}

	/**
	 * @return the name of this font
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the font size of the font bitmaps. This size can be exceeded, it
	 * just gets blurry
	 * 
	 * @return the font size
	 */
	public short getSize() {
		return size;
	}

	/**
	 * Displays a text using this font
	 * 
	 * @param x
	 *            the x coordinate of the upper-left corner of the text
	 * @param y
	 *            the y coordinate of the upper-left corner of the text
	 * @param text
	 *            the text to display
	 * @param size
	 *            the size of this text
	 */
	public void displayText(float x, float y, String text, float size) {
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, texture.index);

		glBegin(GL_TRIANGLES);

		for (int i = 0; i < text.length(); i++) {
			LRect pos = positions[text.charAt(i)];
			float letterWidth = size * pos.width / pos.height;

			glTexCoord2f(pos.x, 1f - pos.y);
			glVertex2f(x, y);
			glTexCoord2f(pos.right(), 1f - pos.y);
			glVertex2f(x + letterWidth, y);
			glTexCoord2f(pos.x, 1f - pos.bottom());
			glVertex2f(x, y + size);

			glTexCoord2f(pos.right(), 1f - pos.y);
			glVertex2f(x + letterWidth, y);
			glTexCoord2f(pos.x, 1f - pos.bottom());
			glVertex2f(x, y + size);
			glTexCoord2f(pos.right(), 1f - pos.bottom());
			glVertex2f(x + letterWidth, y + size);

			x += letterWidth;
		}

		glEnd();
		glDisable(GL_TEXTURE_2D);
	}

	/**
	 * represents a header of a *.wbf winfor bitmap font file
	 * 
	 * @author Faruk Acibal, Richard Dziambor, Christian Holzreuter, Nicolas
	 *         Winkler
	 * 
	 */
	private static class WFontHeader {
		/*
		 * struct WFontHeader { char WF[2]; char version[2]; int width; int
		 * height;
		 * 
		 * char fontName[32];
		 * 
		 * short fontSize;
		 * 
		 * char uselessData1[2];
		 * 
		 * unsigned int pixelsOffset; unsigned int pixelDataSize; unsigned int
		 * posTableOffset;
		 * 
		 * char uselessData2[10]; };
		 */

		/**
		 * the size of the header in bytes (as it is stored in the file)
		 */
		static int byteSize = 72;

		short wf;
		short version;
		int width;
		int height;
		byte[] fontName = new byte[32];
		short fontSize;

		int pixelsOffset;
		int pixelDataSize;
		int posTableOffset;

		/**
		 * reads this header from a {@link ByteBuffer}
		 * 
		 * @param in
		 *            the {@link ByteBuffer}
		 * @throws IOException
		 *             if the {@link ByteBuffer} doesn't contain anything, or
		 *             something else goes wrong.
		 */
		void readHeader(ByteBuffer in) throws IOException {
			wf = in.getShort();
			version = in.getShort();
			width = swapInt(in.getInt());
			height = swapInt(in.getInt());

			in.get(fontName);
			fontSize = swapShort(in.getShort());

			in.get(new byte[2]); // skip 2 bytes

			pixelsOffset = swapInt(in.getInt());
			pixelDataSize = swapInt(in.getInt());
			posTableOffset = swapInt(in.getInt());

			in.get(new byte[10]); // skip 10 bytes
		}

		/**
		 * writes the header into a {@link ByteBuffer} using the format used in
		 * the file
		 * 
		 * @param out
		 *            the {@link ByteBuffer} to write into
		 * @throws IOException
		 *             if the {@link ByteBuffer} is full or something else goes
		 *             wrong.
		 */
		void writeHeader(ByteBuffer out) throws IOException {
			out.putShort(wf);
			out.putShort(version);
			out.putInt(swapInt(width));
			out.putInt(swapInt(height));

			if (fontName.length != 32) {
				fontName = new byte[32];
			}
			out.put(fontName);
			out.putShort(swapShort(fontSize));
			out.putShort((short) 0); // skip 2 bytes

			out.putInt(swapInt(pixelsOffset));
			out.putInt(swapInt(pixelDataSize));
			out.putInt(swapInt(posTableOffset));

			out.put(new byte[10]); // skip 10 bytes
		}
	}

	private static class PosTableRecord {
		/*
		 * struct PosTableRecord { bool available; char uselessData1[3];
		 * 
		 * float x; float y; float width; float height; };
		 */

		/**
		 * the size of a record in bytes (as it is stored in the file)
		 */
		static int byteSize = 20;

		@SuppressWarnings("unused")
		boolean available;
		float x;
		float y;
		float width;
		float height;

		/**
		 * reads one record from a {@link ByteBuffer}
		 * 
		 * @param in
		 *            the {@link ByteBuffer}
		 * @throws IOException
		 *             if the {@link ByteBuffer} doesn't contain anything, or
		 *             something else goes wrong.
		 */
		void readRecord(ByteBuffer in) throws IOException {
			available = in.get() == 0 ? false : true;
			in.get(new byte[3]);
			x = Float.intBitsToFloat(swapInt(in.getInt()));
			y = Float.intBitsToFloat(swapInt(in.getInt()));
			width = Float.intBitsToFloat(swapInt(in.getInt()));
			height = Float.intBitsToFloat(swapInt(in.getInt()));
		}

		/**
		 * writes the record into a {@link ByteBuffer} using the format used in
		 * the file
		 * 
		 * @param out
		 *            the {@link ByteBuffer} to write into
		 * @throws IOException
		 *             if the {@link ByteBuffer} is full or something else goes
		 *             wrong.
		 */
		void writeRecord(ByteBuffer out) throws IOException {
			byte[] a = new byte[4];
			a[0] = 123;
			a[1] = 45;
			a[2] = 67;
			a[3] = 89;
			out.put(a);
			out.put(new byte[16]);
			/*
			 * out.putInt((Float.floatToIntBits(x)));
			 * out.putInt((Float.floatToIntBits(y)));
			 * out.putInt((Float.floatToIntBits(width)));
			 * out.putInt((Float.floatToIntBits(height)));
			 */
		}
	}

	/**
	 * changes the order of the bytes in an {@code int} from 1 2 3 4 to 7 3 2 1
	 * 
	 * @param a
	 * @return the new {@code int}
	 */
	private static int swapInt(int a) {
		return (((a & 0x000000FF) << 24) | ((a & 0x0000FF00) << 8)
				| ((a & 0x00FF0000) >> 8) | ((a & 0xFF000000) >>> 24));
	}

	/**
	 * changes the order of the bytes in an {@code short} from 1 2 to 2 1
	 * 
	 * @param a
	 * @return the new {@code short}
	 */
	private static short swapShort(short a) {
		return (short) (((a & 0xFF) << 8) | ((a & 0xFF00) >> 8));
	}

	/**
	 * loads a {@link Font} from a file
	 * 
	 * @param path
	 *            the file path
	 * @return the new {@link Font}
	 * 
	 * @throws IOException
	 *             if there is something wrong with the file
	 */
	public static Font loadFont(InputStream input) throws IOException {
		byte[] data = new byte[(int) input.available()];
		input.read(data);
		ByteBuffer in = ByteBuffer.wrap(data);

		Font font = new Font();

		WFontHeader wfh = new WFontHeader();
		wfh.readHeader(in);

		font.size = wfh.fontSize;

		in = ByteBuffer.wrap(data, wfh.pixelsOffset, data.length
				- wfh.pixelsOffset);
		byte[] pixels = new byte[wfh.pixelDataSize];
		in.get(pixels);
		font.texture = Texture.createTexture(wfh.width, wfh.height, pixels,
				GL_ALPHA);
		// font.texture = Texture.createTest();

		in = ByteBuffer.wrap(data, wfh.posTableOffset, data.length
				- wfh.posTableOffset);
		PosTableRecord record = new PosTableRecord();
		font.positions = new LRect[256];
		float width = wfh.width;
		float height = wfh.width;
		for (int i = 0; i < font.positions.length; i++) {
			LRect r = new LRect();
			record.readRecord(in);

			r.x = record.x / width;
			r.y = record.y / height;
			r.width = record.width / width;
			r.height = record.height / height;

			font.positions[i] = r;
		}

		return font;
	}

	/**
	 * saves a font
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void save(String path, byte[] textureData)
			throws FileNotFoundException, IOException {
		WFontHeader wfh = new WFontHeader();
		wfh.fontName = this.name.getBytes();
		wfh.fontSize = this.size;
		wfh.wf = 22342;
		wfh.width = this.texture.width;
		wfh.height = this.texture.height;
		wfh.pixelDataSize = wfh.width * wfh.height;
		wfh.pixelsOffset = WFontHeader.byteSize;
		wfh.posTableOffset = wfh.pixelsOffset + wfh.pixelDataSize - 2;

		int bufferSize = WFontHeader.byteSize + texture.width
				* this.texture.height + 256 * PosTableRecord.byteSize + 2;
		ByteBuffer buffer = ByteBuffer.allocate(bufferSize);

		wfh.writeHeader(buffer);
		buffer.put(textureData, 0, wfh.pixelDataSize);
		PosTableRecord record = new PosTableRecord();
		for (int i = 0; i < 256; i++) {
			LRect rect = this.positions[i];
			record.x = rect.x * texture.width;
			record.y = rect.y * texture.height;
			record.width = rect.width * texture.width;
			record.height = rect.height * texture.height;

			if (rect.width > 0f || rect.height > 0f)
				record.available = true;
			else
				record.available = false;

			record.writeRecord(buffer);
		}

		FileOutputStream fos = new FileOutputStream(path);

		if (buffer.hasArray())
			fos.write(buffer.array());
		else {
			byte[] arr = new byte[1];
			for (int i = 0; i < bufferSize; i++) {
				arr[1] = buffer.get(i);
				fos.write(arr);
			}
		}

		fos.close();
	}

	/**
	 * used to store the position of character on the font texture
	 * 
	 * @author Faruk Acibal, Richard Dziambor, Christian Holzreuter, Nicolas
	 *         Winkler
	 * 
	 */
	private static class LRect {
		public float x;
		public float y;
		public float height;
		public float width;

		public float right() {
			return x + width;
		}

		public float bottom() {
			return y + height;
		}

		@Override
		public String toString() {
			return "LRect [height=" + height + ", width=" + width + ", x=" + x
					+ ", y=" + y + "]";
		}
	}

	public Texture getTexture() {
		return texture;
	}

	@Override
	public String toString() {
		return "Font [name=" + name + ", positions="
				+ Arrays.toString(positions) + ", size=" + size + ", texture="
				+ texture + "]";
	}
}

