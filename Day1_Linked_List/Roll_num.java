class Roll_num {
    static class Student {
        int rollNumber;
        String name;
        int age;
        String grade;
        Student next;

        Student(int rollNumber, String name, int age, String grade) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.next = null;
        }
    }

    private Student head;

    public void addAtBeginning(int rollNumber, String name, int age, String grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    public void addAtEnd(int rollNumber, String name, int age, String grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    public void addAtPosition(int position, int rollNumber, String name, int age, String grade) {
        if (position == 0) {
            addAtBeginning(rollNumber, name, age, grade);
            return;
        }
        Student newStudent = new Student(rollNumber, name, age, grade);
        Student temp = head;
        for (int i = 0; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return;
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    public void deleteByRollNumber(int rollNumber) {
        if (head == null) return;
        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != rollNumber) {
            temp = temp.next;
        }
        if (temp.next == null) return;
        temp.next = temp.next.next;
    }

    public Student searchByRollNumber(int rollNumber) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) return temp;
            temp = temp.next;
        }
        return null;
    }

    public void displayAll() {
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll Number: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }

    public void updateGrade(int rollNumber, String newGrade) {
        Student temp = searchByRollNumber(rollNumber);
        if (temp != null) {
            temp.grade = newGrade;
        }
    }

    public static void main(String[] args) {
        Roll_num list = new Roll_num();
        list.addAtEnd(1, "Alice", 20, "A");
        list.addAtBeginning(2, "Bob", 21, "B");
        list.addAtPosition(1, 3, "Charlie", 22, "C");
        list.displayAll();
        list.updateGrade(2, "A+");
        list.displayAll();
        list.deleteByRollNumber(1);
        list.displayAll();
        Student student = list.searchByRollNumber(3);
        if (student != null) {
            System.out.println("Found: Roll Number: " + student.rollNumber + ", Name: " + student.name);
        }
    }
}