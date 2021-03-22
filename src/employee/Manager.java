package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public final class Manager extends Worker {

	// attributes
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (derived --- i.e. calculated on the fly --- a list of subordinates in all hierarchy)

	private final List<Employee> _subordinates;

	public Manager(String firstName, String lastName,
				   LocalDate birthDate,
				   BigDecimal salary,
				   LocalDate empStart,
				   BigDecimal bonus,
				   List<Employee> subordinates,
				   Manager manager) {
		super(firstName, lastName, birthDate, salary, manager, empStart , bonus);
		_subordinates = subordinates;
	}

	public List<Employee> getSubordinates() {
		return _subordinates;
	}

}