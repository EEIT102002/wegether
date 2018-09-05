package pictureconvert;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class PictureConvert {
	
	
	public static String convertBase64Image(byte[] picture) {
		
		String base64Image = StringUtils
				.newStringUtf8(Base64
						.encodeBase64(picture, false))
									.toString();

		return base64Image;
	}

}
