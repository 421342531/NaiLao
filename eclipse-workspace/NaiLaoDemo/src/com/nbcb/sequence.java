package com.nbcb;

public class sequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(getSequence("35"));
		
	}
	
	
	public static String getSequence(String str) {
		
		int iii = Integer.valueOf(str);
		
		int out1 =-1;
		if(iii>=34) {
			out1 = iii-34;
		}else {
			
			return "cb:"+(iii+2)+" pb:"+"48";
		}
		//------------------

		int out2 = (iii+2);
		int out3 = 0;
		
		if(iii>=10 &&  iii<=46) {
			if(out1>=out2) {
				out3 = out2;
			}else
			{
				out3 = out1;
			}
			return "cb:"+out3+" pb:"+"48";
		}else {
			
			
			int outyy1=iii+2;
			int outyy2 = 146-iii;
			 int geren =0;
			if(outyy1<=outyy2) {
				geren =outyy1;
			}else {
				geren = outyy2;
			}
			return "cb: 12"+" pb:"+geren;
		}
	}
}
