package DV.Tests;

import DV.DVMessageSender;
import tp.Link;
import tp.Router;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class RunDVTestLocal {

    /**
     * Pour tester DV, on recree le graph de l'enonce. On essaie de passer un message de l'hote 1 vers l'hote 2.
     * On test d'abord les entrees/sorties des ports de facon locale.
     */
    public static void main(String[] args) throws UnknownHostException {
        //Init le graph de router de l'enonce

        //init les liens entre routeurs
        //Send port: xxx1, Receiveport xxx0
        Link aLinkB = new Link( 5,1001, 2000);
        Link aLinkD = new Link(45, 1001, 4000);
        ArrayList<Link> aLinks = new ArrayList<Link>();
        aLinks.add(aLinkB);
        aLinks.add(aLinkD);

        Link bLinkA = new Link(5, 2001, 1000);
        Link bLinkC = new Link(70, 2001, 3000);
        Link bLinkE = new Link(3, 2001, 5000);
        ArrayList<Link> bLinks = new ArrayList<Link>();
        bLinks.add(bLinkA);
        bLinks.add(bLinkC);
        bLinks.add(bLinkE);

        Link cLinkB = new Link(70, 3001, 2000);
        Link cLinkD = new Link(50, 3001, 4000);
        Link cLinkF = new Link(78, 3001, 6000);
        ArrayList<Link> cLinks = new ArrayList<Link>();
        cLinks.add(cLinkB);
        cLinks.add(cLinkD);
        cLinks.add(cLinkF);

        Link dLinkA = new Link(45, 4001, 1000);
        Link dLinkC = new Link(50, 4001, 3000);
        Link dLinkE = new Link(8, 4001, 5000);
        ArrayList<Link> dLinks = new ArrayList<Link>();
        dLinks.add(dLinkA);
        dLinks.add(dLinkC);
        dLinks.add(dLinkE);

        Link eLinkB = new Link(3, 5001, 2000);
        Link eLinkD = new Link(8, 5001, 4000);
        Link eLinkF = new Link(7, 5001, 6000);
        ArrayList<Link> eLinks = new ArrayList<Link>();
        eLinks.add(eLinkB);
        eLinks.add(eLinkD);
        eLinks.add(eLinkF);

        Link fLinkC = new Link(78, 6001, 3000);
        Link fLinkE = new Link(7, 6001, 5000);
        ArrayList<Link> fLinks = new ArrayList<Link>();
        fLinks.add(fLinkC);
        fLinks.add(fLinkE);

        //init les routers
        Router routerA = new Router("routerA", 1000, 1001, aLinks);
        Router routerB = new Router("routerB", 2000, 2001, bLinks);
        Router routerC = new Router("routerC", 3000, 3001, cLinks);
        Router routerD = new Router("routerD", 4000, 4001, dLinks);
        Router routerE = new Router("routerE", 5000, 5001, eLinks);
        Router routerF = new Router("routerF", 6000, 6001, fLinks);

        //init les hosts
        Router host1 = new Router("host1", 500, 501, null);
        Router host2 = new Router("host2", 600, 601, null);

        /** Actual test */
        String msg = "Hello World!";
        DVMessageSender sender = new DVMessageSender(msg, 500, 1000);

    }
}
