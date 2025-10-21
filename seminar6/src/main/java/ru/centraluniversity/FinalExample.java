package ru.centraluniversity;


/**
 * Использование ключевого слова 'final' для поля обеспечивает безопасную публикацию объекта.
 * Это означает, что после создания объекта и инициализации final-поля,
 * все потоки будут видеть корректное значение поля без дополнительной синхронизации.
 * Такое поведение гарантируется памятью моделью Java (Java Memory Model).
 */
public class FinalExample {
    private final int data;

    public FinalExample(int data) {
        this.data = data;
    }

    public int getData() { return data; }

    public static void main(String[] args) {
        FinalExample obj = new FinalExample(10);
        System.out.println(obj.getData()); // 10
    }
}