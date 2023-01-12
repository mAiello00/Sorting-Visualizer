import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//Inherits everything from JPanel
//JPanel is everything inside the frame
public class SortingPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Random rand = new Random();
	private int[] numberArray = new int[100];
	
	JButton bubbleSort = new JButton("Bubble Sort");
	JButton mergeSort = new JButton("Merge Sort");
	JButton insertionSort = new JButton("Insertion Sort");
	JButton quickSort = new JButton("Quick Sort");
	JButton heapSort = new JButton("Heap Sort");
	JButton reset = new JButton("Reset");
		
	public SortingPanel()
	{
		this.setArray();
		
		//Set up how the 'start' button looks
		bubbleSort.setBackground(Color.WHITE);
		bubbleSort.setFocusPainted(false);
		bubbleSort.setBorderPainted(false);
		//Defines what the 'start' button does when clicked
		bubbleSort.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("This is the array before sorting");
				printArray();
				
				bubbleSort();
				
				System.out.println("This is the array after sorting");
				printArray();
			}
		});
		
		mergeSort.setBackground(Color.WHITE);
		mergeSort.setFocusPainted(false);
		mergeSort.setBorderPainted(false);
		//Defines what the 'mergeSort' button does when clicked
		mergeSort.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("This is the array before sorting");
				printArray();
				
				mergeSort(numberArray, 0, numberArray.length - 1);
				
				System.out.println("This is the array after sorting");
				printArray();
			}
		});
		
		insertionSort.setBackground(Color.WHITE);
		insertionSort.setFocusPainted(false);
		insertionSort.setBorderPainted(false);
		//Defines what the 'insertionSort' button does when clicked
		insertionSort.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				insertionSort();
			}
		});
		
		quickSort.setBackground(Color.WHITE);
		quickSort.setFocusPainted(false);
		quickSort.setBorderPainted(false);
		//Defines what the 'quickSort' button does when clicked
		quickSort.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("This is the array before sorting");
				printArray();
				
				// 0 is first index of array
				// high is last index of the array
				int high = numberArray.length - 1;
				quickSort(numberArray, 0, high);
				
				System.out.println("This is the array after sorting");
				printArray();
			}
		});
		
		heapSort.setBackground(Color.WHITE);
		heapSort.setFocusPainted(false);
		heapSort.setBorderPainted(false);
		//Defines what the 'heapSort' button does when clicked
		heapSort.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("This is the array before sorting");
				printArray();
				
				heapSort(numberArray);
				
				System.out.println("This is the array after sorting");
				printArray();
			}
		});
		
		//Set up how the r'reset' button looks
		reset.setBackground(Color.WHITE);
		reset.setFocusPainted(false);
		reset.setBorderPainted(false);
		
		//Defines what the 'reset' button does when clicked
		reset.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setArray(); // Generates new array values
				repaint();
				
			}
		});
		
		//Adds all the buttons to the panel
		this.add(bubbleSort);
		this.add(mergeSort);
		this.add(insertionSort);
		this.add(quickSort);
		this.add(heapSort);
		this.add(reset);
	}
	
	
	public void bubbleSort()
	{
		for(int i = 0; i < numberArray.length; i++)
		{
			for(int j = 0; j < numberArray.length - i - 1; j++)
			{
				if(numberArray[j] > numberArray[j + 1])
				{
					int temp = numberArray[j];
					numberArray[j] = numberArray[j + 1];
					numberArray[j + 1] = temp;
					repaint();
				}
			}	
		}
	}
	
	/*
	 * 
	 * */
	public void mergeSort(int array[], int leftIndex, int rightIndex)
	{
		// If the left index is smaller than the right index, then we calculate the midpoint (if the array is not of size 1)
		if(leftIndex < rightIndex)
		{
			int midpoint = leftIndex+(rightIndex-leftIndex)/2;
			
			// Sort the two halves
			mergeSort(array, leftIndex, midpoint);
			mergeSort(array, midpoint + 1, rightIndex);
			
			// Merge the two halves
			merge(array, leftIndex, midpoint, rightIndex);
		}
	}
	
	//First sub-array is arr[leftIndex ... midpoint]
	//Second sub-array is arr[midpoint+1 ... rightIndex]
	private void merge(int array[], int leftIndex, int midpoint,  int rightIndex)
	{
		//Find the size of the two sub-arrays to be merged
		int sizeOne = midpoint - leftIndex + 1;
		int sizeTwo = rightIndex - midpoint;
		
		//Create temporary arrays that will contain the numbers for the two sub arrays
		int leftArray[] = new int[sizeOne];
		int rightArray[] = new int[sizeTwo];
		
		//Copy the data into the arrays
		for(int i = 0; i < sizeOne; i++)
			leftArray[i] = array[leftIndex + i];
		for(int j = 0; j < sizeTwo; j++)
			rightArray[j] = array[midpoint+1+j];
		
		// Merge the temporary arrays
		
		//initialize the index of the first and second arrays
		int firstSubarrayIndex = 0, secondSubarrayIndex = 0;
		
		//Initialize index of merged sub-array
		int mergedSubarrayIndex = leftIndex;
		
		//Start from the beginning of the sub-arrays and move along until you reach the end of them
		while((firstSubarrayIndex < sizeOne) && (secondSubarrayIndex < sizeTwo))
		{
			// If the current element of the left array is less than or equal to the current element of the right 
			// sub-array, then we insert the value of the left sub-array into the merged-array and index the left sub-array by 1
			if(leftArray[firstSubarrayIndex] <= rightArray[secondSubarrayIndex])
			{
				array[mergedSubarrayIndex] = leftArray[firstSubarrayIndex];
				firstSubarrayIndex++;
			}
			// Otherwise we insert the current value from the right sub-array and increment it's position
			else
			{
				array[mergedSubarrayIndex] = rightArray[secondSubarrayIndex];
				secondSubarrayIndex++;
			}
			
			//Then increment the index of the merged sub array
			mergedSubarrayIndex++;
			repaint();
		}
		
		//Copy the remainder of either the left or right array into the merged array if there is anything left
		while(firstSubarrayIndex < sizeOne)
		{
			array[mergedSubarrayIndex] = leftArray[firstSubarrayIndex];
			firstSubarrayIndex++;
			mergedSubarrayIndex++;
			repaint();
		}
		
		while(secondSubarrayIndex < sizeTwo)
		{
			array[mergedSubarrayIndex] = rightArray[secondSubarrayIndex];
			secondSubarrayIndex++;
			mergedSubarrayIndex++;
			repaint();
		}
	}
	
	/*
	 * 1. Start at a given index
	 * 2. Compare it to the item on its left
	 * 3. If the number to the left is greater than it, swap the two values
	 * 4. Continue moving left until we reach the first element that is smaller than it, or it becomes the first element of the array
	 * 5. Move on to the next unsorted value and repeat the algorithm
	 * 
	 * */
	public void insertionSort()
	{
		System.out.println("This is the array before sorting");
		printArray();
		
		for(int i = 1; i < numberArray.length; i++)
		{
			//Get the current value we want to sort
			int currentNumber = numberArray[i];
			
			//Get the index before the value we are sorting
			int comparisonIndex = i - 1;
			while(comparisonIndex >= 0 && numberArray[comparisonIndex] > currentNumber)
			{
				//Swap the two elements
				numberArray[comparisonIndex + 1] = numberArray[comparisonIndex];
				comparisonIndex = comparisonIndex - 1;
				repaint();
			}
			numberArray[comparisonIndex + 1] = currentNumber;
		}
		
		System.out.println("This is the array after sorting");
		printArray();
	}
	
	/*
	 * 1. Items to the left of the pivot are smaller and items to the right are larger
	 * */
	public void quickSort(int[] numberArray, int low, int high)
	{
		if(low < high)
		{
			int partitionIndex = partition(numberArray, low, high);
			quickSort(numberArray, low, partitionIndex - 1);
			quickSort(numberArray, partitionIndex + 1, high);
		}
	}
	private int partition(int array[], int low, int high)
	{
		int pivot = array[high];
		
		//Smaller element index
		int i = low - 1;
		
		for(int j = low; j <= high - 1; j++)
		{
			//If current element is less than the pivot
			if(array[j] <= pivot)
			{
				//Increment index of smaller element 
				i++;
				swap(array, i, j);
			}
			repaint();
		}
		swap(array, i + 1, high);
		repaint();
		return(i + 1);
	}
	
	private void swap(int array[], int first, int second)
	{
		int temp = array[first];
		array[first] = array[second];
		array[second] = temp;
	}
	
	
	/*
	 * 1. Continuously create max heaps to find the largest item
	 * 2. Then remove that item
	 * 3. Then place it in a sorted partition
	 * */
	public void heapSort(int array[])
	{
		int size = array.length;
		
		// Build the heap
		for(int i = size/2 - 1; i >= 0; i--)
			heapify(array, size, i);
		
		// Extract element from the heap
		for(int i = size - 1; i > 0; i--)
		{
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			
			heapify(array, i, 0);
		}
	}
	
	
	private void heapify(int array[], int size, int root)
	{
		int largest = root; // Largest element initialized as the root
		int leftChild = 2 * root + 1; // Right child index
		int rightChild = 2 * root + 2; // Left child index
		
		// If the left child is larger than the root
		if((leftChild < size) && (array[leftChild] > array[largest]))
			largest = leftChild;
		
		// If right child is larger than the root
		if((rightChild < size) && (array[rightChild] > array[largest]))
			largest = rightChild;
		
		// If largest is not the root
		if(largest != root)
		{
			int temp = array[root];
			array[root] = array[largest];
			array[largest] = temp;
			repaint();
			
			// Heapify the subtree recursively
			heapify(array, size, largest);
		}
		
	}
	

	
	void setArray()
	{
		for(int i = 0; i < numberArray.length; i++)
		{
			numberArray[i] = rand.nextInt(510) + 40;
		}
	}
	
	private void printArray()
	{
		for(int i = 0; i < numberArray.length; i++)
		{
			System.out.print(numberArray[i] + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// Go through every element of the array and set the height of it on the graph
		for(int i = 0; i < numberArray.length; i++)
		{
			int height = numberArray[i]; // Set the height as the value in the array
			g.setColor(Color.white);
			
			g.fillRect(i*15, 600 - numberArray[i], 14, height);
			g.setColor(Color.black);
			g.drawRect(i*15, 600 - numberArray[i], 14, height);
		}
	}
	
	public boolean isSorted()
	{
		for(int i = 0; i < numberArray.length; i++)
		{
			if(i == numberArray.length - 1)
				return true;
			
			if(numberArray[i] > numberArray[i + 1])
				return false;
		}
		return true;
	}
	
	public void bubbleSortAnimate() throws Exception
	{
		Timer timer = new Timer(1, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				
				repaint();
			}

		});
		timer.start();
	}
}
