package com.zdsoft.finance.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderedProperties {

	private String natureFile = "/conf/select.properties";
	
	private List<String> keys = new ArrayList<String>();

	private Map<String, String> valueMap = new HashMap<String, String>();
	
	

	public String getProperty(String key) {
		return valueMap.get(key);
	}

	public List<String> getKeys(String keyPattern) {
		Pattern pat = Pattern.compile(keyPattern);
		List<String> kl = new ArrayList<String>();
		for (String k : keys) {
			if (pat.matcher(k).matches()) {
				kl.add(k);
			}
		}
		return kl;
	}

	public synchronized void load(InputStream istream) throws IOException {
		BufferedReader bread = new BufferedReader(
				new InputStreamReader(istream));
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bread.readLine()) != null) {
			line = line.trim();
			if (line.equals(""))
				continue;
			if(line.startsWith("#"))
				continue;
			if (line.endsWith("\\"))
				line = line.substring(0, line.length() - 1);
			if (line.indexOf('=') != -1) {
				lines.add(line);
			} else {
				String s = lines.get(lines.size() - 1);
				s += line;
				lines.set(lines.size() - 1, s);
			}
		}
		for (String l : lines) {
			String k = l.substring(0, l.indexOf("=")).trim();
			String v = l.substring(l.indexOf("=") + 1).trim();
			keys.add(Unicode2UTF8(k));
			valueMap.put(Unicode2UTF8(k),Unicode2UTF8(v));

		}

	}

	public List<String> getKeys() {
		return keys;
	}

	@Override
	public String toString() {
		return valueMap.toString();
	}

	public static String Unicode2UTF8(String dataStr) {
		int index = 0;
		StringBuffer buffer = new StringBuffer();
		Matcher m=Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(dataStr);
		if(m.find()){
			while (index < dataStr.length()) {
				if (!"\\u".equals(dataStr.substring(index, index + 2))) {
					buffer.append(dataStr.charAt(index));
					index++;
					continue;
				}
				String charStr = "";
				charStr = dataStr.substring(index + 2, index + 6);
				char letter = (char) Integer.parseInt(charStr, 16);
				try {
					buffer.append(new String((letter + "").getBytes("UTF-8"),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				index += 6;
			}
			return buffer.toString();
		}else{
			return dataStr;
		}
	}

	public List<String[]> getList(String selectType){
		List<String[]> selectList = new ArrayList<String[]>();
		List<String> keys = this.getKeys();
		String[] string;
		for(int i = 0;i<keys.size();i ++){
			String index = keys.get(i);
			String[] key = index.split("\\.");
			String s = key[0];
			String name = key[1];
			if(s.equals(selectType)){
				string = new String[2];
				String value = this.getProperty(index);
				string[0] = name;
				string[1] = value;
				selectList.add(string);
			}
		}
		return selectList;
	}
	
	public OrderedProperties(){
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.natureFile);
		try {
			this.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
