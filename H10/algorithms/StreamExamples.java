package algorithms;
import java.util.Arrays;
import java.util.List;


public class StreamExamples {

	public static void main(String[] args) {
		
		System.out.println("First example:");
		List<String> names = Arrays.asList("Jan", "Pier", "Joris", "Korneel");
		names
			.stream()
			.filter(s -> s.startsWith("J"))
			.map(String::toUpperCase)
			.sorted()
			.forEach(System.out::println);
		
		System.out.println("Second example:");
		Arrays.stream(new int[] {1, 2, 3})
	    .map(n -> 2 * n + 1)
	    .average()
	    .ifPresent(System.out::println);
		
		System.out.println("Third example:");
		List<Book> books = Arrays.asList(
				new Book("Adams", 42),
				new Book("Adams", 50),
				new Book("Adams", 60),
				new Book("Tolkien", 30),
				new Book("Tolkien", 55)
				);
		
		Double combinedPriceOfBooksWrittenByAdamsVATincluded = books
			.stream()
			.filter(book -> book.getAuthor() == "Adams")
			.map(book -> book.getPrice() + book.getPrice() * 0.21)
			.reduce(0.0, (sum, price) ->  sum + price);			
		
		System.out.println(combinedPriceOfBooksWrittenByAdamsVATincluded);
	}
}
