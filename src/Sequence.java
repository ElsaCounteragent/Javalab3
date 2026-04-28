//назначение - модель последовательности домино. Реализация удаления домино и последовательности.
public class Sequence {
    private Collection collection; //ссылка на коллекцию
    private int firstIndex; //индекс первого домино в последовательности. Он будет первым выведен на экран
    private int afterRemove; //индекс, которым мы шагаем по массиву
    
    public Sequence(Collection collection) { //конструктор
        this.collection = collection;
        firstIndex = 0;
        afterRemove = 0;
    }

    public Domino extractNext(int offset){ //метод извлечения домино с указанным шагом N (N == offset). Возвращает домино которое осталось последним
        if (collection.getDomino(firstIndex).getNextIndex() == firstIndex){
            return removeLast();
        }

        while (offset > 0){
            afterRemove = collection.getDomino(afterRemove).getNextIndex();
            offset--;
        }
        
        if (afterRemove == firstIndex){
            firstIndex = collection.getDomino(afterRemove).getNextIndex();
        }

        Domino deleting = collection.getDomino(afterRemove); //сохраняем домино, которое будем удалять, чтобы его не потелять

        afterRemove = deleting.getNextIndex();

        //убираем индекс удаляемого домино из цепочки индексации
        collection.getDomino(deleting.getPreviousIndex()).setNextIndex(deleting.getNextIndex());
        collection.getDomino(deleting.getNextIndex()).setPreviousIndex(deleting.getPreviousIndex());

        deleting.setNextIndex(-1); //обнуляем индексы удаленного домино, так как оно больше не участвует в последовательности
        deleting.setPreviousIndex(-1);

        return deleting; //возвращаем удалённый элемент чтобы его можно было вывести на экран
    }

    private Domino removeLast(){ //метод, который вызывается, когда осталось только одно домино
        Domino deleting = collection.getDomino(firstIndex);  //домино, которое будем удалять
        deleting.setNextIndex(-1);
        deleting.setPreviousIndex(-1);
        firstIndex = -1; //обнуляем эти параметры тоже
        afterRemove = -1;

        return deleting;
    }

    public boolean isEmpty(){ //проверка на пустоту последовательности
        return firstIndex == -1;
    }

    public void print(){ //метод вывода на экран
        if (isEmpty()){
            System.out.println("Sequence is empty");
            return;
        }
        
        int current = firstIndex; //текущая позиция в массиве

        do{
            collection.getDomino(current).print();
            System.out.print(", ");
            current = collection.getDomino(current).getNextIndex();
        }
        while (current != firstIndex);
    }

}