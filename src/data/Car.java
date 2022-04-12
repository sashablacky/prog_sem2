package data;

public class Car implements Validateable {
    private Boolean cool;

    public Car(Boolean howCool){
        cool = howCool;
    }

    /** 
     * @return Boolean
     */
    public Boolean isCool(){
        return cool;
    }

    /** 
     * @return String
     */
    @Override
    public String toString(){
        String s = "";
        s += "{";
        s += "  \"cool\" : " + Boolean.toString(cool) + "\n";
        s += "}";
        return s;
    }

    public boolean validate(){
        return (cool!=null);
    }
}
