package apps;

import java.io.IOException;
import java.util.ArrayList;

import structures.Graph;

public class Driver {
	public static void main(String[] args) throws IOException {
		Graph inputGraph = new Graph("graph1.txt");
		PartialTreeList partialTreeList = MST.initialize(inputGraph);
		ArrayList<PartialTree.Arc> listOfArcs = MST.execute(partialTreeList);
		System.out.println(listOfArcs.toString());
	}
}