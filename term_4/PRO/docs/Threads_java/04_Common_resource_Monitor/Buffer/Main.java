// РІШЕННЯ ЗАДАЧІ PRODUCER-CONSUMER (СПІЛЬНИЙ РЕСУРС - BEKTOP-СТЕK)
// ЗА ДОПОМОГОЮ МОНІТОРА


//  Copyright (c) 2009 Oleksandr Marchenko. All rights reserved.

class CommonResource
{
	public static final int MaxBufSize = 99;
	public static final int MinBufSize = 0;
	int buf[] = new int[MaxBufSize+1];
	int ind = 0;
	boolean IsEmpty = ind  == MinBufSize;
	boolean IsFull = ind == MaxBufSize;
	
	synchronized void consume()
	{
		while (IsEmpty)
			try
			{
				wait(); 
			}
			catch (InterruptedException e)
			{
				System.out.println("InterruptedException");
			}
			
		System.out.println("--- buf[" + ind + "] = " + buf[ind]);
		buf[ind] = 0;
		System.out.println("--- ind: " + ind-- + " => " + ind);
		System.out.println();
			
		IsEmpty = ind == MinBufSize;
		IsFull = false;
			
		notify();
	}

	
	synchronized void produce ()
	{
		while (IsFull)
		
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				System.out.println("InterruptedException");
			}
			
		System.out.println("+++ ind: " + ind++ +" => " + ind);
		buf[ind] = ind;
		System.out.println("+++ buf[" + ind + "] = " + buf[ind]);
		System.out.println();

		IsFull = ind == MaxBufSize;
		IsEmpty = false;
			
		notify();

		
	}
}

class Producer implements Runnable
{
	CommonResource CR;
		
	Producer (CommonResource CR_arg)
	{
		this.CR = CR_arg;
		new Thread (this, "Producer").start();
	}
	
	public void run()
	{
		while (true) 
		{
			CR.produce();
		}
	}
}
	
class Consumer implements Runnable
{
	CommonResource CR;
		
	Consumer (CommonResource CR_arg)
	{
		this.CR = CR_arg;
		new Thread (this, "Consumer").start();
	}
	
	public void run()
	{
		while (true) 
		{
			CR.consume();
		}
	}
}

class Main
{
	public static void main (String args[])
	{
		System.out.println ("To stop running press Ctrl+C");

		CommonResource MyCR = new CommonResource();
		new Producer ( MyCR );
		new Consumer ( MyCR );
		
	}
}
