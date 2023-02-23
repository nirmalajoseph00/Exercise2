package com.zohocorp.zohoex2project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


class Browser {
	private ArrayList<String> url = new ArrayList<>();
	
	Browser(){
		//empty constructor
	}
	
	Browser(String urlHistory[])	
	{
		int l=urlHistory.length;
		for(int i=0;i<l;i++)
			addURL(urlHistory[i]);
	}

	public void addURL(String s)
	{
		url.add(s); 
	}
	
	
	public void display()  
	{  
		System.out.println("Browser History \n");
		for(int i=0;i<url.size();i++)
			System.out.println(url.get(i)+"\n");
	}
	
	public void whoAmI() {
		System.out.println("I am a browser");
	}
}

class GoogleChrome extends Browser{
	private boolean isLocationAccessible=false,isCameraAccessible=false, isMicrophoneAccessible=false;
	final String versionNumber="1.0";
	static int countGoogleTabs=0;
	
	GoogleChrome(){
		countGoogleTabs++; //To count the number of google tabs opened
	}

	GoogleChrome(String urlHistory[]){
		super(urlHistory);
	}
	
	
	public void setAccessibility() {
		
			this.isLocationAccessible=false;
		
			this.isCameraAccessible=false;
	
			this.isMicrophoneAccessible=false;
		permissionDisplay();
	}
	
	public void setAccessibility(boolean permissionArray[]) {
		this.isLocationAccessible=permissionArray[0];
		this.isCameraAccessible=permissionArray[1];
		this.isMicrophoneAccessible=permissionArray[2];
		permissionDisplay();
	}
	
	public void permissionDisplay() {
		System.out.println("Location Permission:" + this.isLocationAccessible);
		System.out.println("Camera Permission:" + this.isCameraAccessible);
		System.out.println("Microphone Permission:" + this.isMicrophoneAccessible);
	}
	
	@Override
	public void whoAmI() {
		System.out.println("I am Google Chrome");
	}

}

interface MultipleAccountContainers {
	void addContainer(String newContainer);
	void removeContainer(String deleteContainer);
}

class Firefox extends Browser implements MultipleAccountContainers {
	
	private static ArrayList<String> container = new ArrayList<>();
	
	Firefox(){
		//empty constructor
	}
	
	Firefox(String urlHistory[]){
		super(urlHistory);
	}
	@Override
	public void whoAmI() {
		System.out.println("I am Firefox");
	}

	@Override
	public void addContainer(String newContainer) {
		if (container.contains(newContainer)) {
			System.out.println("Container already present");
		}
		else
			container.add(newContainer);
		System.out.println("Container List:"+ container);
	}

	@Override
	public void removeContainer(String deleteContainer) {
		if (container.size()==0)
		{
			System.out.println("Container List is empty");
		}
		else {
			if (container.contains(deleteContainer)==false) {
				System.out.println("Container not present");
			}
			else {	
				container.removeIf(n -> (n.equals(deleteContainer)));
				System.out.println("Container List:"+container);
			}
		}
	}
}

public class BrowserOops{
	
	public static Browser[] growArraySize(Browser b[], int indexAllBrowsers) {
		if(b.length==indexAllBrowsers)
		{
			Browser newB[]=new Browser[indexAllBrowsers+1];
			for(int i=0;i<indexAllBrowsers;i++)
				newB[i]=b[i];
			
			b=newB;
		}  
		return b;
	}
	
	
	public static Scanner in = new Scanner(System.in); //globally declared scanner is static since non-static variable i/p cannot be referenced from static content
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		char c='Y' ;
		int choice;
		int indexAllBrowsers=5;
		
		
		Browser newTab=new Browser();
		Browser newGoogleTab=new GoogleChrome();
		Browser newFirefoxTab=new Firefox();
		
		Browser tabOne=new GoogleChrome();
		Browser tabTwo=new Firefox();
		Browser tabThree=new Firefox();
		Browser tabFour=new GoogleChrome();
		Browser tabFive=new GoogleChrome();
		Browser[] allBrowsers=new Browser[5];
		
