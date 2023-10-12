package model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Courier {
    private int id;
    private String name;
    private String vehicleType;
    private String vehiclePlate;
    private String phoneNumber;
    private boolean isEmpty;

    public Courier(String name, String vehicleType, String vehiclePlate, String phoneNumber, boolean isEmpty) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.vehiclePlate = vehiclePlate;
        this.phoneNumber = phoneNumber;
        this.isEmpty = isEmpty;
    }
    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehiclePlate='" + vehiclePlate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isEmpty=" + isEmpty +
                '}';
    }
}
