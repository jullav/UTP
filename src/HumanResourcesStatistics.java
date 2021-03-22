import employee.Employee;
import employee.Manager;
import employee.Trainee;
import employee.Worker;
import payroll.PayrollEntry;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public final class HumanResourcesStatistics {

	private static List<Employee> employees(Employee employee) {
		if (employee instanceof Manager) {
			var manager = (Manager) employee;
			var allSubordinates = manager
					.getSubordinates();
			var subEmployees =
					allSubordinates.stream()
							.filter(s -> s instanceof Manager == false)
							.collect(Collectors.toList());
			var subManagers =
					allSubordinates.stream()
							.filter(s -> s instanceof Manager)
							.map(s -> (Manager) s)
							.collect(Collectors.toList());

			var resultList = Stream
					.concat(subManagers.stream() , subEmployees.stream())
					.collect(Collectors.toList());
			resultList.add(manager);
			return resultList;
		}
		return new ArrayList<>() {
			{
				add(employee);
			}
		};
	}



	public static List<PayrollEntry> payroll(List<Employee> employees) {

		return employees
				.stream()
				.map(employee -> new PayrollEntry(employee);
				.map(payroll -> payroll.getSalaryPlusBonus())
				.collect(toList());
	}


	// payroll for all subordinates
	public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
		return manager.getSubordinates()
				.stream()
				.map(emp -> new PayrollEntry(emp))
				.map(payroll -> payroll.getSalaryPlusBonus())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}


	public static BigDecimal bonusTotal(List<Employee> employees) {
		return employees()
				.stream()
				.map(emp -> new PayrollEntry(emp))
				.map(payroll -> payroll.getBonus())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static Employee longestSeniority(List<Employee> employees) {
		if (employees == null || employees.isEmpty())
			return null;
		var now = LocalDate.now();

		var trainee = employees.stream()
				.filter(e -> e instanceof Trainee)
				.map(t -> (Trainee) t)
				.map(t -> {
					var daysEmployed = Math.abs(Period.between(t.getStartDate() , now).getDays());
					return daysEmployed;
				})
				.collect(toList())
				.get(0);

		var worker = employees.stream()
				.filter(e -> e instanceof Worker)
				.map(t -> (Worker) t)
				.map(t -> {
					var daysEmployed = Math.abs(Period.between(t.getEmpStart() , now).getDays());
					return daysEmployed;
				})
				.collect(toList())
				.get(0);
		return (Employee) employees;
	}

	public static BigDecimal highestSalaryWOBonus(){
		return employees()
				.stream()
				.map(emp -> new PayrollEntry(emp).getSalary())
				.max(BigDecimal::compareTo)
				.get();
	}

	public static BigDecimal highestSalaryWBonus() {
		return employees()
				.stream()
				.map(emp -> new PayrollEntry(emp).getSalaryPlusBonus())
				.max(BigDecimal::compareTo)
				.get();
	}

	private static List<Employee> compareTo(List<Employee> source, BigDecimal compareTo, int expectedCompareResult) {
		if(source == null || compareTo == null)
			return null;
		if(expectedCompareResult < -1 || expectedCompareResult > 1)
			throw new IllegalArgumentException("expected compare result can be -1, 0, 1");
		return source.stream()
				.map(employee -> new PayrollEntry(employee))
				.filter(s -> s.getSalaryPlusBonus().compareTo(compareTo) == expectedCompareResult)
				.map(s -> s.getEmployee())
				.collect(Collectors.toList());
	}


	public static List<Employee> earnMoreThanThousand(List<Employee> source){
		return compareTo(source, new BigDecimal(1000), 1);
	}


	/// ...
	// rest of the methods specified in the assignment description
	
	
	/**
	 * samples for functional processing in Java
	 * 
	 */
	public static List<Short> getAges(List<Employee> employees) {
		if (employees == null) {
			return null;
		}
		List<Short> ages = employees //
				.stream() //
				.map(emp -> emp.getAge()) //
				.collect(toList());
		return ages;
	}

	public static void printAges(List<Employee> employees) {
		if (employees == null) {
			return;
		}
		employees //
				.stream() //
				.map(emp -> (int) emp.getAge()) //
				.forEach(age -> System.out.print(age + ", "));
	}

	//
	// average age for the Employees whose first name starts with 'A' and they are older than 20
	public static short getAverageAgeInline(List<Employee> employees) {
		if (employees == null) {
			return 0;
		}
		int employeeTotalAge = employees //
				.stream() //
				.filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
				.map(emp -> (int) emp.getAge()) //
				.reduce(0, //
						(total, age) -> total + age);

		long filteredEmployeesCount = employees //
				.stream() //
				.filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
				.count();

		return (short) (employeeTotalAge / filteredEmployeesCount);
	}

	public static short getAverageAgeMethodReference(List<Employee> employees) {
		if (employees == null) {
			return 0;
		}
		int employeeTotalAge = employees //
				.stream() //
				.map(emp -> (int) emp.getAge()) //
				.reduce(0, HumanResourcesStatistics::totalAge);
		return (short) (employeeTotalAge / employees.size());
	}

	public static short getMaxAgeInline(List<Employee> employees) {
		short employeeMaxAge = employees //
				.stream() //
				.map(emp -> emp.getAge()) //
				.reduce((short) 0, //
						(maxAge, age) -> {
							if (maxAge < age) {
								return age;
							} else {
								return maxAge;
							}
						});
		return employeeMaxAge;
	}

	public static short getMaxAgeMethodReference(List<Employee> employees) {
		short employeeMaxAge = employees //
				.stream() //
				.map(emp -> emp.getAge()) //
				.reduce((short) 0, HumanResourcesStatistics::maxAge);
		return employeeMaxAge;
	}

	private static int totalAge(int totalAge, int age) {
		return totalAge + age;
	}

	private static short maxAge(short maxAge, short age) {
		if (maxAge < age) {
			return age;
		} else {
			return maxAge;
		}
	}
}

