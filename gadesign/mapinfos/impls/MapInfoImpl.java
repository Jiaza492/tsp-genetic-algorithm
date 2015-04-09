package gadesign.mapinfos.impls;

import gadesign.entitys.utils.ODdata;
import gadesign.entitys.utils.Place;
import gadesign.mapinfos.MapInfo;
import gadesign.mapinfos.OrigDestString;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class MapInfoImpl implements MapInfo {

	OrigDestString ods;
	
	public MapInfoImpl(String key){
		this.ods = new OrigDestString(key);
	}
	
	//利用关键字
	public ODdata mapInfoRequest(String origin, String destination) {

		ODdata od = null;

		try {
			od = new ODdata();
			URL url = new URL(ods.requestURL(origin, destination));// 初始化一个URL对象

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();// 得到一个Dom工厂实例
			DocumentBuilder db = dbf.newDocumentBuilder();// 从工厂获得Dom解析器

			InputStream is = url.openStream();// 打开URL对象获得输入流

			Document doc = db.parse(is);// 将输入流导向解析器获得一个Dom树

			// 对Dom树进行操作，获得Map数据，以两点之间时间为例
			NodeList durlist = doc.getElementsByTagName("duration");// 获取duration节点列表
			Element durelement = (Element) durlist.item(0);// 获取列表中的一个节点，向下转型
			String duration = durelement.getFirstChild().getNodeValue();// 读取Dur节点的第一个子节点的值
			od.setTime(Double.parseDouble(duration));
			//System.out.println(duration);

			// 以两点之间距离为例
			NodeList distlist = doc.getElementsByTagName("distance");
			Element distelement = (Element) distlist.item(0);
			String distance = distelement.getFirstChild().getNodeValue();
			od.setDistance(Double.parseDouble(distance));
			//System.out.println(distance);

			// 以两点之间路径坐标为例
//			List<String[]> path = new ArrayList<String[]>();
//			NodeList pathlist = doc
//					.getElementsByTagName("stepDestinationLocation");
//			for (int i = 0; i <= pathlist.getLength() - 1; i++) {
//				Element pathelement = (Element) pathlist.item(i);
//				String[] location = new String[2];
//				//lng
//				NodeList lngList = pathelement.getElementsByTagName("lng");
//				Element lngelement = (Element)lngList.item(0);
//			    String lng = lngelement.getFirstChild().getNodeValue();
//				location[0] = lng;
//				//System.out.println(lng);
//				//lat
//				NodeList latList = pathelement.getElementsByTagName("lat");
//				Element latelement = (Element)latList.item(0);
//			    String lat = latelement.getFirstChild().getNodeValue();
//			    location[1] = lat;
//				//System.out.println(lat);
//				//String[]
//				path.add(location);
//				}
//				// path.add(position);
//				//System.out.println(name);
//			
//			od.setPath(path);
//			
			is.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("读取Xml出错！");;
		}

		return od;

	}
	
	//利用经纬度
	public ODdata mapInfoRequest(Place origin, Place destination) {

		ODdata od = null;

		try {
			od = new ODdata();
			URL url = new URL(ods.requestURL(origin, destination));// 初始化一个URL对象

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();// 得到一个Dom工厂实例
			DocumentBuilder db = dbf.newDocumentBuilder();// 从工厂获得Dom解析器

			InputStream is = url.openStream();// 打开URL对象获得输入流

			Document doc = db.parse(is);// 将输入流导向解析器获得一个Dom树

			// 对Dom树进行操作，获得Map数据，以两点之间时间为例
			NodeList durlist = doc.getElementsByTagName("duration");// 获取duration节点列表
			Element durelement = (Element) durlist.item(0);// 获取列表中的一个节点，向下转型
			String duration = durelement.getFirstChild().getNodeValue();// 读取Dur节点的第一个子节点的值
			od.setTime(Double.parseDouble(duration));
			//System.out.println(duration);

			// 以两点之间距离为例
			NodeList distlist = doc.getElementsByTagName("distance");
			Element distelement = (Element) distlist.item(0);
			String distance = distelement.getFirstChild().getNodeValue();
			od.setDistance(Double.parseDouble(distance));
			//System.out.println(distance);

			// 以两点之间路径坐标为例
//			List<String[]> path = new ArrayList<String[]>();
//			NodeList pathlist = doc
//					.getElementsByTagName("stepDestinationLocation");
//			for (int i = 0; i <= pathlist.getLength() - 1; i++) {
//				Element pathelement = (Element) pathlist.item(i);
//				String[] location = new String[2];
//				//lng
//				NodeList lngList = pathelement.getElementsByTagName("lng");
//				Element lngelement = (Element)lngList.item(0);
//			    String lng = lngelement.getFirstChild().getNodeValue();
//				location[0] = lng;
//				//System.out.println(lng);
//				//lat
//				NodeList latList = pathelement.getElementsByTagName("lat");
//				Element latelement = (Element)latList.item(0);
//			    String lat = latelement.getFirstChild().getNodeValue();
//			    location[1] = lat;
//				//System.out.println(lat);
//				//String[]
//				path.add(location);
//				}
//				// path.add(position);
//				//System.out.println(name);
//			
//			od.setPath(path);
//			
			is.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("读取Xml出错！");;
		}

		return od;

	}
}

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */
