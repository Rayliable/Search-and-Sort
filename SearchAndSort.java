package codes;

import java.util.Scanner;

public class SearchAndSort {

	public static Scanner input = new Scanner (System.in);
	public static int SIZE = 100;
	public static int[] list = new int [SIZE];
	
	
	//Error trap Method for ints
	public static int numInput(int min, int max) {//Allows the input of a number and error traps

		int num1=0;

		boolean success=true;
		do{
			success=true;
			try{
				num1=input.nextInt();
			}
			
			catch(Exception e){
				input.nextLine();
				success=false;
			}
			
			if(num1<min || success==false)
				System.out.println("Invalid Entry.");
			
			if(num1>max)
				System.out.println("Number is over " +max +". Please enter another number.");
			
			
		}while(num1<min || success==false || num1>max);
		
		return num1;

	}
	
	//1
	public static void popSequentially(){ //Populates the array sequentially.
		for(int x = 0; x < SIZE; x++)
			list[x]=x+1;
	}
	
	//2
	public static void popRandomly(){ //Populates the array randomly.
		int max=SIZE,min=1;
		
		for(int x = 0; x < SIZE; x++)
			list[x]=(int)(Math.random()*(max-min+1)+min);
	}
	
	//3
	public static boolean checkIfSorted(){ //Populates the array randomly.
			for(int x = 0; x < SIZE; x++)
				if(x!=0&&list[x]<list[x-1])
					return false;
				/*if(list[x]!=x+1)
					return false;*/
			return true;
		}
	
	//4
	public static void display(){ //Displays the array on the screen.
		 for(int x = 0; x < SIZE; x++)
			 System.out.print(list[x]+"; ");
		 System.out.println();
	 }
	
	//5
	public static void shuffleArray(){ //Shuffles the array.
		int max=SIZE-1;
		int min=0;
		int tempNum=0;
		for(int x = 0; x < SIZE; x++){
			int tempPos=(int)(Math.random()*(max-min+1)+min);
			tempNum=list[tempPos];
			list[tempPos]=list[x];
			list[x]=tempNum;
		}
	}
	
	//6
	public static int linearSearch(int searchNum){ //Runs a linear search.
		for(int x = 0; x < SIZE; x++)
			if(list[x]==searchNum)
				return x;
		return -1;
	}
	
	//7
	public static boolean checkIfUnique(){ //Checks that the list is unique.
		for(int x = 0; x < SIZE; x++)
			for(int y = x+1; y < SIZE; y++)
				if (list[x]==list[y])
						return false;
		return true;
	}
	
	//8
	public static void bubbleSort(){ //Bubble sorts the list.
		
		int tempNum=0;
		for(int x = 0; x < SIZE-1; x++)
			for(int y = x+1; y < SIZE; y++)
				if(list[y]<list[x]){
					tempNum=list[y];
					list[y]=list[x];
					list[x]=tempNum;
				}				
	}
		
	//9
	public static void randomSort(){ //Random sorts the list.
		
		int tempNum=0;
		int max=SIZE-1;
		int min=0;
		for(int x = 0; x < SIZE-1; x++){
			int y=(int)(Math.random()*(max-min+1)+min);
			tempNum=list[y];
			list[y]=list[x];
			list[x]=tempNum;
		}
	}
	
	//10
	public static int binarySearch(int findNum){ //Binary searches the list.
		if(checkIfSorted()==false){
			System.out.println("List is not sorted. Sorting list...");
			popSequentially();
			bubbleSort();
		}
		
		int posNum=0;
		boolean numFound=false;
		int min=0;
		int max=SIZE-1;
			
		do{
			//System.out.println("loop");
			if(min==max||min+1==max){
				//System.out.println("op1");
				posNum=-1;
				numFound=true;
			}
			else if(list[(min+max)/2]==findNum){
				//System.out.println("op2");
				posNum=(min+max)/2;
				numFound=true;
			}
			else if (list[(min+max)/2]>findNum){
				//System.out.println("op3 " +min +" . " +max);
				max=((min+max)/2);
			}
			else if (list[(min+max)/2]<findNum){
				//System.out.println("op4 " +min +" . " +max);
				min=((min+max)/2);
			}
			
		}while(numFound==false);
		return posNum;
	}
	
	//11
	public static void selectionSort(){ //Selection sorts the list.
		
		int lowestNumPos=-1;
		int tempNum=0;
		for(int x = 0; x < SIZE-1; x++){
			lowestNumPos=x;
			for(int y = x+1; y < SIZE; y++)
				if(list[y]<list[lowestNumPos])
					lowestNumPos=y;
			if(lowestNumPos!=x){
				//System.out.println(x+1);
				tempNum=list[lowestNumPos];
				list[lowestNumPos]=list[x];
				list[x]=tempNum;
			}
		}
	}
	
	//12
	public static int digitAt(int num, int digitPos){ //Finds the digit of a number in a specific position.
		if(digitPos<0)
			digitPos=digitPos*-1;
		
		
		for(int x=0; x < (digitPos-1); x++)
			num = num/10;
		num=num%10;
		
		/*
		if(num<0)
			num=num*-1;
		*/
		
		return num;
	}
	
