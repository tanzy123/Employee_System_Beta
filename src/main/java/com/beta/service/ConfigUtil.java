package com.beta.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.beta.exception.VendorMgmtException;

public class ConfigUtil {
	
	static Properties props = new Properties();
	
	static{
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			props.load(fis );
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new VendorMgmtException("Can not load properties", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new VendorMgmtException("Can not load properties", e);
		}
	}
	
	public static String getKey(String key){
		return props.getProperty(key);
	}

}
