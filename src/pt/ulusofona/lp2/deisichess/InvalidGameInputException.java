package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception{
    public InvalidGameInputException() {
        super();
    }

    public int getLineWithError(){
        return 0;
    }

    public String getProblemDescription(){
        return "";
    }
}
