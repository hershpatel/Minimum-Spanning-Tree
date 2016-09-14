package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		PartialTreeList partialTreeList = new PartialTreeList();
		for (int i=0; i<graph.vertices.length; i++) {
			Vertex currentVertex = graph.vertices[i];
			PartialTree newTree = new PartialTree(currentVertex);
			Vertex.Neighbor currentNeighbor = currentVertex.neighbors;
			while (currentNeighbor != null) {
				newTree.getArcs().insert(new PartialTree.Arc(currentVertex, currentNeighbor.vertex, currentNeighbor.weight));
				currentNeighbor = currentNeighbor.next;
			}
			partialTreeList.append(newTree);
		}
		return partialTreeList;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		ArrayList<PartialTree.Arc> arcArrayList=new ArrayList<PartialTree.Arc>();
		while (ptlist.size()>1) {
			PartialTree PTX=ptlist.remove();
			
			MinHeap<PartialTree.Arc> PQX=PTX.getArcs();
			
			PartialTree.Arc minArc=PQX.deleteMin();
			
			Vertex v2=minArc.v2;
			
			while (v2.getRoot()==PTX.getRoot()) {
				minArc=PQX.deleteMin();
				v2=minArc.v2;
			}
			
			arcArrayList.add(minArc);
			
			PartialTree PTY=ptlist.removeTreeContaining(v2);
			
		 	PTX.merge(PTY);
		 	
			ptlist.append(PTX);
			
		}
		return arcArrayList;
	}
}
