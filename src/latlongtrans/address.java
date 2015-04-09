package latlongtrans;

public class address extends wrin {
	public static String[] tj=new String[999];//¾­¶È
	public static String[] tw=new String[999];//Î³¶È
	public static int tms=tm;

    public static void main(String[] a,String[] b){
    	String[] mm=new String[999];
    	//int[] ag = new int[a.length]; 
    	//int[] bg = new int[b.length]; 
  int i=0;
  
  for (i=0;i<=tm;i++)
  {	//ag[i]=Integer.parseInt(a[i]);
  //bg[i]=Integer.parseInt(b[i]);
	  tj[i]=a[i];	
tw[i]=b[i];
    	System.out.println(i+"--JD---"+tj[i]);
    	System.out.println(i+"--WD---"+tw[i]);
  }
} 
}

