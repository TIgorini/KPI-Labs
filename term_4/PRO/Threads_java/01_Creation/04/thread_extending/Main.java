/* ПЕРЕДАВАННЯ АРГУМЕНТУ У ПОТІК І ПОВЕРНЕННЯ ЗНАЧЕННЯ З ПОТОКУ
 * ПРИ СТВОРЕННІ ПОТОКУ ЗІ СПАДКУВАННЯМ КЛАСУ Thread.
 */

//  Copyright (c) 2009 Oleksandr Marchenko. All rights reserved.

class ChildThread extends Thread{
	
		private int in_value; // дані, що передаються у потік
		private int out_value;// дані, що повертаються з потоку
				
		/* Конструктор классу ChildThread */
		ChildThread(int in_val){
			
			/* Виклик батьківського конструктора
			 * та створення нового потоку */
			super("Thild Thread");			
			
			in_value = in_val; 

			start(); // запускаємо новий потік
		}
		
		/* Потокова функція */ 
		public void run(){
			/* Обчислення суми від 1 до in_value */	
			int sum = 0;
			for(int i = 1; i <= in_value; i++)
			       sum = sum + i;
			
			/* Встановлюємо значення, що повернеться у головний потік */
			out_value = sum;
		}
		
		/* Метод для повернення значення із дочірнього потоку у головний */
		public int get_out_value(){
			return out_value;
		}
}

public class Main{
	/* Головний потік */
	public static void main(String [] args){
		 
		int number = 10;
		int sum;
		
		/* Запускаємо потік, який обчислює потрібну суму
		 * і передаємо йому число number для обчислення суми від 1 до number. */
		ChildThread thread = new ChildThread(number);

		/* Виконуємо інші дії */

		
		/* Використання методу join потребує конструкції обробки виключень */
		try{
		/* Чекаємо завершення потоку */
		thread.join();
		}catch(InterruptedException e){
			System.out.println("Interrupted");
		}

		/* Приймаємо значення, яке повертає потік Thread, у змінну sum. */
		sum = thread.get_out_value();

		/* Виводимо обчислений результат. */
		System.out.println("Sum of numbers from 1 to " + number + " is " + sum + "\n");
	}

}

