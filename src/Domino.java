//назначение - модель домино с указателями на следующее и предыдущее для последовательности
public class Domino {
    private final int valueA; //первое значение в паре
    private final int valueB; // второе значение в паре
    private int nextIndex; //индекс следующего
    private int previousIndex; //индекс предыдущего

    public Domino(int valueA, int valueB, int nextIndex, int previousIndex) { //конструктор. Создаёт домино с 2 значениями и указателями на следующее и предыдущее
        this.valueA = valueA;
        this.valueB = valueB;
        this.nextIndex = nextIndex;
        this.previousIndex = previousIndex;
        
    }
    public int getValueA(){ //различные геттеры и сеттеры
        return valueA;
    }

    public int getValueB(){
        return valueB;
    }
    
    public int getNextIndex(){
        return nextIndex;
    }

    public void setNextIndex(int nextIndex){
        this.nextIndex = nextIndex;
    }

    public void setPreviousIndex(int previousIndex){
        this.previousIndex = previousIndex;
    }

    public int getPreviousIndex(){ //конец геттеров и сеттеров
        return previousIndex;
    }

    public void print(){ //метод вывода на экран
        System.out.print("[" + valueA + " | " + valueB + "]");
    }

}
