package Service.bean;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class UniqueToken {
	private Map<String, Integer> tokenMap;
	
	public Map<String, Integer> getTokenMap() {
		return tokenMap;
	}

	public void setTokenMap(Map<String, Integer> tokenMap) {
		this.tokenMap = tokenMap;
	}

	public String randomToken(Integer id) {
		String token = null;
			
		do{
			token = UUID.randomUUID().toString();
		}while(tokenMap.get(token) != null);
		tokenMap.put(token, id);
		return token;
	}
}
