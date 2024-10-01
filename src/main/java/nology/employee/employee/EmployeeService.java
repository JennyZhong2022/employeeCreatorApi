package nology.employee.employee;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import nology.employee.common.ValidationErrors;
import nology.employee.common.exceptions.ServiceValidationException;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository repo;

  @Autowired
  private ModelMapper mapper;

  public Employee createEmployee(@Valid CreateEmployeeDTO data) throws Exception {
    ValidationErrors errors = new ValidationErrors();
    Employee newPost = mapper.map(data, Employee.class);

    if (errors.hasErrors()) {
      throw new ServiceValidationException(errors);
    }

    return this.repo.save(newPost);

  }

  public List<Employee> findAllEmployees() {
    return this.repo.findAll();
  }

  public Optional<Employee> findEmployeeById(Long id) {
    return this.repo.findById(id);
  }

  public Optional<Employee> updateEmployeeById(Long id, @Valid UpdateEmployeeDTO data) throws Exception {
    Optional<Employee> result = this.findEmployeeById(id);
    if (result.isEmpty()) {
      return result;
    }

    Employee foundEmployee = result.get();
    mapper.map(data, foundEmployee);

    ValidationErrors errors = new ValidationErrors();

    if (errors.hasErrors()) {
      throw new ServiceValidationException(errors);
    }
    Employee updateEmployee = this.repo.save(foundEmployee);

    return Optional.of(updateEmployee);
  }

  public boolean deleteEmployeeById(Long id) {
    Optional<Employee> result = this.findEmployeeById(id);
    if (result.isEmpty()) {
      return false;
    }
    this.repo.deleteById(id);
    // this.repo.delete(result.get());
    return true;
  }

  public boolean deleteAllEmployees() {
    List<Employee> result = this.findAllEmployees();
    if (result.isEmpty()) {
      return false;
    }
    this.repo.deleteAll();
    return true;
  }

  public List<Employee> findEmployeesByName(String name) {
    return repo.findByAnyName(name);
  }

  public List<Employee> findEmployeesByStatus(String status) {
    return repo.findByEmployeeStatus(status);
  }

  public List<Employee> findEmployeesByFilters(String name, String status, String basis) {
    return repo.findByOptionalCriteria(name, status, basis);
  }

}
