package nology.employee.employee;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEmployeeDTO {

  @NotBlank(message = "First name is required.")
  @Length(min = 2, max = 50, message = "First name must be between 2 and 50 characters long.")
  private String firstName;

  @Length(max = 50, message = "Middle name must not exceed 50 characters.")
  private String middleName;

  @NotBlank(message = "Last name is required.")
  @Length(min = 2, max = 50, message = "Last name must be between 2 and 50 characters long.")
  private String lastName;

  @NotBlank(message = "Email is required.")
  @Email(message = "Email should be valid.")
  private String email;

  @NotBlank(message = "Mobile number is required.")
  @Length(min = 10, max = 15, message = "Mobile number should be between 10 and 15 digits.")
  private String mobile;

  @NotBlank(message = "Address is required.")
  @Length(min = 2, max = 255, message = "Address must be detailed.")
  private String address;

  @NotBlank(message = "Employee status is required.")
  private String employeeStatus;

  @NotNull(message = "Start day is required.")
  private Integer startDay;

  @NotNull(message = "Start month is required.")
  private String startMonth; // Use String for ease, convert to Month in setter

  @NotNull(message = "Start year is required.")
  private Integer startYear;

  private LocalDate startDate;

  private Integer finishDay;

  private String finishMonth;

  private Integer finishYear;

  private void updateStartDate() {
    if (startDay != null && startMonth != null && startYear != null) {
      startDate = LocalDate.of(startYear, Month.valueOf(startMonth.toUpperCase()), startDay);
    }
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

  private LocalDate finishDate; // no validation needed

  private void updateFinishDate() {
    if (finishDay != null && finishMonth != null && finishYear != null) {
      finishDate = LocalDate.of(finishYear, Month.valueOf(finishMonth.toUpperCase()), finishDay);
    } else {
      finishDate = null;
    }
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

  private Boolean onGoing;

  @NotBlank(message = "Employment basis is required.")
  private String employmentBasis;

  @Max(value = 50, message = "Hours per week cannot exceed 50")
  private Integer hoursPerWeek;

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

}
