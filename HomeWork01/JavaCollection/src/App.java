import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        Repository repo = new Repository();

        List<Person> listPeople = List.of(new Person("John", "USA", 24), new Person("David", "USA", 30),
                new Person("Trang", "Viet Nam", 11), new Person("Hoang", "Viet Nam", 40),
                new Person("Nga", "Viet Nam", 27), new Person("Van", "Viet Nam", 22),
                new Person("Maggie", "Finland", 28), new Person("Liu", "China", 41), new Person("Han", "Singapore", 27),
                new Person("Phuc", "Viet Nam", 37), new Person("Julia", "Italia", 19), new Person("Fruu", "Italia", 22),
                new Person("Jessica", "China", 44), new Person("Kim", "China", 33));

        // Lọc người có độ tuổi từ 20-30
        System.out.println("-----------------------------------------------------------------------");
        List<Person> listPeopleAgeFr20To30 = repo.choosePeopleAgeFr20To30(listPeople);
        System.out.println("Danh sách người có độ tuổi từ 20 đến 30 tuổi: ");
        for (Person person : listPeopleAgeFr20To30) {
            System.out.println(person);
        }

        // Tuổi trung bình của tất cả mọi người
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("Tuổi trung bình của tất cả mọi người bằng: %.2f", repo.averageAgeOfAll(listPeople));
        System.out.println();

        // Tuổi trung bình theo từng quốc tịch
        System.out.println("-----------------------------------------------------------------------");
        Map<String, Double> mapAverageAge = repo.calcuAverageAgeByNation(listPeople);
        System.out.println("Tuổi trung bình theo từng quốc tịch: ");
        for (var entry: mapAverageAge.entrySet()){
            System.out.printf("Quốc tịch: %s - %.2f \n", entry.getKey(), entry.getValue());
        }

    }
}
