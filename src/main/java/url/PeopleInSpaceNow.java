package url;

import java.util.ArrayList;

public class PeopleInSpaceNow {
    public class People{
        private String name;
        private String craft;

        public String getName() {
            return name;
        }

        public String getCraft() {
            return craft;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", craft='" + craft + '\'' +
                    '}';
        }
    }
    private String message;
    private Integer number;
    private ArrayList<People> people;

    public String getMessage() {
        return message;
    }

    public Integer getNumber() {
        return number;
    }

    public ArrayList<People> getPeople() {
        return people;
    }

    @Override
    public String toString() {
        return "PeopleInSpaceNow{" +
                "message='" + message + '\'' +
                ", number=" +number +
                ", people=" + people +
                '}';
    }
}
