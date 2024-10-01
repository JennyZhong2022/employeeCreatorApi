package nology.employee.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.List;
import java.util.Collections;

import nology.employee.common.ValidationErrors;
import nology.employee.common.exceptions.ServiceValidationException;

public class EmployeeServiceUnitTest {
  @Mock
  private EmployeeRepository repo;

  @Mock
  private ModelMapper mapper;

  @InjectMocks
  @Spy
  private EmployeeService employeeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void findAllEmployees_success() {
    // Given
    List<Employee> expectedEmployees = List.of(new Employee(), new Employee());
    when(repo.findAll()).thenReturn(expectedEmployees);

    // When
    List<Employee> employees = employeeService.findAllEmployees();

    // Then
    assertFalse(employees.isEmpty());
    assertEquals(2, employees.size());
    verify(repo).findAll();
  }

  @Test
  public void findEmployeeById_success() {
    // Given
    Optional<Employee> expectedEmployee = Optional.of(new Employee());
    when(repo.findById(1L)).thenReturn(expectedEmployee);

    // When
    Optional<Employee> employee = employeeService.findEmployeeById(1L);

    // Then
    assertTrue(employee.isPresent());
    verify(repo).findById(1L);
  }

  @Test
  public void createEmployee_success() throws Exception {
    // Arrange
    CreateEmployeeDTO mockEmployeeDTO = new CreateEmployeeDTO();
    mockEmployeeDTO.setFirstName("John");
    mockEmployeeDTO.setLastName("Doe");
    mockEmployeeDTO.setEmail("john.doe@example.com");
    mockEmployeeDTO.setMobile("1234567890");
    mockEmployeeDTO.setAddress("123 Baker Street");
    mockEmployeeDTO.setEmployeeStatus("Permanent");
    mockEmployeeDTO.setStartDay(1);
    mockEmployeeDTO.setStartMonth("January");
    mockEmployeeDTO.setStartYear(2020);

    Employee mockEmployee = new Employee();
    when(mapper.map(mockEmployeeDTO, Employee.class)).thenReturn(mockEmployee);
    when(repo.save(mockEmployee)).thenReturn(mockEmployee);

    // Act
    Employee result = employeeService.createEmployee(mockEmployeeDTO);

    // Assert
    assertNotNull(result);
    assertEquals(mockEmployee, result);
    verify(repo).save(mockEmployee);
  }

  @Test
  public void updateEmployeeById_success() throws Exception {
    // Arrange
    Long id = 1L;
    UpdateEmployeeDTO dto = new UpdateEmployeeDTO();
    dto.setFirstName("Updated Name");

    Employee existingEmployee = new Employee();
    existingEmployee.setFirstName("Original Name");

    // Set up the mapper behavior
    doAnswer(invocation -> {
      Object source = invocation.getArguments()[0];
      Object target = invocation.getArguments()[1];
      if (source instanceof UpdateEmployeeDTO && target instanceof Employee) {
        Employee emp = (Employee) target;
        UpdateEmployeeDTO empDTO = (UpdateEmployeeDTO) source;
        emp.setFirstName(empDTO.getFirstName()); // Mock the behavior of setting first name
        return null;
      }
      return null;
    }).when(mapper).map(any(UpdateEmployeeDTO.class), any(Employee.class));

    when(repo.findById(id)).thenReturn(Optional.of(existingEmployee));
    when(repo.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

    // Act
    Optional<Employee> result = employeeService.updateEmployeeById(id, dto);

    // Assert
    assertTrue(result.isPresent());
    assertEquals("Updated Name", result.get().getFirstName());
    verify(repo).save(existingEmployee);
    verify(mapper).map(dto, existingEmployee);
  }

  @Test
  public void deleteEmployeeById_success() {
    // Arrange
    Long id = 1L;

    when(repo.findById(id)).thenReturn(Optional.of(new Employee()));

    // Act
    boolean result = employeeService.deleteEmployeeById(id);

    // Assert
    assertTrue(result);
    verify(repo).deleteById(id);
  }

  @Test
  public void deleteAllEmployees_withEmployees() {
    // Arrange
    List<Employee> employees = List.of(new Employee());
    when(repo.findAll()).thenReturn(employees);

    // Act
    boolean result = employeeService.deleteAllEmployees();

    // Assert
    assertTrue(result);
    verify(repo).deleteAll();
  }

}