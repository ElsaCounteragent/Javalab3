//назначение - модель коллекции домино, самое сложное - перемешивание
import java.util.Random;
public class Collection {
    private Domino[] dominoes;//массив домино
    public static final int DominoAmmount = 28; //сколько домино всего
    public static final int DominoMaxValue = 6; //максимальное значение на домино
    public static final int DominoMinValue = 0; //минимальное значение на домино

    public Collection (){ //конструктор создаёт коллекцию
        dominoes = new Domino[DominoAmmount]; 

        int position = 0;  //текущая позиция в массиве, для заполнения

        for (int firstValue = DominoMinValue; firstValue <= DominoMaxValue; firstValue++){
            for (int secondValue = firstValue; secondValue <= DominoMaxValue; secondValue++){
                dominoes[position] = new Domino(firstValue, secondValue, (position + 1) % DominoAmmount, (position - 1 + DominoAmmount) % DominoAmmount);
                position++;
            }
        }
    }
    public void print(){ //метод вывода на экран
        int cur = 0; //текущая позиция в массиве
        for (int i = 0; i < DominoAmmount - 1; i++, cur = dominoes[cur].getNextIndex()){
            dominoes[cur].print();
            System.out.print(", ");
        }
        dominoes[cur].print();
    }

    public Domino getDomino(int index){ //метод получения домино по индексу в массиве. Возвращает домино по индексу
        return dominoes[index];
    }

    public void shuffle(){ //метод перемешивания. Перемешивает доминошки.
        Random random = new Random(); //генератор случайных чисел
        for (int i = 0; i < DominoAmmount; i++){
            swap(i, random.nextInt(DominoAmmount));
        }
    }

    private void swap(int first, int second){ //метод перестановки домино по их позициям в массиве. Меняет указатели на след, пред. для всех нужных домино.
        int firstNext = dominoes[first].getNextIndex(); //позиция следующего у первого домино, сохраняем в переменную чтобы её не потерять
        
        dominoes[first].setNextIndex(dominoes[second].getNextIndex()); //первое домино теперь показывает на следующего второго как его следующее
        dominoes[dominoes[second].getNextIndex()].setPreviousIndex(first); //домино после второго теперь показывает на первое как его предыдущее
        
        dominoes[second].setNextIndex(firstNext); //второе домино теперь показывает на следующее первого как его следующее
        dominoes[dominoes[second].getNextIndex()].setPreviousIndex(second); //теперь это новое домино, на которое второе омино показывает как его следующее, имеет второе домино как его предыдущее
        
        int firstPrevious = dominoes[first].getPreviousIndex(); //позиция предыдущего у первого домино, сохраняем в переменную чтобы её не потерять

        dominoes[first].setPreviousIndex(dominoes[second].getPreviousIndex()); //аналогичные операции, только теперь мы за основу берем изменение previous, а next идет как вторичное
        dominoes[dominoes[second].getPreviousIndex()].setNextIndex(first);

        dominoes[second].setPreviousIndex(firstPrevious);
        dominoes[dominoes[second].getPreviousIndex()].setNextIndex(second);
    }
}
