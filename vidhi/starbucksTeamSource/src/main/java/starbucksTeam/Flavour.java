package starbucksTeam;

public enum Flavour {

    CMO{
        public float getCost() {
            return 1;

        }
        public String getName(){
            return "Caramel Macchiato";
        }
    }, CL {
        public float getCost() {
            return 2;
        }
        public String getName(){
            return "Caffe latte";
        }
    },
    CM {
        public float getCost() {
            return 2;
        }
        public String getName(){
            return "Caffe Mocha";
        }

    },
    Tea {
        public float getCost() {
            return 2;
        }
        public String getName(){
            return "Tea";
        }

    };

    //public abstract float getCost();
    public abstract String getName();
}
