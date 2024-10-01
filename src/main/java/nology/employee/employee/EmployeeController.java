package nology.employee.employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import nology.employee.common.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping
  public ResponseEntity<Employee> createEmployee(@Valid @RequestBody CreateEmployeeDTO data) throws Exception {
    Employee createdPost = this.employeeService.createEmployee(data);
    return new ResponseEntity<Employee>(createdPost, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Employee>> findAllEmployees() {
    List<Employee> employees = this.employeeService.findAllEmployees();
    return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) throws NotFoundException {
    Optional<Employee> result = this.employeeService.findEmployeeById(id);
    Employee foundEmployee = result
        .orElseThrow(() -> new NotFoundException("Couldn't find employee with id " + id));
    return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Employee>> findEmployeeByName(@PathVariable String name) throws NotFoundException {
    List<Employee> employees = this.employeeService.findEmployeesByName(name);
    if (employees.isEmpty()) {
      throw new NotFoundException("Couldn't find any employees with name: " + name);
    }
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<Employee>> findEmployeesByStatus(@PathVariable String status) throws NotFoundException {
    List<Employee> employees = this.employeeService.findEmployeesByStatus(status);
    if (employees.isEmpty()) {
      throw new NotFoundException("Couldn't find any employees with status: " + status);
    }
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping("/filter")
  public ResponseEntity<List<Employee>> findEmployeesByFilters(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String status,
      @RequestParam(required = false) String basis) throws NotFoundException {
    List<Employee> employees = this.employeeService.findEmployeesByFilters(name, status, basis);

    if (employees.isEmpty()) {
      throw new NotFoundException("No employees found with the provided filters.");
    }
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id,
      @Valid @RequestBody UpdateEmployeeDTO data) throws Exception {
    Optional<Employee> result = this.employeeService.updateEmployeeById(id, data);
    Employee foundEmployee = result
        .orElseThrow(() -> new NotFoundException("Couldn't find employee with id " + id));
    return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id) throws NotFoundException {
    boolean result = this.employeeService.deleteEmployeeById(id);
    if (result == false) {
      throw new NotFoundException("Couldn't find employee with id " + id);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping
  public ResponseEntity<Employee> deleteAllEmployees() {
    boolean result = this.employeeService.deleteAllEmployees();
    if (result == false) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

  }
}
