package it.unibo.es2;

public interface Logics {

    /**
     * 
     * @param elem
     * @return new value.
     */
    String hit(Pair<Integer, Integer> elem);

    /**
     * 
     * @return the current value.
     */
    String getResult(Pair<Integer, Integer> elem);

    /**
     * 
     * @return to know wether to quit or not.
     */
    boolean toQuit(Pair<Integer, Integer> elem);
}
