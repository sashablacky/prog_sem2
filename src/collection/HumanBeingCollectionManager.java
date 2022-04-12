package collection;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Collections;
import java.time.LocalDate;

import data.HumanBeing;
import static io.OutputManager.*;

//@Alias LinkedList<HumanBeing>  NameToNumbers;
/**
 * Operates collection.
 */
public class HumanBeingCollectionManager implements CollectionManager<HumanBeing> {
    private LinkedList<HumanBeing> collection;
    private HashSet<Integer> uniqueIds;
    private java.time.LocalDateTime initDate;
    public HumanBeingCollectionManager(){
        uniqueIds = new HashSet<>();
        collection = new LinkedList<>();
        initDate = java.time.LocalDateTime.now();
    }
    public int generateNextId(){
        if (collection.isEmpty())
            return 1;
        else {
            Integer id = collection.getLast().getId() + 1;
            if(uniqueIds.contains(id)){
                while (uniqueIds.contains(id)) id+=1;
            }
            uniqueIds.add(id);
            return id;
        }
    }
    public void sort(){
        Collections.sort(collection);
    }

}
