package collection;

import data.HumanBeing;

import java.util.LinkedList;

/**
 * interface for storing elements
 * @param <T> type of elements
 */
public interface CollectionManager<T> {
    /**
     * generates new unique ID for collection
     * @return
     */
    int generateNextId();

    void sort();
    
    LinkedList<T> getCollection();

    /**
     * adds new element
     * @param element
     */
    void add(T element);

    /**
     * get information about collection
     * @return info
     */
    String getInfo();

    /**
     * checks if collection contains element with particular id
     * @param ID
     * @return
     */
    boolean checkID(Integer ID);

    /**
     * removes element by id
     * @param id
     */
    void removeByID(Integer id);

    /**
     * updates element by id
     * @param id
     * @param newElement
     */
    void updateByID(Integer id, T newElement);

    /**
     * get collection size
     * @return
     */
    int getSize();
   
    void clear();

    void load(LinkedList<HumanBeing> data);

    void removeFirst();

    void removeLast();

    void shuffle();

    void sum_of_minutes_of_waiting();

    void min_by_minutes_of_waiting();

    void print_unique_impact_speed();

}
