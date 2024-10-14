public class SeasonOfTheYear {

    public enum Season {
        SPRING {
            @Override
            public void whatToDo() {
                System.out.println("Time to plant flowers and enjoy the bloom!");
            }
        },
        SUMMER {
            @Override
            public void whatToDo() {
                System.out.println("Perfect time for beach activities and picnics!");
            }
        },
        AUTUMN {
            @Override
            public void whatToDo() {
                System.out.println("Enjoy the amazing fall colors and harvest!");
            }
        },
        WINTER {
            @Override
            public void whatToDo() {
                System.out.println("Time for snowball fights and cozy evenings by the fire!");
            }
        };

        public abstract void whatToDo();
    }

    public static void main(String[] args) {
        for (Season season : Season.values()) {
            System.out.print(season + ": ");
            season.whatToDo();
        }
    }
}

