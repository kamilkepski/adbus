package pl.kepski.busdemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ads")
public class Ad {

    @ManyToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Proszę podać markę pojazdu.")
    @Column(nullable = false, length = 80)
    private String brand;

    @NotEmpty(message = "Proszę podać model pojazdu.")
    @Column(nullable = false, length = 80)
    private String model;

    @NotEmpty(message = "Proszę wybrać kategorię pojazdu.")
    @Column(nullable = false)
    private String type;

    @NotEmpty(message = "Proszę podać rok produkcji pojazdu.")
    @Column(name = "production_year", nullable = false)
    private String productionYear;

    @NotNull(message = "Proszę podać ilość miejsc.")
    @Column(name = "number_of_seats", nullable = false)
    private Integer numberOfSeats;

    private String mileage;

    @NotNull(message = "Proszę podać cenę pojazdu.")
    @Column(nullable = false)
    private Float price;

    @Column(name = "emission_standard")
    private String emissionStandard;

    @Column(name = "engine_power")
    private String enginePower;

    @Column(name = "engine_capacity")
    private String engineCapacity;

    @Column(name = "gearbox_type")
    private String gearboxType;

    @Column(name = "number_of_axles")
    private String numberOfAxles;

    public Ad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getEmissionStandard() {
        return emissionStandard;
    }

    public void setEmissionStandard(String emissionStandard) {
        this.emissionStandard = emissionStandard;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public String getNumberOfAxles() {
        return numberOfAxles;
    }

    public void setNumberOfAxles(String numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
