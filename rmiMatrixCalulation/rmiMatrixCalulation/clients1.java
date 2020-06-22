package rmiMatrixCalulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedHashMap;

class clients1 {//提供给客户的程序,这是运行在不同主机上的 ...所以要检查
    public static void main(String[] args) throws IOException, NotBoundException {
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
class clients4{
    public static void main(String[] args) throws RemoteException, NotBoundException {
        new Thread(new Clients(1111,"PPP001")).start();
    }
}