	//13
	public static void radixSort(){ //Radix sorts the list.
		
		//int list2[] = list;
		
		//System.out.println((int)Math.log10(SIZE)+2);
		
		for(int digitNum=0; digitNum < (findHighestPower10(list))+1; digitNum++){
			int[] temp = new int[SIZE];
			int counter = 0;
			for(int y=0; y < 10; y++){
				for(int x=0; x < SIZE; x++){
					if (digitAt(list[x],digitNum)==y){
						temp[counter] = list[x];
						counter++;
					}
				}
			}
			list = temp;
		}
		
		/*
		//Check
		for(int x = 0; x < SIZE; x++)
			 System.out.print(list[x]+"; ");
		 System.out.println();
		 */
		
	}
	
	
	public static int findHighestPower10(int[]array){ //Find the power of the highest number in a list.
		
		int max=array[0];
		for (int x=0;x<array.length;x++)
			if(array[x]>max)
				max=array[x];
		
		int power=0;
		
		while(Math.pow(10, power)<=max)
			power++;
		
		return power;
	}
	
	//14
	public static void quickSort(int beginning, int end){ //Quick sorts the list.
		//long startNum=System.currentTimeMillis();
		//System.out.println("nMethod");
		//1
		int pivot = beginning;
		int rightSlider=SIZE-1;
		int leftSlider=beginning;
		
		//2 
		while (rightSlider!=leftSlider){
			//System.out.println("Pivot" +pivot +" LS" +leftSlider +" RS" +rightSlider);
			//3
			while(leftSlider<pivot&&list[leftSlider]<=list[pivot])
				leftSlider++;
			//4
			while(rightSlider>pivot&&list[rightSlider]>=list[pivot])
				rightSlider--;
			//5
			if(leftSlider!=rightSlider){ //swap em
				int temp = list[leftSlider];
				list[leftSlider]=list[rightSlider];
				list[rightSlider]=temp;
			
				//6
				if(leftSlider==pivot)
					pivot = rightSlider;
				//7
				else if(rightSlider==pivot)
					pivot = leftSlider;
			}
			
		}
		
		//8
		if(pivot-beginning>1){
			//System.out.println("beg-loop");
			quickSort(beginning,pivot-1);
		}
		//9
		if(end-pivot>1){
			//System.out.println("end-loop");
			quickSort(pivot+1,end);
		}
		
		//System.out.println("Time taken: " +(System.currentTimeMillis()-startNum) +" milliseconds.");
	}
	
	//Main
	public static void main(String[] args) {
		popSequentially();
		int menuChoice=1;
		while(menuChoice!=0){
			
			System.out.println("0. Quit the program.");
			System.out.println("1. Populates the array sequentially.");
			System.out.println("2. Populates the array randomly.");
			System.out.println("3. Checks to see if the array is sorted.");
			System.out.println("4. Displays the array on the screen.");
			System.out.println("5. Shuffles the array.");
			System.out.println("6. Runs a linear search.");
			System.out.println("7. Checks that the list is unique.");
			System.out.println("8. Bubble sorts the list.");
			System.out.println("9. Random sorts the list.");
			System.out.println("10. Binary searches the list.");
			System.out.println("11. Selection sorts the list.");
			System.out.println("12. Gives the value of a number's specific digit.");
			System.out.println("13. Radix sorts the list.");
			System.out.println("14. Quick sorts the list.");
			
			
			menuChoice=numInput(0, 14);
			
			if(menuChoice==1)
				popSequentially();
			
			if(menuChoice==2)
				popRandomly();
			
			if(menuChoice==3)
				System.out.println(checkIfSorted());
			
			if(menuChoice==4)
				display();
			
			if(menuChoice==5)
				shuffleArray();
			
			if(menuChoice==6){
				System.out.println("What number would you like to search for?");
				int searchNum = numInput(0, SIZE);
				if(linearSearch(searchNum)>0)
					System.out.println("The number is in the position number " +(linearSearch(searchNum)+1) +". ");
				else
					System.out.println("The number is not present in the list.");
			}
			
			if(menuChoice==7)
				System.out.println(checkIfUnique());
			
			if(menuChoice==8){
				long startNum=System.currentTimeMillis();
				bubbleSort();
				System.out.println("Time taken: " +(System.currentTimeMillis()-startNum) +" milliseconds.");
			}
			
			if(menuChoice==9){
				long startNum=System.currentTimeMillis();
				randomSort();
				System.out.println("Time taken: " +(System.currentTimeMillis()-startNum) +" milliseconds.");
			}
			
			if(menuChoice==10){
				System.out.println("What number would you like to search for?");
				int searchNum = numInput(0, SIZE);
				if(binarySearch(searchNum)!=-1)
					System.out.println("The number is in the position number " +(binarySearch((searchNum)+1)) +". ");
				else
					System.out.println("The number is not present in the list.");
				System.out.println();
			}
			
			if(menuChoice==11){
				long startNum=System.currentTimeMillis();
				selectionSort();
				System.out.println("Time taken: " +(System.currentTimeMillis()-startNum) +" milliseconds.");
			}
			
			if(menuChoice==12){
				System.out.println("Please enter the number, then the digit.");
				System.out.println("The value of this digit is " +digitAt(numInput(0, 1000000),numInput(0, SIZE)) +". ");
			}
			
			if(menuChoice==13){
				long startNum=System.currentTimeMillis();
				radixSort();
				System.out.println("Time taken: " +(System.currentTimeMillis()-startNum) +" milliseconds.");
			}
			
			if(menuChoice==14){
				long startNum=System.currentTimeMillis();
				quickSort(0,SIZE-1);
				System.out.println("Time taken: " +(System.currentTimeMillis()-startNum) +" milliseconds.");
			}
			
			System.out.println();
		}
		
	}
	
}
