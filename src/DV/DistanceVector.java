package DV;

import tp.Link;
import tp.Router;
import tp.Sender;

import java.net.DatagramPacket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DistanceVector {

    public static void updateTable(int port, String msg, HashMap<Integer, Integer> edgeTable) {
        int newVerticeWeight = Integer.parseInt(msg);
        edgeTable.put(port, newVerticeWeight);
    }

    public static int computeDistanceVector(ArrayList<Link> links, HashMap<Integer, Integer> edgeTable){
        int shortestPath = -1;

        for(int e = 0; e < edgeTable.size() ; e++ ){
            //aller chercher la longueur du chemin pour chaque point voisin
            int newPath = edgeTable.get(e);

            //lui ajouter la longueur du "link"
            int port = getKey(edgeTable, newPath);

            for(int y = 0; y < links.size(); y++){
                if(port == links.get(y).to_port){
                    newPath += links.get(y).cost;
                    break;
                }
            }

            //Verifie s'il s'agit du plus court chemin pour ce point.
            if(shortestPath == -1 || shortestPath > newPath){
                shortestPath = newPath;
            }
        }

        return shortestPath;
    }

    public static void transmitDistanceVector(HashMap<Integer, Integer> edgeTable, Router router, int weight) throws UnknownHostException {
        for (Integer port : edgeTable.keySet()) {
            DatagramPacket dp = PacketFactory.createDvPacket(weight, port);

            //TODO : envoyer le paquet par le sender
            Sender s = new Sender(router);
        }
    }

    public static void removeFromTable(HashMap<Integer, Integer> edgeTable, int port){
        edgeTable.remove(port);
    }

    //retourne la valeur cle d'une hashmap en echange de sa valeur
    public static <K, V> K getKey(Map<K,V> map, V value){
        for(Map.Entry<K, V> entry : map.entrySet()) {
            if(value.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }
}
