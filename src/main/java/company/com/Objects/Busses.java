package company.com.Objects;

public class Busses {
    private int id;
    private int VIN_number;
    private String status;

    public Busses(){}
    public Busses(int id, int VIN_number, String status) {
        this.id = id;
        this.VIN_number = VIN_number;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVIN_number() {
        return VIN_number;
    }

    public void setVIN_number(int VIN_number) {
        this.VIN_number = VIN_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}