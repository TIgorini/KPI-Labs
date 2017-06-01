﻿/* РІШЕННЯ ЗАВДАННЯ ВЗАЄМНОГО ВИКЛЮЧЕННЯ ПРИ ДОСТУПІ ДО СПІЛЬНОГО РЕСУРСУ
 * ЗА ДОПОМОГОЮ ДВІЙКОВОГО СЕМАФОРУ
 */

/* Програма до рисунку 8 з конспекту */

//  Copyright (c) 2009 Oleksandr Marchenko. All rights reserved.

/* Підключення необхідних бібліотек */
import java.io.*;
import java.util.concurrent.Semaphore;

 
/* Оголошення класу, що містить спільні 
 * данні, які використовуються у потоках */
class Global
{
	public static int sum = 0;
	public static int n = 0;

	/* Оголошення семафору */
	//Semaphore(int permits, boolean fair) 
        public static Semaphore sem = new Semaphore(1, true);
}

class Thread_1 implements Runnable
{
	/* Оголошення потокового об'єкту */
	Thread t;

	/* Конструктор */
	Thread_1()
	{
		/* створення нового потоку */
		t = new Thread(this, "Thread_1");
		/* Запуск потокової функції */
		t.start();
	}
	
	/* Потокова функція */
	public void run()
	{
		int i = 1;
		while(i <= Global.n)
		{
			/* Закриваємо семафор */
			try
			{
				Global.sem.acquire();
				
			} catch(InterruptedException e)
			  {
				System.out.println("Thread_1 was interrupted\n");
			  }
			/* Обчислюємо значення змінної sum, що спільно використовується двома потоками. */
			Global.sum = Global.sum + i;

			System.out.println("sum = " + Global.sum + ";  i = " + i);

			/* Відкриваємо семафор */
			Global.sem.release();

			i += 2;
		}
	}		
 }

class Thread_2 implements Runnable
{
	/* Оголошення потокового об'єкту */
	Thread t;

	/* Конструктор */
	Thread_2()
	{
		/* створення нового потоку */
		t = new Thread(this, "Thread_1");
		/* Запуск потокової функції */
		t.start();
	}
	
	/* Потокова функція */
	public void run()
	{
		int i = 2;
		while(i <= Global.n)
		{
			/* Закриваємо семафор */
			try
			{
				Global.sem.acquire();

			} catch(InterruptedException e)
			  {
				System.out.println("Thread_2 was interrupted\n");
			  }
			/* Обчислюємо значення змінної sum, що спільно використовується двома потоками. */
			Global.sum = Global.sum + i;

			System.out.println("sum = " + Global.sum + ";  i = " + i);

			/* Відкриваємо семафор */
			Global.sem.release();

			i += 2;
		}
	}	
}

class Main
{
	public static void main(String [] args)
	{
		/* Для реалізації вводу з клавіатури */
		BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter n: ");
		try 
		{
			Global.n = Integer.valueOf(inStream.readLine().trim()).intValue();
 		} catch(IOException e)
		  {
			System.out.println("Wrong Input\n");
		  }
		
		/* Створюються потоки. */
		Thread_1 thread1 = new Thread_1();
		Thread_2 thread2 = new Thread_2();

		/* Очікується завершення потоків. */
		try
		{
			thread1.t.join();
			thread2.t.join();
		} catch(InterruptedException e)
		  {
			System.out.println("Main Interrupted\n");
		  }
		
		 /*Завершення програми. */
		System.out.println("Resulting sum = " + Global.sum);
	}
}
			
	
