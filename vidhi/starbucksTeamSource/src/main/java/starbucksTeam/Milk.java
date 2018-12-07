package starbucksTeam;

public enum Milk {
    Almond{
        public float getCost() {
            return 1;

        }
         public String getName(){
             return "Almond";
         }
    }, Milk2 {
        public float getCost() {
            return 2;
        }
        public String getName(){
            return "2%Milk";
        }
    },
    Coconut {
        public float getCost() {
            return 2;
        }
        public String getName(){
        return "Coconut";
        }
    };

    //public abstract float getCost();
    public abstract String getName();

}
