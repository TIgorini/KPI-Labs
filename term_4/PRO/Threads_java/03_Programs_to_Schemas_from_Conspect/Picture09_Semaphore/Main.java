/* РІШЕННЯ ЗАВДАННЯ ПРОСТОЇ (ЧАСТКОВОЇ, НЕПОВНОЇ) СИНХРОНІЗАЦІЇ
 * ЗА ДОПОМОГОЮ ДВІЙКОВОГО СЕМАФОРУ
 */

/* Програма до рисунку 9 з конспекту */

//  Copyright (c) 2009 Oleksandr Marchenko. All rights reserved.

/* Бібліотеки, які потрібні для роботи з семафорами */
import java.util.concurrent.Semaphore;

class Thread_1 implements Runnable
{
	/* Семафор */
	private Semaphore sem;

	/* Потоковий об'єкт */
	Thread t;

	/* конструктор */
	Thread_1(Semaphore semInit)
	{
		/* Створення нового потоку */
		t = new Thread(this, "Thread_1");
		/* Ініціалізація семафору */
		sem = semInit;
		/* Запуск потокової функції */
		t.start();
	}

	/* Потокова функція(метод) */
	public void run()
	{
		 int i = 0;
 		 /* Виведення на екран символу 'а' 100 разів. */
 		 for (i = 0; i<100; i++)
  		 {
   		 	System.out.print("a ");
  		 }
  		System.out.println("\nFunction 1 waits for the opening of the semaphore...\n");
  		try
  		{
			/* Функція чекає, поки якийсь інший потік відкриє семафор. */
  			sem.acquire();
			
  		}catch(InterruptedException e)
  			{
  				System.out.println("Thread_1 Interrupted\n");
  			}
  		System.out.println("\nSemaphore is opened!\n");
  		/* Виведення на екран символу 'b' 100 разів. */
  		for (i = 0; i<100; i++)
  		{
    			System.out.print("b ");
  		}
		System.out.println();
	}
}



class Thread_2 implements Runnable
{
	/* Семафор */
	private Semaphore sem;

	/* Потоковий об'єкт */
	Thread t;

	/* конструктор */
	Thread_2(Semaphore semInit)
	{
		/* Створення нового потоку */
		t = new Thread(this, "Thread_1");
		/* Ініціалізація семафору */
		sem = semInit;
		/* Запуск потокової функції */
		t.start();
	}

	/* Поотокова функція(метод) */
	public void run()
	{
		try
		{
			Thread.sleep(10);
		}catch(InterruptedException e)
			{
				System.out.println("Thread_2 interrupted\n");
			}
 		int i = 0;
  		/* Виведення на екран символу '1' 75 разів. */
  		for (i = 0; i<75; i++)
  		{
    			System.out.print("1 ");
  		}
  		/* Функція відкриває семафор. */
  		sem.release();

  		System.out.println("\nFunction 2 has opened the semaphore.\n");
  		/* Виведення на екран символу '2' 75 разів. */
  		for (i = 0; i<75; i++)
  		{
    			System.out.print("2 ");
  		}
		System.out.println();
	}
}

public class Main
{
	public static void main(String [] args)
	{
		/*Оголошення та ініціалізація семафору. */
		  Semaphore sem1 = new Semaphore(0, true);

		  /* Створюються потоки. */
		  Thread_1 thread_1 = new Thread_1(sem1);
		  Thread_2 thread_2 = new Thread_2(sem1);

		  /* Очікується завершення потоків. */
		 try
		 {
			 /* Очікується завершення потоків. */
			 thread_1.t.join();
			 thread_2.t.join();
		 }catch(InterruptedException e)
		 	{
			 	System.out.println("Main Interrupted\n");
		 	}

		  
	}
	
}
