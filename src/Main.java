import java.awt.*;
import java.util.*;

public class Main {

    /***
     *
     * @param seq
     * @param table
     * @return true if each character in the sequence is found in the probability table & total percentages equals 1, false otherwise.
     */
    static public boolean checkTable(String seq, ArrayList<Symbol> table){
        int cntr = 0;
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        for(int i=0;i<seq.length();i++){
            lhs.add(seq.charAt(i));
        }
        for (Character ch : lhs) {
            for (int i = 0; i < table.size(); i++) {
                if(ch==table.get(i).name){
                    cntr++;
                    break;
                }
            }
        }

        return ((cntr==table.size()));

    }

    static public ArithmeticCoding_Floating takeInput() {
        Scanner sc = new Scanner(System.in);
        float nextLow = 0; //to prepare ranges
        ArrayList<Symbol> probabilitiesTable = new ArrayList<Symbol>();
        HashMap<Character, Symbol> mp = new HashMap<Character, Symbol>();
        System.out.println("Enter sequence: ");
        String seq = sc.next();
        System.out.println("Enter probability table size");
        int sz = sc.nextInt();
        for (int i = 0; i < sz; i++) {
            Symbol ch = new Symbol();
            System.out.println("Enter Character");
            ch.name = sc.next().charAt(0);
            System.out.println("Enter Probability");
            ch.probability = sc.nextFloat();
            ch.lowRange = nextLow;
            ch.upperRange = nextLow + ch.probability;
            nextLow = ch.upperRange;
            probabilitiesTable.add(ch);
            mp.put(ch.name, ch); ///mapping each character to its representative symbol in the table
        }
        if (!checkTable(seq, probabilitiesTable)) {
            System.out.println("A symbol is missing in the table");

        }

        ArithmeticCoding_Floating art = new ArithmeticCoding_Floating(seq, probabilitiesTable, mp);
        return  art;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean work = true;
        while (work) {
            System.out.println("Choose Algorithm\n1. Arithmetic Coding (Floating)\n" +
                    "2. Arithmetic Coding (Binary)\n" +
                    "3. Vector Quantization\n" +
                    "4. exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ArithmeticCoding_Floating art = takeInput();
                    Float compressionCode = (art.compress());
                    System.out.println("Compression Code: " + compressionCode);
                    System.out.println("Do you want to decompress this code? press 1 for yes");
                    if(sc.nextInt()==1){
                        System.out.println("Enter size of string to be decompressed");
                        int sz = sc.nextInt();
                        System.out.println(art.decompress(sz,compressionCode));
                    }
                    break;


            case 2:
                break;
            case 3:
                break;
            case 4:
                work = false;
                break;
            default:
                System.out.println("Choose 1-4 based on desired operation");
        }


        }
    }
}
