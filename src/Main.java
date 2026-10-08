package lab2;

import java.util.ArrayList;
import java.util.List;

class Name {
    private final String firstName;
    private final String lastName;
    private final String patronymic;

    public Name(String firstName) {
        this(firstName, null, null);
    }

    public Name(String firstName, String lastName) {
        this(firstName, lastName, null);
    }

    public Name(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (lastName != null && !lastName.isEmpty()) {
            sb.append(lastName).append(" ");
        }
        if (firstName != null && !firstName.isEmpty()) {
            sb.append(firstName).append(" ");
        }
        if (patronymic != null && !patronymic.isEmpty()) {
            sb.append(patronymic).append(" ");
        }
        return sb.toString().trim();
    }
}

class Person {
    private String name;
    private int height;

    public Person(String name, int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Рост должен быть положительным числом!");
        }
        this.name = name;
        this.height = height;
    }

    @Override
    public String toString() {
        return name + ", рост: " + height;
    }
}

class Department {
    private String name;
    private Employee manager;
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        }
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
}

class Employee {
    private String name;
    private Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
        if (department != null) {
            department.addEmployee(this);
        }
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public List<Employee> getAllDepartmentEmployees() {
        if (this.department != null) {
            return this.department.getEmployees();
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        if (department == null) {
            return name + " не работает в отделе";
        }
        if (department.getManager() == this) {
            return name + " начальник отдела " + department.getName();
        }
        String managerName = (department.getManager() != null) ? department.getManager().getName() : "не назначен";
        return name + " работает в отделе " + department.getName() + ", начальник которого " + managerName;
    }
}

class Pistol {
    private int ammo;

    public Pistol() {
        this.ammo = 5;
    }

    public Pistol(int ammo) {
        if (ammo < 0) {
            throw new IllegalArgumentException("Количество патронов не может быть отрицательным!");
        }
        this.ammo = ammo;
    }

    public void shoot() {
        if (ammo > 0) {
            System.out.println("Бах!");
            ammo--;
        } else {
            System.out.println("Клац!");
        }
    }

    @Override
    public String toString() {
        return "Пистолет (патронов: " + ammo + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("задание 1.2");
        Person cleopatra = new Person("Клеопатра", 152);
        Person pushkin = new Person("Пушкин", 167);
        Person vladimir = new Person("Владимир", 189);
        System.out.println(cleopatra);
        System.out.println(pushkin);
        System.out.println(vladimir);

        System.out.println("\nзадание 1.3 и 4.5");
        Name name1 = new Name("Клеопатра");
        Name name2 = new Name("Александр", "Пушкин", "Сергеевич");
        Name name3 = new Name("Владимир", "Маяковский");
        Name name4 = new Name("Христофор", "Бонифатьевич");

        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);
        System.out.println(name4);

        System.out.println("\nЗадание 2.3 и 4.3");
        Department itDept = new Department("IT");

        Employee petrov = new Employee("Петров", itDept);
        Employee kozlov = new Employee("Козлов", itDept);
        Employee sidorov = new Employee("Сидоров", itDept);

        itDept.setManager(kozlov);

        System.out.println(petrov);
        System.out.println(kozlov);
        System.out.println(sidorov);

        System.out.println("\nСписок всех сотрудников отдела через Петрова:");
        List<Employee> itStaff = petrov.getAllDepartmentEmployees();
        for (Employee emp : itStaff) {
            System.out.println("- " + emp.getName());
        }

        System.out.println("\nЗадание 5.1");
        Pistol pistol = new Pistol(3);
        for (int i = 1; i <= 5; i++) {
            System.out.print("Выстрел " + i + ": ");
            pistol.shoot();
        }
    }
}
