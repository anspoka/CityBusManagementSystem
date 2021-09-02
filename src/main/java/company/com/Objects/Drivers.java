package company.com.Objects;

public class Drivers {
    private int id;
    private String name;
    private String surname;
    private String status;
    private String assigned_bus;
    private int assigned_route;

    public Drivers(){}

    public Drivers(int id, String name, String surname, String status, String assigned_bus, int assigned_route) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.assigned_bus = assigned_bus;
        this.assigned_route = assigned_route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssigned_bus() {
        return assigned_bus;
    }

    public void setAssigned_bus(String assigned_bus) {
        this.assigned_bus = assigned_bus;
    }

    public int getAssigned_route() {
        return assigned_route;
    }

    public void setAssigned_route(int assigned_route) {
        this.assigned_route = assigned_route;
    }



}