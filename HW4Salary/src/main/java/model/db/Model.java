package model.db;

import model.Employee;

import java.util.List;

public class Model {
    private List<Employee> companyWorkers;

    public List<Employee> getCompanyWorkers() {
        return this.companyWorkers;
    }
    public void addEmployee(Employee employee) {
        this.companyWorkers.add(employee);
    }
}
