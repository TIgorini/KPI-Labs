## Memory management

## Task
Написати програму, що моделює процес управління пам’яттю (розподілу
пам’яті для процесів), перетворення віртуальної адреси у фізичну, пошук у
пам’яті за запитами процесів, вивільнення пам’яті)

__Спосіб організації пам’яті__: 
Фіксовані розділи (без використання зовнішньої пам’яті). Кількість 
розділів меньша, ніж кількість процесів. Процеси утворюють загальну чергу до
розділів пам’яті. Використовується сегментований адресний простір. Сегменти
можуть розміщуватися в різних розділах. Розміри процесів задаються
випадково.