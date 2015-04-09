package latlongtrans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LatitudeUtils extends wrin {
	
	public static final String KEY_1 = "7d9fbeb43e975cd1e9477a7e5d5e192a";
	// String key2 = "8ec6cd98bd56554407a207d597c0f3e5";
	// String key3 = "be025dc280e1e3f7ffb95fe42a01fab2";
	// String key4 = "327db7009617d6806b9c38e819ea06ac";
	
	/**
	 * 返回输入地址的经纬度坐标
	 * key lng(经度),lat(纬度)
	 */
	public static Map<String,String> getGeocoderLatitude(String address){
		BufferedReader in = null;
		try {
			//将地址转换成utf-8的16进制
			address = URLEncoder.encode(address, "UTF-8");
//       如果有代理，要设置代理，没代理可注释
//		System.setProperty("http.proxyHost","192.168.1.188");
//		System.setProperty("http.proxyPort","3128");
			URL tirc = new URL("http://api.map.baidu.com/geocoder?address="+ address +"&output=json&key="+ KEY_1);
			//获取地图api
			in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while((res = in.readLine())!=null){
				sb.append(res.trim());//累积字符串，trim去空格
			}
			String str = sb.toString();
			Map<String,String> map = null;
			if(!str.equals("")){
				int lngStart = str.indexOf("lng\":");
				int lngEnd = str.indexOf(",\"lat");
				int latEnd = str.indexOf("},\"precise");
				//------------------
				int cfdStart = str.indexOf("confidence");
				int cfdEnd = str.indexOf("level");
				if(lngStart > 0 && lngEnd > 0 && latEnd > 0){
					String lng = str.substring(lngStart+5, lngEnd);
					String lat = str.substring(lngEnd+7, latEnd);
					String cfd  = str.substring(cfdStart, cfdEnd);
					//--------------
				
					map = new HashMap<String,String>();
					map.put("confidence", cfd);
					map.put("lng", lng);
					map.put("lat", lat);//将返回值赋值到变量
					return map;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void main(String args[]) {
	
		String[] jwdj=new String[999];//经度
		String[] jwdw=new String[999];//纬度
		int i=0;
		for (i=0;i<tm;i++){
		String mm=args[i];//从wrin调取地址数据
		Map<String, String> json = LatitudeUtils.getGeocoderLatitude(mm);//获取地址
		System.out.println("地点 : "+mm);
//if (json.get("lng") != null)
//continue;
		System.out.println("lng : "+json.get("lng"));
		System.out.println("lat : "+json.get("lat"));
	System.out.println("SSSSS: "+json.get("cfd"));
		jwdj[i]=json.get("lng");   //经纬度数组
		jwdw[i]=json.get("lat");
		System.out.println("jwd : "+jwdj[i]+jwdw[i]);
		address getjw=new address();
		getjw.main(jwdj,jwdw);
		//LatitudeUtils getjw=new LatitudeUtils();
		//getjw.jw(jwd);
		//ReadWriteExcelUtil c=new ReadWriteExcelUtil();
	//	ReadWriteExcelUtil.main(jwd);
		}
	
}
	public void jw(String st[]) {   //jw[]取出经纬度
		String[] jw=new String[999];
		int j=0;
		for (j=0;j<tm;j++){
		jw[j]=st[j];
		//System.out.println("jwd : "+j+"==="+jw[j]);
		}
	}
}