package rmiMatrixCalulation;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

class clients1 {//提供给客户的程序

    public static void main(String[] args) throws RemoteException, NotBoundException {
        new Thread(new Clients(9365,"KNSY001")).start();
    }
}
class clients2 {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        new Thread(new Clients(9366,"KNSY002")).start();
    }

}
class clients3 {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        new Thread(new Clients(9367,"KNSY003")).start();
    }
}
class client4{
    public static void main(String[] args) throws RemoteException, NotBoundException {
        new Thread(new Clients(1111,"PPP001")).start();
    }
}