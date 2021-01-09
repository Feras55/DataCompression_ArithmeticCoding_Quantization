public class Symbol {
    char name;
    float probability;
    float lowRange;
    float upperRange;

    public Symbol() {
    }

    public Symbol(char name, float probability, float lowRange, float highRange) {
        this.name = name;
        this.probability = probability;
        this.lowRange = lowRange;
        this.upperRange = highRange;
    }
}
