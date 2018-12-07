package starbucksTeam;

public enum Size {
    Short {
        public float getCost() {
            return 1;
        }
        public String getName(){
            return "Short";
        }
    }, Tall {
        public float getCost() {
            return 2;
        }
        public String getName(){
            return "Tall";
        }
    },
    Grande {
        public float getCost() {
            return 2;
        }
        public String getName(){
            return "Grande";
        }
    };

    //public abstract float getCost();
    public abstract String getName();

}
