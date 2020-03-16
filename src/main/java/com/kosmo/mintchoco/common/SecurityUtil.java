package com.kosmo.mintchoco.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SecurityUtil {

	
	public SecurityUtil() {
		super();
		// TODO Auto-generated constructor stub
	}


	public static String encryptSHA256(String plainText) { // throws NoSuchAlgorithmException, UnsupportedEncodingException {		
		String sha256 = "";
		//StringBuffer hexSHA256hash = null;
		
		try {			
			// SHA-256 MessageDigest 객체 생성
	        MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
	        
	        //"king1234" 문자열 바이트로 메시지 다이제스트를 갱신
	        //String 클래스의 getBytes() 메소드는 문자열의 바이트 배열을 돌려준다.
	        //다시 말하면 바이트의 순서를 돌려준다.
	        mdSHA256.update(plainText.getBytes("UTF-8"));
	        
	        // 해시 계산 반환값은 바이트 배열
	        byte[] sha256Hash = mdSHA256.digest();
	        
	        // 바이트배열을 16진수 문자열로 변환하여 표시
	        /*
	         * 그래서 멀티 쓰레드에 상관없는 상황에서 사용할 목적으로 StringBuilder 클래스를 정의하기에 이른다. 
	         * 물론 이 클래스는 멀티 쓰레드에 안전하지 않다. 
	         * 대신에 속도가 빠르다는 장점이 있다. 
	         * 아래 소스를 StringBuffer 클래스로 객체 생성해도 된다.
	         */
	        StringBuffer hexSHA256hash = new StringBuffer();
	        
	        for(byte b : sha256Hash) {
	            String hexString = String.format("%02x", b);
	            hexSHA256hash.append(hexString);
	        }
	        sha256 = hexSHA256hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		return sha256;
	}
//	public String encryptSHA256(String str) {
//		
//		String sha = "";
//		
//		try {
//			
//			MessageDigest sh = MessageDigest.getInstance("SHA-256");
//			sh.update(str.getBytes());
//			byte byteData[] = sh.digest();
//			StringBuffer sb = new StringBuffer();
//			
//			for(int i=0; i< byteData.length; i++) {
//				sb.append(Integer.toString(byteData[i]&0xff + 0x100,16).substring(1));
//			}
//			
//			sha = sb.toString();
//			
//		} catch (NoSuchAlgorithmException e) {
//			
//			System.out.println("Encrypt Error - NoSuchAlgorithmException");
//			sha = null;
//		}
//		
//		
//		return sha;
//	}

}
