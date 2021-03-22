package employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trainee extends Employee {

	// attributes:
	// * apprenticeship start date
	// * apprenticeship length (in days)

	private final int appLength;
	private final LocalDate startDate;

	public Trainee(String firstName, String lastName,
				   LocalDate birthDate,
				   BigDecimal salary,
				   Manager manager,
				   LocalDate startDate,
				   int appLength) {

		super(firstName, lastName, birthDate, salary, manager);
		this.appLength = appLength;
		this.startDate = startDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}
}