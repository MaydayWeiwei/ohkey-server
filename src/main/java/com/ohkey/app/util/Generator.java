package com.ohkey.app.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.ohkey.app.model.KeyInfo;
import com.ohkey.app.repository.KeyInfoRepository;

public class Generator {
	
	@Autowired
	private static KeyInfoRepository keyInfoRepository;

	public static int generateCode () {
		return 1;
	}
	
	public static int generateExternalKey () {
		int maxExternalKey = 0;
		Iterable<KeyInfo> keyInfos = keyInfoRepository.findAll();
		for (KeyInfo k : keyInfos) {
			Integer externalKey = k.getExternalKey();
			if (externalKey != null && externalKey > maxExternalKey)
				maxExternalKey = externalKey;
		}
		return maxExternalKey;
	}

}