		allBrowsers[0]=tabOne;
		allBrowsers[1]=tabTwo;
		allBrowsers[2]=tabThree;
		allBrowsers[3]=tabFour;
		allBrowsers[4]=tabFive;
		
		do
		{
			System.out.println("\n Main Menu \n 1.Add URLs \n 2.Find my Browser name \n 3.Set Permissions"
					+ "\n 4.Number of Tabs \n 5.Container \n 6.Quit");
			System.out.println("Enter your choice: ");
			choice = in.nextInt();
			
			switch(choice)
			{
			case 1:
				String browserName;
				System.out.println("Enter the browser whose history you want to enter: ");
				browserName = reader.readLine();
				if(browserName.equalsIgnoreCase("Google Chrome"))
					newTab=addHistory(newGoogleTab);
				if(browserName.equalsIgnoreCase("Firefox"))
					newTab=addHistory(newFirefoxTab);
				allBrowsers=growArraySize(allBrowsers, indexAllBrowsers);
				allBrowsers[indexAllBrowsers++]=newTab;
				break;
			case 2:
				System.out.println("The open browser tabs are :");
				for(int j=0;j<allBrowsers.length;j++) {
					allBrowsers[j].whoAmI();
				}
				break;
				
			case 3:
				setPermissions(tabOne);
				break;
			case 4:
				numberOfTabs(allBrowsers);
				break;
			case 5:
				editContainer(tabTwo);
				break;
			case 6:
				System.out.println("Do you want to continue(Y/N): ");
				c=in.next().charAt(0);
				break;
			default:
				System.out.println("Wrong choice");
			}
			
		}while(c=='Y');
		System.out.println("Goodbye");
		in.close();

	}
	
	
	private static Browser addHistory(Browser newTab) { //add urls to browser 
		
		int urlNumber;
		System.out.println("Enter the number of urls you want to add: ");
		urlNumber = in.nextInt();
		String[] urlName= new String[urlNumber];
		System.out.println("Enter the URLS you want to add: ");
		for(int i=0;i<urlNumber;i++)
		{
			urlName[i] = in.next();
			newTab.addURL(urlName[i]);
		}
		newTab.display();
		return (newTab);
	}
	
	private static void setPermissions(Browser tabOne){
		int permissionChoice;
		System.out.println("\n Set Permissions \n 1.ALL \n 2.RESET PERMISSIONS");
		System.out.println("Enter your choice:");
		permissionChoice = in.nextInt(); 
		
		switch(permissionChoice)
		{
		case 1:
			boolean permissionOptionArray[]=new boolean[3];
			System.out.println("Enter permission for location, camera and microphone: ");
			for(int j=0;j<3;j++) {
				permissionOptionArray[j]=in.nextBoolean();
			}
			((GoogleChrome) tabOne).setAccessibility(permissionOptionArray);
			break;
		case 2:
			((GoogleChrome) tabOne).setAccessibility();
			break;
		default:
			System.out.println("Wrong choice");
		}
	}
	
	public static void numberOfTabs(Browser b[]){
		int countGoogleChrome=0;
		int countFirefox=0;
		for(int j=0;j<b.length;j++) {
			if(b[j] instanceof GoogleChrome) {
				countGoogleChrome++;
			}
			else if(b[j] instanceof Firefox) {
				countFirefox++;
			}
		}
		System.out.println("Number of GoogleChrome tabs: "+ countGoogleChrome);
		System.out.println("Number of Firefox tabs: "+ countFirefox);
	}
	
	public static void editContainer(Browser tabTwo){
	
			String containerName;
			int containerChoice;
			System.out.println("\n Container \n 1.Add Container \n 2.Remove Container");
			System.out.println("Enter your choice:");
			containerChoice = in.nextInt(); 
			switch(containerChoice)
			{
			case 1:
				System.out.println("Enter the name of container you want to add: ");
				containerName = in.next();
				((Firefox) tabTwo).addContainer(containerName);
				break;
			case 2:
				System.out.println("Enter the name of container you want to remove: ");
				containerName = in.next();
				((Firefox) tabTwo).removeContainer(containerName);
				break;
			default:
				System.out.println("Wrong choice");
			}
	}

}

