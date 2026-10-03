import java.util.*;
import java.io.*;

class Employee implements Serializable {

    private String name;
    private int id;
    private String department;
    private int salary;

    Employee(String name, int id, String department, int salary) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name +
               ", id=" + id +
               ", department=" + department +
               ", salary=" + salary + "]";
    }
}


class EmployeeManagement {

    ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        employees.add(e);
        System.out.println("Employee added successfully!");
    }

    public void removeEmployee(int id) {

        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i).getId() == id) {

                employees.remove(i);

                System.out.println("Employee removed successfully!");
                return;
            }
        }

        System.out.println("Employee not found!");
    }

    public void updateEmployee(int id, String name,
                               String department, int salary) {

        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i).getId() == id) {

                employees.get(i).setName(name);
                employees.get(i).setDepartment(department);
                employees.get(i).setSalary(salary);

                System.out.println("Employee updated successfully!");
                return;
            }
        }

        System.out.println("Employee not found!");
    }

    public void searchEmployee(int id) {

        for (Employee e : employees) {

            if (e.getId() == id) {

                System.out.println("Employee found:");
                System.out.println(e);
                return;
            }
        }

        System.out.println("Employee not found!");
    }

    public void viewAllEmployees() {

        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public void getHighestSalaryEmployee() {

        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        Employee highest = employees.get(0);

        for (Employee e : employees) {

            if (e.getSalary() > highest.getSalary()) {
                highest = e;
            }
        }

        System.out.println("Highest Salary Employee:");
        System.out.println(highest);
    }

    public void calculateTotalSalary() {

        int total = 0;

        for (Employee e : employees) {
            total += e.getSalary();
        }

        System.out.println("Total Salary: " + total);
    }

    public void saveEmployees() {

        try {

            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream("employees.dat"));

            output.writeObject(employees);
            output.close();

            System.out.println("Employees saved successfully!");

        } catch (IOException e) {

            System.out.println("Error while saving employees!");
        }
    }

    @SuppressWarnings("unchecked")
    public void loadEmployees() {

        try {

            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream("employees.dat"));

            employees = (ArrayList<Employee>) input.readObject();

            input.close();

            System.out.println("Employees loaded successfully!");

        } catch (FileNotFoundException e) {

            System.out.println("No saved data found!");

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error while loading employees!");
        }
    }
}


class Main {

    public static void main(String[] args) {

        EmployeeManagement obj = new EmployeeManagement();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n================================");
            System.out.println(" Employee Management System");
            System.out.println("================================");

            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. View All Employees");
            System.out.println("6. Highest Salary Employee");
            System.out.println("7. Calculate Total Salary");
            System.out.println("8. Save Employees");
            System.out.println("9. Load Employees");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    sc.nextLine();

                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter employee id: ");
                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter department: ");
                    String department = sc.nextLine();

                    System.out.print("Enter salary: ");
                    int salary = sc.nextInt();

                    Employee e =
                            new Employee(name, id, department, salary);

                    obj.addEmployee(e);

                    break;


                case 2:

                    System.out.print("Enter employee id to remove: ");
                    int removeId = sc.nextInt();

                    obj.removeEmployee(removeId);

                    break;


                case 3:

                    System.out.print("Enter employee id to update: ");
                    int updateId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new department: ");
                    String newDepartment = sc.nextLine();

                    System.out.print("Enter new salary: ");
                    int newSalary = sc.nextInt();

                    obj.updateEmployee(
                            updateId,
                            newName,
                            newDepartment,
                            newSalary
                    );

                    break;


                case 4:

                    System.out.print("Enter employee id to search: ");
                    int searchId = sc.nextInt();

                    obj.searchEmployee(searchId);

                    break;


                case 5:

                    obj.viewAllEmployees();

                    break;


                case 6:

                    obj.getHighestSalaryEmployee();

                    break;


                case 7:

                    obj.calculateTotalSalary();

                    break;


                case 8:

                    obj.saveEmployees();

                    break;


                case 9:

                    obj.loadEmployees();

                    break;


                case 10:

                    System.out.println("Thank you!");

                    sc.close();

                    return;


                default:

                    System.out.println("Invalid choice!");
            }
        }
    }
}