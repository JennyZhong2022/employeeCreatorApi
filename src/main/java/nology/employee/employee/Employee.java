package nology.employee.employee;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "employee_creator")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String firstName;

  @Column(columnDefinition = "TEXT")
  private String middleName;

  @Column(columnDefinition = "TEXT")
  private String lastName;

  @Column
  @Temporal(TemporalType.TIMESTAMP)

  private Date createdAt;

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @PrePersist
  public void onCreate() {
    Date timestamp = new Date();
    createdAt = timestamp;
    updatedAt = timestamp;
  }

  @PreUpdate
  public void onUpdate() {
    updatedAt = new Date();
  }

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @Email
  @Column(columnDefinition = "VARCHAR(255)")
  private String email;

  @Column(columnDefinition = "VARCHAR(15)")
  private String mobile;

  @Column(columnDefinition = "TEXT")
  private String employeeStatus;

  @Column(columnDefinition = "DATE")
  private LocalDate startDate;

  @Column(columnDefinition = "DATE")
  private LocalDate finishDate;

  @Column
  private Boolean onGoing;

  @Column(columnDefinition = "VARCHAR(50)")
  private String employmentBasis;

  @Column
  private Integer hoursPerWeek;

  public Employee() {
  }

  public Long getId() {
    return id;
  }

  // public void setId(Long id) {
  // this.id = id;
  // }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(columnDefinition = "VARCHAR(255)")
  private String address;

  public String getEmployeeStatus() {
    return employeeStatus;
  }

  public void setEmployeeStatus(String employeeStatus) {
    this.employeeStatus = employeeStatus;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(LocalDate finishDate) {
    this.finishDate = finishDate;
  }

  public Boolean getOnGoing() {
    return onGoing;
  }

  public void setOnGoing(Boolean onGoing) {
    this.onGoing = onGoing;
  }

  public Integer getHoursPerWeek() {
    return hoursPerWeek;
  }

  public void setHoursPerWeek(Integer hoursPerWeek) {
    this.hoursPerWeek = hoursPerWeek;
  }

  public String getEmploymentBasis() {
    return employmentBasis;
  }

  public void setEmploymentBasis(String employmentBasis) {
    this.employmentBasis = employmentBasis;
  }

}