public class Hero implements Comparable<Hero>{

    public String name;
    public long power;

    public Hero(String _name, long _power){
        name = _name;
        power = _power;
    }

    @Override
    public int compareTo(Hero o) {
        return o.power < power ? 1:-1;
    }


}
