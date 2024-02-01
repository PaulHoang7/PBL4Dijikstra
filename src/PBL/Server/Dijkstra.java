package PBL.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
	private static int INFINITY = 0;
	int matrix[][] = new ReadFile().loadData("Graph.txt");
	int length1 = matrix.length;
	private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public String dijkstra(char startSymbol, char targetSymbol, char finalSymbol) {
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            Vertex vertex = new Vertex(i, i);
            vertex.setDistance(INFINITY);
            vertices.add(vertex);
        }
        Vertex startVertex = vertices.get(Vertex.getColFromSymbol(startSymbol));
        Vertex targetVertex = vertices.get(Vertex.getColFromSymbol(targetSymbol));
        Vertex finalVertex = vertices.get(Vertex.getColFromSymbol(finalSymbol));

        startVertex.setDistance(0);

        Queue<Vertex> vertexQueue = new PriorityQueue<>(Comparator.comparing(Vertex::getDistance));
        	vertexQueue.add(startVertex);
	        
	        while (!vertexQueue.isEmpty()) {
	            Vertex current = vertexQueue.poll();
	            // xét đỉnh hàng xóm
	            for (Vertex neighbor : vertices) { 
	                int nextWeight = matrix[current.getRow()][neighbor.getCol()];
	                // đỉnh hàng xóm tồn tại đường đi và khác đỉnh bắt đầu, tránh trường hợp quay ngược.
	                if (neighbor != startVertex && nextWeight != INFINITY) {
	                    int distance = nextWeight + current.getDistance();
	                    // chưa tồn tại đường đi
	                    if (neighbor.getPrev() == null) {
	                        neighbor.setDistance(distance);
	                        neighbor.setPrev(current);
	                        vertexQueue.add(neighbor);
	                    }
	                    // đường mới ngắn hơn đường cũ
	                    else if (vertexQueue.contains(neighbor) && distance < neighbor.getDistance()) {
	                        // lấy con đường mới ngắn hơn
	                        neighbor.setDistance(distance);
	                        neighbor.setPrev(current);
	                        // cập nhật thứ tự ưu tiên
	                        vertexQueue.remove(neighbor);
	                        vertexQueue.add(neighbor);
	                    }
	                }
	                if (neighbor == finalVertex) {
		                  if (neighbor != startVertex && nextWeight != INFINITY) {
		                      int distance = nextWeight + current.getDistance();
		                      // chưa tồn tại đường đi
		                      if (neighbor.getPrev() == null) {
		                          neighbor.setDistance(distance);
		                          neighbor.setPrev(current);
		                          vertexQueue.add(neighbor);
		                      }
		                      // đường mới ngắn hơn đường cũ
		                      else if (vertexQueue.contains(neighbor) && distance < neighbor.getDistance()) {
		                          // lấy con đường mới ngắn hơn
		                          neighbor.setDistance(distance);
		                          neighbor.setPrev(current);
		                          // cập nhật thứ tự ưu tiên
		                          vertexQueue.remove(neighbor);
		                          vertexQueue.add(neighbor);
		                      }
		                  }
		                  break;
	                }
	            }
	        }
	        
        if (targetVertex.getPrev() == null) {
            System.out.println("KHÔNG TỒN TẠI ĐƯỜNG ĐI TỪ " + startSymbol + " TỚI " + targetSymbol);
        } else {
            System.out.println("ĐƯỜNG ĐI TỪ " + startSymbol + " TỚI " + targetSymbol + " MẤT " + targetVertex.getDistance());
            printPath(startVertex, targetVertex);
        }
        return printPath(startVertex, targetVertex) + "  " + targetVertex.getDistance();
    }
	public String printPath(Vertex startVertex, Vertex targetVertex) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.insert(0, " " + targetVertex.getSymbol());
        while (targetVertex.getPrev() != null) {
            if (targetVertex == startVertex) {
                break;
            }
            stringBuilder.insert(0, " " + targetVertex.getPrev().getSymbol());
            targetVertex = targetVertex.getPrev();
        }
        return stringBuilder.toString();
    }

}
