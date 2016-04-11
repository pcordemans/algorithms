package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Job {
	private int id;
	private String name;

	public Job(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// Comparator anonymous class implementation
	public static Comparator<Job> idComparator = new Comparator<Job>() {
		@Override
		public int compare(Job job1, Job job2) {
			return (job1.getId() - job2.getId());
		}
	};

	public static void main(String[] args) {

		Queue<Job> jobPriorityQueue = new PriorityQueue<>(7, idComparator);
		Random rand = new Random(1000); // seeded random

		for (int i = 0; i < 7; i++) {
			int id = Math.abs(rand.nextInt());
			Job j = new Job(id, "Job " + id);
			jobPriorityQueue.add(j);

			System.out.println("Added " + j.getName());
		}

		int elements = jobPriorityQueue.size();
		for (int i = 0; i < elements; i++) {
			Job j = jobPriorityQueue.poll();
			System.out.println("Next " + j.getName());
		}

	}
}
