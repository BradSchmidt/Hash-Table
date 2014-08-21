
public class Table <K, E>{
	private int manyItems;
	private Object[] keys;
	private Object[] data;
	private boolean[] hasBeenUsed; //returns true if data[i] and keys[i] have been used
	
	int count = 0;
	
	public Table(int capacity)
	{
		if (capacity <= 0)
			throw new IllegalArgumentException("Capacity is negative.");
		keys = new Object[capacity];
		data = new Object[capacity];
		hasBeenUsed = new boolean[capacity];
	}
	
	//keys are objects so hash the keys by first computing their hash code and the convert the hash code to valid array index
	private int hash(Object key) //DONE!!!
	{
		return Math.abs(key.hashCode()) % data.length;
	}
	
	//search for an open address
	private int nextIndex(int i)
	{
		if (count + 1 == data.length)	
			return 0;
		else
			return i + count;
	}
	
	//find the array index of an object with a particular key
	private int findIndex(K key)
	{
		int count = 0;
		int i = hash(key);  //hash key value to an array index and assign to i
		
		while ((count < data.length) && (hasBeenUsed[i]))
		{
			if (key.equals(keys[i]))
				return i;  //returns the index of the key in the keys array
			count++;
			i = nextIndex(i);
		}
		return -1;  //key is not in the keys array
	}
	
	public E put(K key, E element)  //puts element in table with specified key
	{
		int index = findIndex(key);
		E answer;
		
		if (index != -1) 
		{ 
			answer = (E)data[index];
			data[index] = element;
			System.out.println("The key for " + key + " with element " + answer + " was replaced with element " + element + " at position " + index);
			return answer;	//returns reference to old element which has been replaced
		}
		else if (manyItems < data.length)
		{//key is not yet in table
			index = hash(key);
			if (keys[index] != null)  
				System.out.println("COLLISION witk key " + key + " at index" + "(" + keys[index] + ")" + index + " element " + data[index]);
			while (keys[index] != null)  //while the spot is full, move to the next.
			{
				index = (index + count) % data.length;
				count++;	
			}
			
			if (count > 0)
				System.out.println("Key " + key + "  moved " + count + " spaces to find an open spot");
			
			System.out.println("Element " + element + " with key " + key + " was placed at index " + index);
			keys[index] = key;
			data[index] = element;   
			hasBeenUsed[index] = true;
			manyItems++;
			return null;	
		}
		else
		{//table is full
			throw new IllegalStateException("table is full");	
		}
	}
	
	public E remove(K key)
	{
		int index = findIndex(key);
		E answer = null;
		
		if (index != -1)
		{
			answer = (E)data[index];
			System.out.println("The element " + answer + " was removed with key " + key + " at position " + index);
			keys[index] = null;
			data[index] = null;
			manyItems--;
		}
		return answer;
	}
	
	public E get(K key)
	{
		int index = findIndex(key);
		if (index == -1)
			return null;
		else
		{
			return (E)data[index];
		}
	}
	
	public boolean containskey(K key)
	{
		return (findIndex(key) != -1);
	}
	
	public void size()
	{
		System.out.println("There are " + manyItems + " items and the capacity is " + data.length);
	}
	
}
