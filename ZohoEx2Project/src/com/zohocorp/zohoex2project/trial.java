package com.zohocorp.zohoex2project;

import java.util.Arrays;

public class trial {

	public static void main(String[] args) {
		int indexAllBrowsers=6;
		
		Browser tabOne=new GoogleChrome();
		Browser tabTwo=new Firefox();
		Browser tabThree=new Firefox();
		Browser tabFour=new GoogleChrome();
		Browser tabFive=new GoogleChrome();
		Browser[] allBrowsers=new Browser[7];
		
		allBrowsers[0]=tabOne;
		allBrowsers[1]=tabTwo;
		allBrowsers[2]=tabThree;
		allBrowsers[3]=tabFour;
		allBrowsers[4]=tabFive;
		for(int i=0;i<allBrowsers.length;i++)
			System.out.print(allBrowsers[i]+ "\t");
		System.out.println();
		Browser newTab1=new Browser();
		allBrowsers[5]=newTab1;
		for(int i=0;i<allBrowsers.length;i++)
			System.out.print(allBrowsers[i]+ "\t");
		System.out.println(allBrowsers);
		
		if (Arrays.asList(allBrowsers).contains(newTab1)== false)
		{
			allBrowsers[indexAllBrowsers++]=newTab1;
		}
		for(int i=0;i<allBrowsers.length;i++)
			System.out.println(allBrowsers[i]);
	}

}
