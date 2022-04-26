package data;
/**
 * interface for objects that can be stored in collections
 */
public interface Collectionable extends Comparable<Collectionable>, Validateable{
    public int getId();
    /**
     * sets id, useful for replacing object in collection
     * @param ID
     */
    public void setId(int ID);
    
    public float getImpactSpeed();

    public String getName();

    /**
     * compairs two objects
     */
    public int compareTo(Collectionable worker);

    public boolean validate();
}
