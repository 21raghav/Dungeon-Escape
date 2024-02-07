import java.io.FileNotFoundException;
import java.io.IOException;


public class FindShortestPath {
	// declare class variables(dungeon object,the object for the current instance of the FindShortestPath class,the priority queue to store hexagons)
	private static Dungeon dungeon;
	
	private static FindShortestPath chambers;
	
	private static DLPriorityQueue<Hexagon> queue;

	
	public FindShortestPath(String filename) throws InvalidDungeonCharacterException, FileNotFoundException, IOException {
		dungeon = new Dungeon(filename);
	}
// to check if the given chamber is adjacent to dragon 
 static boolean adjacentToDragon(Hexagon chamber) {
	    for (int i = 0; i < 6; i++) {
	        Hexagon neighbourChamber = chamber.getNeighbour(i);
	        if (neighbourChamber != null) {
	            if (neighbourChamber.isDragon()) {
	                return true;
	            } else {
	                continue;
	            }
	        } else {
	            continue;
	        }
	    }
	    return false;
	}


	
	public static void main(String[] args) {
		try {
			// check if the input file is given or not
			if (args.length < 1) {
				throw new IllegalArgumentException("Please input a file in the CMD: ");
			}
//initialise the varibale and objects 
			chambers = new FindShortestPath(args[0]);
			queue = new DLPriorityQueue<>();
			// Mark the starting chamber as start and add it to the queue
			dungeon.getStart().markStart();
			queue.add(dungeon.getStart(), 0);
			dungeon.getStart().markEnqueued();
//loop till the queue is finnaly empty 
			while (!queue.isEmpty()) {
				// Remove the hexagon with the minimum distance
				Hexagon currChamber = queue.removeMin();
				currChamber.markDequeued();
				// Check if the current hexagon is the exit or not
				if (currChamber.isExit()) {
					System.out.println("Exit found: The length of dungeon is: "
							+ queue.size() + ". distance to shortest path is: "
							+ (currChamber.getDistanceToStart() + 1));
					return;
				}
// check if the  current chamber is adjacent to dragon
				if (!adjacentToDragon(currChamber)) {
					for (int i = 0; i < 6; i++) {
						Hexagon neighbour = currChamber.getNeighbour(i);
						if (neighbour != null && !neighbour.isWall()) {
							// Calculate the distance to start
							int D = currChamber.getDistanceToStart() + 1;
							boolean modifiedDist = false;
							// Check if the distance to start of the neighbor hexagon can be updated
							if (neighbour.getDistanceToStart() > D) {
								modifiedDist = true;
								neighbour.setDistanceToStart(D);
								neighbour.setPredecessor(currChamber);
							}

							if (neighbour.isMarkedEnqueued() && modifiedDist) {
								queue.updatePriority(neighbour, neighbour.getDistanceToExit(dungeon) + neighbour.getDistanceToStart());
							}

							if (!neighbour.isMarkedDequeued()) {
								queue.add(neighbour, neighbour.getDistanceToExit(dungeon) + neighbour.getDistanceToStart());
								neighbour.markEnqueued();
							}
						}
					}
				}
			}

			System.out.println("No exit found.");

		} catch (FileNotFoundException e) {
			System.out.println("Input file not found. " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error reading input file. " + e.getMessage());
		} catch (InvalidDungeonCharacterException e) {
			System.out.println("Invalid input file format. " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}