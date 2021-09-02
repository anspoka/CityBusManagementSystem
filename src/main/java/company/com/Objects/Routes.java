package company.com.Objects;

public class Routes {
    private int id;
    private int route_number;
    private String route_name;

    public Routes(){}
    public Routes(int id, int route_number, String route_name) {
        this.id = id;
        this.route_number = route_number;
        this.route_name = route_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoute_number() {
        return route_number;
    }

    public void setRoute_number(int route_number) {
        this.route_number = route_number;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }
}