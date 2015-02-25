package org.bitbucket.calvinmwhu.chess.values;

/**
 * Created by calvinmwhu on 2/24/15.
 */
public enum Index {
    NONE(""),ZERO("0"),ONE("1"),TWO("2"),THREE("3"),FOUR("4"),FIVE("5"),SIX("6"),SEVEN("7"),EIGHT("8");

    private final String index;
    Index(String index){
        this.index = index;
    }

    public String getIndex(){
        return index;
    }
}
