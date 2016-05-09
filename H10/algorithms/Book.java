package algorithms;

public class Book {
	private String author;
	private double price;
	
	public Book(String author, double price){
		this.author = author;
		this.price = price;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String toString(){
		return "Author: " + author + " Price: " + price;
	}
}