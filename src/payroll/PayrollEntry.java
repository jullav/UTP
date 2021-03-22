package payroll;

import employee.Employee;
import employee.Worker;

import java.math.BigDecimal;

import static employee.Worker.*;


public final class PayrollEntry {

	private final Employee _employee;
	private final BigDecimal _salaryPlusBonus;
	private final BigDecimal _salary;
	private final BigDecimal _bonus;


	public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
		_employee = employee;
		_salary = employee.get_salary();
		_bonus = Worker.getBonus();
		if (_salary == null || bonus == null) {
			throw new IllegalArgumentException("Salary or bonus is null");
		}

		_salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
	}

	public BigDecimal getSalaryPlusBonus() {
		return _salaryPlusBonus;
	}
	public Employee getEmployee() {
		return _employee;
	}
	public BigDecimal getSalary() {
		return _salary;
	}
	public BigDecimal getBonus() {
		return _bonus; }

}