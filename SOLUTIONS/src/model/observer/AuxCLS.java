package model.observer;

public class AuxCLS {

    private static AuxCLS instance;

    private String text;

    private AuxCLS(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static AuxCLS getInstance(){
        if(instance == null){
            instance = new AuxCLS();
        }

        return instance;
    }
}
