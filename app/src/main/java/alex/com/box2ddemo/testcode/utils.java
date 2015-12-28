package alex.com.box2ddemo.testcode;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import alex.com.box2ddemo.MyApplication;

/**
 * @author AleXQ
 * @Date 15/12/5
 */

public class utils {
	public static String getLineInfo()
	{
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		return ste.getFileName() + ": Line " + ste.getLineNumber();
	}

	public static File copy(String asset, String path) {
		File destinationFile = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = MyApplication.getInstance().getAssets().open(new File(asset).getPath());
			destinationFile = new File(path);
			if (destinationFile.exists()) {
				destinationFile.delete();
			}
			destinationFile.getParentFile().mkdirs();
			out = new FileOutputStream(destinationFile);
			byte[] buffer = new byte[1024];
			int nread = -1;
			while ((nread = in.read(buffer)) != -1) {
				out.write(buffer, 0, nread);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(in);
			closeQuietly(out);
			return destinationFile;
		}
	}

	private static void closeQuietly(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				c = null;
			}
		}
	}
}

