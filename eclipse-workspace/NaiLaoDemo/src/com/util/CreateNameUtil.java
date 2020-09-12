package com.util;

import java.util.Random;

public class CreateNameUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String new1 ="迦勒逸乐奶酪换手机啊返回就是卡号发可好会复发的开发建设的看了几分";
		String new2="反对护肤和苏打粉互动时放假就是返回时可减肥加快进度款老骥伏枥手机费反对联赛积分落户大数据峰会上即可";
		System.out.println(new1.length());

		System.out.println(new2.length());
		
	     Random r = new Random();
		 int ran1 = r.nextInt(new1.length()+1);
		 int ran2 = r.nextInt(new2.length()+1);
		 System.out.println(ran2);
		System.out.println(ran1);
		
		String str1 = new1.substring(ran1,ran1+1);
		String str2 = new2.substring(ran2,ran2+1);
		System.out.println(str1+str2);
	}
	
	public static String getName() {
		String new1 ="迦勒逸乐黄金时代风华绝代恢复开放放假的恢复健康护肤决定是否健康你去玩儿吐过但是放不开破口解放后不错不错项目的姐姐家的看那家店即可难道就是你房间看书呢奶酪换手机啊返回就是卡号发可好会复发的开发建设的看了几分";
		String new2="反对护肤和苏打粉互动时放假就是返放假时的集装箱那时不懂哈十八大后无哦哦可不可不看姐姐给胡歌女女孩莫咯六级叫姑姑电话电几哦时湖大吧关于大手笔回时可减肥加快进度款老骥伏枥手机费反对联赛积分落户大数据峰会上即可";
		System.out.println(new1.length());

		System.out.println(new2.length());
		
	     Random r = new Random();
		 int ran1 = r.nextInt(new1.length()+1);
		 int ran2 = r.nextInt(new2.length()+1);

			String str1 = new1.substring(ran1,ran1+1);
			String str2 = new2.substring(ran2,ran2+1);
		return "王"+str1+str2;
	}

}
