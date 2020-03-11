package com.kosmo.mintchoco.common;

public class Unicode 
{
	public static String encode(String s)
	{
		StringBuffer uni_s = new StringBuffer();
		String temp_s = null;
		for( int i=0 ; i < s.length() ; i++)
		{
			temp_s = Integer.toHexString( s.charAt(i) );
			uni_s.append( "\\u"+(temp_s.length()==4 ? temp_s : "00" + temp_s ) );
		}
		
		return uni_s.toString();
	}

	public static String decode(String uni)
	{
		StringBuffer str = new StringBuffer();
		for( int i= uni.indexOf("\\u") ; i > -1 ; i = uni.indexOf("\\u") )
		{
			str.append( uni.substring( 0, i ) );
			str.append( String.valueOf( (char)Integer.parseInt( uni.substring( i + 2, i + 6 ) ,16) ) );
			uni = uni.substring( i +6);
		}
		
		str.append( uni );
		return str.toString();
	}
}