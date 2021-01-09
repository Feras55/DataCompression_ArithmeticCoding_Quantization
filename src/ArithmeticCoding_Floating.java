import java.util.ArrayList;
import java.util.HashMap;

public class ArithmeticCoding_Floating {
    String sequence;
    ArrayList<Symbol> probabilitiesTable = new ArrayList<Symbol>();
    HashMap<Character, Symbol> mp= new HashMap<Character, Symbol>();

    public ArithmeticCoding_Floating(String sequence, ArrayList<Symbol> probabilitiesTable, HashMap<Character, Symbol> mp) {
        this.sequence = sequence;
        this.probabilitiesTable = probabilitiesTable;
        this.mp = mp;
    }

    /*
        Lower (Symbol) = Lower+ Range * Low_Range(Symbol)
        Upper (Symbol)= Lower+ Range * High_Range(Symbol)
         */
    float compress(){
        float lower = mp.get(sequence.charAt(0)).lowRange;
        float upper = mp.get(sequence.charAt(0)).upperRange;
        for (int i = 1; i < sequence.length(); i++) {
            float newLower = lower + (upper - lower)*mp.get(sequence.charAt(i)).lowRange;
            float newUpper = lower + (upper - lower)*mp.get(sequence.charAt(i)).upperRange;
            lower = newLower;
            upper = newUpper;
        }
        ///picking value between final lower and upper
        float compressionCode = (float)(Math.floor(upper * 1000.0) / 1000.0);
        return compressionCode;
    }

    String decompress(int sz, float compressionCode){
        String seq = new String();
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < probabilitiesTable.size(); j++) {
                Symbol s =probabilitiesTable.get(j);
                if(s.lowRange<compressionCode
                        && s.upperRange>compressionCode){
                    seq+=s.name;
                    compressionCode = (compressionCode - s.lowRange) / (s.upperRange-s.lowRange);
                    break;
                }
            }
        }
        return seq;
    }

}


