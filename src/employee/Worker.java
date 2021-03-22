package employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

	// attributes
	// * employment date
	// * bonus

	private final LocalDate empStart;
	private static BigDecimal bonus;


	public Worker(String firstName, String lastName, LocalDate birthDate, BigDecimal salary,
				  Manager manager,
				  LocalDate empStart,
				  BigDecimal bonus) {

		super(firstName, lastName, birthDate, salary, manager);
		this.empStart = empStart;
		this.bonus = bonus;
	}

	public static BigDecimal getBonus() {
		return bonus;
	}

	public LocalDate getEmpStart() {
		return empStart;
	}

}