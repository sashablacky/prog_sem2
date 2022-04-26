package collection;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.HumanBeing;
import exceptions.FileException;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import static io.OutputManager.print;
import static io.OutputManager.printErr;

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

    /**
     * Return collection
     * @return Collection
     */
    public LinkedList<HumanBeing> getCollection()
    {
        return collection;
    }


    /**
     * Get information about collection
     * @return Information
     */
    public String getInfo(){
        return "LinkedList of humans, size: " + Integer.toString(collection.size()) + ", initialization date: " + initDate.toString();
    }

    /**
     * Add element to collection
     * @param humanBeing Element of collection
     */
    public void add(HumanBeing humanBeing){
        humanBeing.setId(generateNextId());
        collection.add(humanBeing);
        print("Added element:");
        print(humanBeing.toString());
    }

    /**
     * Give info about is this ID used
     * @param ID ID
     * @return is it used or not
     */
    public boolean checkID(Integer ID){
        for (HumanBeing humanBeing: collection)
        {
            if (humanBeing.getId() == ID)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete element by ID
     * @param id ID
     */
    public void removeByID(Integer id){
        for (HumanBeing humanBeing : collection){
            if (humanBeing.getId() == id){
                collection.remove(humanBeing);
                uniqueIds.remove(id);
                print("element #"+Integer.toString(id)+" successfully deleted");
                return;
            }
        }
    }

    /**
    * Delete element by ID
    * @param id ID
    */
    public void updateByID(Integer id, HumanBeing newHumanBeing){
        int idx = 0;
        for (HumanBeing humanBeing : collection){
            if (humanBeing.getId() == id){
                newHumanBeing.setId(id);
                collection.set(idx, newHumanBeing);
                print("element #"+Integer.toString(id)+" successfully updated");
                return;
            }
            idx += 1;
        }
    }

    /**
     * Get size of collection
     * @return Size of collection
     */
    public int getSize(){
        return collection.size();
    }

    public void clear(){
        collection.clear();
        uniqueIds.clear();
    }

    public void removeFirst(){
        int id = collection.getFirst().getId();
        collection.removeFirst();
        uniqueIds.remove(id);
        print("element #"+Integer.toString(id)+" successfully deleted");

    }
    public void removeLast(){
        int id = collection.getLast().getId();
        collection.removeLast();
        uniqueIds.remove(id);
        print("element #"+Integer.toString(id)+" successfully deleted");

    }
    public void print_unique_impact_speed(){
        LinkedList<Float> speeds = new LinkedList<>();
        print("unique impact speeds:");
        for (HumanBeing humanBeing: collection){
            if (! speeds.contains(humanBeing.getImpactSpeed())){
                print(humanBeing.getImpactSpeed());
                speeds.add(humanBeing.getImpactSpeed());
            }
        }
    }
    public long sum_of_minutes_of_waiting(){
        long sum = 0;
        for (HumanBeing humanBeing: collection){
            sum += humanBeing.getMinutesOfWaiting();
        }
        return sum;
    }
    public HumanBeing min_by_minutes_of_waiting(){
        long min_minutes = collection.getFirst().getMinutesOfWaiting();
        HumanBeing res = collection.getFirst();
        for (HumanBeing humanBeing: collection){
            if(humanBeing.getMinutesOfWaiting() < min_minutes){
            min_minutes = humanBeing.getMinutesOfWaiting();
            res = humanBeing;
            }
        }
        return res;
    }
    public void deserializeCollection(String xml){
        try {
            if (xml == null || xml.equals("")){
                collection =  new LinkedList<HumanBeing>();
            } else {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(xml));
                XMLDecoder decoder = new XMLDecoder(bis);
                LinkedList<HumanBeing> decodedCollection = (LinkedList<HumanBeing>) decoder.readObject();
                decoder.close();
                bis.close();
                collection = decodedCollection;
            }
        } catch (FileException e){
            printErr(e.toString());
        } catch(IOException e){
            printErr(e.toString());
        } 
    }

    public String serializeCollection() throws IOException {
        try {
            if (collection == null || collection.isEmpty()) return "";

            XmlMapper xmlMapper = new XmlMapper();

            xmlMapper.writeValueAsString(new HumanBeingCollectionManager());

        } catch(IOException e){
            printErr(e.toString());
        }
        return null;
    }
}
