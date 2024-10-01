package nology.employee.employee;

import java.time.LocalDate;
import java.time.Month;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UpdateEmployeeDTO {

  @Length(min = 2, max = 50, message = "First name must be between 2 and 50 characters long.")
  private String firstName;

  @Length(max = 50, message = "Middle name must not exceed 50 characters.")
  private String middleName;

  @Length(min = 2, max = 50, message = "Last name must be between 2 and 50 characters long.")
  private String lastName;

  @Email(message = "Email should be valid.")
  private String email;

  @Length(min = 10, max = 15, message = "Mobile number should be between 10 and 15 digits.")
  private String mobile;

  @Length(min = 2, max = 255, message = "Address must be between 2 and 255 characters.")
  private String address;

  private String employeeStatus;

  private Integer startDay;
  private String startMonth;
  private Integer startYear;
  private LocalDate startDate;

  private Integer finishDay;
  private String finishMonth;
  private Integer finishYear;
  private LocalDate finishDate;

  private Boolean onGoing;

  private String employmentBasis;

  @Max(value = 50, message = "Hours per week cannot exceed 50")
  private Integer hoursPerWeek;

  // Getters and setters
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

  public String getEmployeeStatus() {
    return employeeStatus;
  }

  public void setEmployeeStatus(String employeeStatus) {
    this.employeeStatus = employeeStatus;
  }

  public Integer getStartDay() {
    return startDay;
  }

  public void setStartDay(Integer startDay) {
    this.startDay = startDay;
    updateStartDate();
  }

  public String getStartMonth() {
    return startMonth;
  }

  public void setStartMonth(String startMonth) {
    this.startMonth = startMonth;
    updateStartDate();
  }

  public Integer getStartYear() {
    return startYear;
  }

  public void setStartYear(Integer startYear) {
    this.startYear = startYear;
    updateStartDate();
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public Integer getFinishDay() {
    return finishDay;
  }

  public void setFinishDay(Integer finishDay) {
    this.finishDay = finishDay;
    updateFinishDate();
  }

  public String getFinishMonth() {
    return finishMonth;
  }

  public void setFinishMonth(String finishMonth) {
    this.finishMonth = finishMonth;
    updateFinishDate();
  }

  public Integer getFinishYear() {
    return finishYear;
  }

  public void setFinishYear(Integer finishYear) {
    this.finishYear = finishYear;
    updateFinishDate();
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

  public String getEmploymentBasis() {
    return employmentBasis;
  }

  public void setEmploymentBasis(String employmentBasis) {
    this.employmentBasis = employmentBasis;
  }

  public Integer getHoursPerWeek() {
    return hoursPerWeek;
  }

  public void setHoursPerWeek(Integer hoursPerWeek) {
    this.hoursPerWeek = hoursPerWeek;
  }

  private void updateStartDate() {
    if (startDay != null && startMonth != null && startYear != null) {
      this.startDate = LocalDate.of(startYear, Month.valueOf(startMonth.toUpperCase()), startDay);
    }
  }

  private void updateFinishDate() {
    if (finishDay != null && finishMonth != null && finishYear != null) {
      this.finishDate = LocalDate.of(finishYear, Month.valueOf(finishMonth.toUpperCase()), finishDay);
    } else {
      this.finishDate = null;
    }
  }
}
