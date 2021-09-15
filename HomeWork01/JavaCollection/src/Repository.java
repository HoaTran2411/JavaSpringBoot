import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Repository {

    // Lọc người có độ tuổi từ 20-30
    public List<Person> choosePeopleAgeFr20To30(List<Person> list) {
        return list.stream().filter(person -> (person.getAge() >= 20 && person.getAge() <= 30))
                .collect(Collectors.toList());
    }

    // Tuổi trung bình của tất cả mọi người
    public double averageAgeOfAll(List<Person> list) {
        int totalAge = 0;
        for (Person person : list) {
            totalAge += person.getAge();
        }
        return ((double)totalAge) / ((double)list.size());
    }

    // Tuổi trung bình theo từng quốc tịch
    public Map<String, Double> calcuAverageAgeByNation(List<Person> list) {
        Map<String, Integer> mapPeople = new HashMap<>();
        Map<String, Integer> mapAge = new HashMap<>();
        Map<String, Double> mapAverageAge = new HashMap<>();

        for (Person person : list) {
            mapPeople.put(person.getNationality(), mapPeople.getOrDefault(person.getNationality(), 0) + 1);
            mapAge.put(person.getNationality(), mapAge.getOrDefault(person.getNationality(), 0) + person.getAge());
        }

        for (var entry : mapPeople.entrySet()) {
            if (mapAge.containsKey(entry.getKey())) {
                mapAverageAge.put(entry.getKey(), (double)(mapAge.get(entry.getKey()) / (double) entry.getValue()));
            }
        }

        return mapAverageAge;

    }
}
