package pictureconvert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class PictureConvert {
	
	
	public static String convertBase64Image(byte[] picture) {
		
			String	 base64Image = StringUtils
						.newStringUtf8(Base64
								.encodeBase64(picture, false))
											.toString();
		
		return base64Image;
	}
	
	public static byte[] converFileToByte(File file) throws IOException {
	  
	    ByteArrayOutputStream ous = null;
	    InputStream ios = null;
	    try {
	        byte[] buffer = new byte[4096];
	        ous = new ByteArrayOutputStream();
	        ios = new FileInputStream(file);
	        int read = 0;
	        while ((read = ios.read(buffer)) != -1) {
	            ous.write(buffer, 0, read);
	        }
	    }finally {
	        try {
	            if (ous != null)
	                ous.close();
	        } catch (IOException e) {
	        }

	        try {
	            if (ios != null)
	                ios.close();
	        } catch (IOException e) {
	        }
	    }
	    return ous.toByteArray();
	}
	
	

}
