package algo;

import java.util.HashMap;
import java.util.Map;

public class testGraph {
    public static void main(String[] args) {
        GraphWeighted graphWeighted = new GraphWeighted(true);

        NodeWeighted A = new NodeWeighted(11, "33010");
        NodeWeighted B = new NodeWeighted(2123, "33020");
        NodeWeighted C = new NodeWeighted(32, "33030");
        NodeWeighted D = new NodeWeighted(43, "33040");
        NodeWeighted E = new NodeWeighted(565, "33050");
        NodeWeighted F = new NodeWeighted(126, "33060");

        
        HashMap<String,NodeWeighted> map = new HashMap<String,NodeWeighted>();//Creating HashMap    
        map.put("33010",new NodeWeighted(1, "33010"));  //Put elements in Map  
        map.put("33020",new NodeWeighted(12, "33020"));    
        map.put("33050",new NodeWeighted(12, "33050"));  


        NodeWeighted temp = null;
        for(Map.Entry m : map.entrySet()){    
        	temp = (NodeWeighted) m.getValue();
            if(temp.name == "33010") {
            	break;
            }
           }  
        
        NodeWeighted temp2 = null;
        for(Map.Entry m : map.entrySet()){    
        	temp2 = (NodeWeighted) m.getValue();
            if(temp2.name == "33020") {
            	break;
            }
           }  
        
        NodeWeighted temp3 = null;
        for(Map.Entry m : map.entrySet()){    
        	temp3 = (NodeWeighted) m.getValue();
            if(temp3.name == "33050") {
            	break;
            }
           }  



        graphWeighted.addEdge(temp, temp2, 5);
        graphWeighted.addEdge(temp2, temp3, 45);
//
//        graphWeighted.addEdge(B, C, 70);
//        graphWeighted.addEdge(B, E, 3);
//        graphWeighted.addEdge(D, C, 50);
//        graphWeighted.addEdge(D, E, 8);
//        graphWeighted.addEdge(C, F, 78);
//        graphWeighted.addEdge(E, F, 7);
        
        
        //System.out.println(graphWeighted.getNodeNodeWeighted("33050").name);

        graphWeighted.DijkstraShortestPath(temp,temp3);
    }
}