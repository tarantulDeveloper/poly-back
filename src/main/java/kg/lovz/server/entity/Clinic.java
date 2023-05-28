package kg.lovz.server.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Clinic extends CommonModel{
    String phone;
    String workTime;
    String workDays;
    String oblast;
    String address;
    String webSiteUrl;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "clinic_id")
    List<Feedback> feedbackList = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "clinic_services",
    joinColumns = @JoinColumn(name = "clinic_id"),
    inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<ClinicWork> clinicWorks = new ArrayList<>();
}
