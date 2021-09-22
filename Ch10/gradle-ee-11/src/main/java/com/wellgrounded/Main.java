package com.wellgrounded;

import javax.activation.CommandInfo;
import org.omg.CORBA.ORB;
import javax.transaction.TransactionRequiredException;
import javax.xml.bind.JAXB;
import javax.xml.ws.Action;
import javax.annotation.Resource;

public class Main {
    public static void main(String[] args) {
        System.out.println(CommandInfo.class.getName());
        System.out.println(ORB.class.getName());
        System.out.println(TransactionRequiredException.class.getName());
        System.out.println(JAXB.class.getName());
        System.out.println(Action.class.getName());
        System.out.println(Resource.class.getName());
    }
}
