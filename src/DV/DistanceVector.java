package DV;

import tp.Link;
import tp.Router;
import tp.Sender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DistanceVector {

    /**
     Jsuis pas trop sur de comment t'as implemente tes paquets, mais si tu peux ajouter des types pis tt,
     on rajoute DISTV, pis la valeur du message c'est la nouvelle valeur "weight" du Node qui est envoyé.

     type de paquet  : "DISTV" {
     msg = "<ValeurDeDistance>
     }

     */
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

    public static void transmitDistanceVector(HashMap<Integer, Integer> edgeTable, Router router, int weight){
        for(int e = 0; e < edgeTable.size(); e++){
            //TODO : Faire un paquet avec la valeur "weight" comme msg
            Sender s = new Sender(router);
            //TODO : envoyer le paquet par le sender
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
