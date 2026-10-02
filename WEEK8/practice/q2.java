import java.util.*;
import java.time.LocalDate;
import java.util.function.Function;

interface LibraryItem {
    LocalDate getDueDate();
    String getTitle();
}

class Book implements LibraryItem {
    private String title;
    private LocalDate date;

    Book(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }

    public LocalDate getDueDate() {
        return date.plusDays(14);
    }

    public String getTitle() {
        return title;
    }
}

class DVD implements LibraryItem {
    private String title;
    private LocalDate date;

    DVD(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }

    public LocalDate getDueDate() {
        return date.plusDays(7);
    }

    public String getTitle() {
        return title;
    }
}

class Magazine implements LibraryItem {
    private String title;
    private LocalDate date;

    Magazine(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }

    public LocalDate getDueDate() {
        return date.plusDays(3);
    }

    public String getTitle() {
        return title;
    }
}

public class q2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LocalDate currentDate = LocalDate.of(2023, 10, 26);

        Map<String, Function<String, LibraryItem>> factory = Map.of(
            "BOOK", title -> new Book(title, currentDate),
            "DVD", title -> new DVD(title, currentDate),
            "MAGAZINE", title -> new Magazine(title, currentDate)
        );

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();

            int space = line.indexOf(' ');
            String type = line.substring(0, space);

            String title = line.substring(space + 1).trim();

            if (title.startsWith("\"") && title.endsWith("\"")) {
                title = title.substring(1, title.length() - 1);
            }

            LibraryItem item = factory.get(type).apply(title);

            System.out.println(item.getTitle() + ": " + item.getDueDate());
        }
    }
}