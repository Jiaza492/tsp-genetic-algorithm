package gadesign.mapinfos;

import gadesign.entitys.utils.Place;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class OrigDestString {

	String key;
	
	public OrigDestString(String key){
		this.key = key;
	}
	
	public String requestURL(String origin, String destination){
		String urlpath = "http://api.map.baidu.com/direction/v1?mode=driving";
		urlpath = urlpath+"&origin="+origin+"&destination="+destination;
		urlpath = urlpath+"&origin_region=北京&destination_region=北京&output=xml";
		urlpath = urlpath+"&ak="+this.key;
		//System.out.println(urlpath);
		return urlpath;
	}
	
	public String requestURL(Place origin, Place destination){
		String urlpath = "http://api.map.baidu.com/direction/v1?mode=driving";
		urlpath = urlpath+"&origin="+origin.getLat()+","+origin.getLng();
		urlpath = urlpath+"&destination="+destination.getLat()+","+destination.getLng();
		urlpath = urlpath+"&origin_region=北京&destination_region=北京&output=xml";
		urlpath = urlpath+"&ak="+this.key;
		//System.out.println(urlpath);
		return urlpath;
	}
}
