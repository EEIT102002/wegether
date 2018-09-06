package Service.bean;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UniqueToken {
	@Autowired
	@Qualifier("tokenMap")
	private Map<String, Integer> tokenMap;
	
	public String randomToken() {
		String token = null;
			
		do{
			token = UUID.randomUUID().toString();
		}while(tokenMap.get(token) != null);
		tokenMap.put(token, 0);
		return token;
	}
}
