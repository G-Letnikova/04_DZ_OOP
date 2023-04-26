
import java.util.ArrayList;
import java.util.Arrays;

/**
     Домашняя работа, задача:
     ========================

     a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
     b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
     поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
     c. Для хранения фруктов внутри коробки можно использовать ArrayList;
     d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
     вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
     e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую
     подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
     Можно сравнивать коробки с яблоками и апельсинами;
     f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
     Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
     Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
     g. Не забываем про метод добавления фрукта в коробку.
 */
public class Homework {
    public static void main(String[] args) {
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Orange orange1 = new Orange();
        Orange orange2 = new Orange();

        ArrayList<Apple> apples = new ArrayList<>(Arrays.asList(apple1, apple2, apple3));
        ArrayList<Orange> oranges = new ArrayList<>(Arrays.asList(orange1, orange2));

        Box<Orange> boxOfOrange1 = new Box<>(oranges);
        Box<Apple> boxOfApple2 = new Box<>(apples);
        Box<Apple> boxOfApple3 = new Box<>(new ArrayList<>());

        System.out.println(boxOfOrange1);
        System.out.println(boxOfApple2);
        System.out.println(boxOfApple3);

        System.out.println("---Добавим яблоко в коробку---");
        boxOfApple2.addFruit(apple3);
        System.out.println(boxOfApple2);

        System.out.println("---Сравним вес коробок---");
        if (boxOfApple2.compare(boxOfOrange1))
          System.out.printf("вес коробок #%d и #%d равен\n", boxOfApple2.getNumberBox(),boxOfOrange1.getNumberBox() );
        else  System.out.printf("коробки #%d и #%d имеют разный вес\n", boxOfApple2.getNumberBox(),boxOfOrange1.getNumberBox());

        System.out.printf("вес коробки #%d : %.2f\n", boxOfApple2.getNumberBox(), boxOfApple2.getWeight());
        System.out.printf("вес коробки #%d : %.2f\n", boxOfOrange1.getNumberBox(), boxOfOrange1.getWeight());

        System.out.println("---Пересыпем яблоки из одной коробки в другую---");
        boxOfApple2.moveTo(boxOfApple3);
        System.out.println(boxOfApple2);
        System.out.println(boxOfApple3);

    }
}

abstract class Fruit{
    protected final float weight;
    protected final String name;
    public Fruit(float weight, String neme) { this.weight = weight; this.name = neme;}
    public float getWeight() {
        return weight;
    }
    public String getName(){ return name;}

}

class Apple extends Fruit{
    public Apple() { super(1.0f,"яблоки"); ;}

}
class Orange extends Fruit{
    public Orange() {
        super(1.5f, "апельсины");
    }
}

class Box<T extends Fruit>{
    private final ArrayList<T> fruits;
    private int number;
    private static int count;
    { count++; }

    public Box(ArrayList<T> fruits) {
        this.fruits = fruits;
        this.number = count;
    }

    public int getNumberBox() {
        return number;
    }

    public float getWeight(){
        if (fruits.size() == 0) return 0;
        return fruits.get(0).getWeight() * fruits.size();
    }
    }
    public void addFruit(T fruict){
        this.fruits.add(fruict);

    }
    public boolean compare(Box<? extends Fruit> box){
        return this.getWeight() == box.getWeight();
    }
    public void moveTo(Box<? super T> box){
        box.fruits.addAll(this.fruits);
        fruits.clear();
    }

    @Override
    public String toString() {
        if (fruits.size() !=0)
            return String.format("В коробке #%d %s: %d шт. ", getNumberBox(), fruits.get(0).name, fruits.size());
        return String.format("В коробке #%d нет фруктов", getNumberBox());
    }

}

