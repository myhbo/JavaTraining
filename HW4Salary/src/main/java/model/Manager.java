package model;

import java.util.Date;
import java.util.List;

public class Manager extends Employee {
    private String name;
    private Date birthDate;
    private Date startingWork;
    private String department;
    private List<Worker> managedWorkers;
    private final long MAIN_SALARY = 20000;
    private final long ADDITIONAL_SALARY= 1000;
    private final long ADDITIONAL_SALARY_PER_WORKER = 100;

    @Override
    public long getSalary() {
        Date currentDate = new Date();
        if (birthDate.getMonth() == birthDate.getMonth())
            return getMAIN_SALARY() + getADDITIONAL_SALARY() +
                    getADDITIONAL_SALARY_PER_WORKER() * managedWorkers.size();
        else return getMAIN_SALARY() + getADDITIONAL_SALARY_PER_WORKER() * managedWorkers.size();
    }
    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getStartingWork() {
        return startingWork;
    }

    public String getDepartment() {
        return department;
    }

    public List<Worker> getManagedWorkers() {
        return managedWorkers;
    }

    public long getMAIN_SALARY() {
        return MAIN_SALARY;
    }

    public long getADDITIONAL_SALARY() {
        return ADDITIONAL_SALARY;
    }

    public long getADDITIONAL_SALARY_PER_WORKER() {
        return ADDITIONAL_SALARY_PER_WORKER;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setStartingWork(Date startingWork) {
        this.startingWork = startingWork;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void addToManagedWorkers(Worker worker) {
        this.managedWorkers.add(worker);
    }
}
