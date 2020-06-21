package rmiMatrixCalulation;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/*
author : Juminiy
date : 2020.06.17
log : the first test :No Bugs ,the three methods run correctly !
 */

/*
author : juminiy
data : 2020.06.19  10:01
log :add the multipleThread to the Clients to support many users to use
 */

/*
author : juminiy
data : 2020.06.21
log : the service is multipleThread and only support the user who has been registered ,the service support a port and a signalName
and the Matrix format must be correct or the program will throw a MatrixFormatIllegalException and the user must use the supported port and
signalName or the program will throw a TransportException to tell the user the signalName is Illegal
 */
public class Service {//答案正确 没有BUG

    static int serviceAmounts=0;
    private Registry  registry;
    private CalcuInterface calcuInterface;

    public CalcuInterface getCalcuInterface() {
        return calcuInterface;
    }
    public void setCalcuInterface(CalcuInterface calcuInterface1){
        this.calcuInterface=calcuInterface1;
    }
    public Service(int port, String signalName) throws RemoteException, AlreadyBoundException {
        registry=LocateRegistry.createRegistry(port);
        calcuInterface=new CalcuImp(signalName);
        registry.bind(signalName, calcuInterface);
    }

    public static void main(String[] args) throws RemoteException {

        //服务器多线程并行跑 每个客户端计算独立运行 互不干扰
        //矩阵发送给 Client
        //服务器必须 颁发签名证书 客户端才能运行 一个签名只能有一个对象
        //一个对象对应的客户端开启一个线程，一个端口
        new Thread(new ServiceRun(9365,"KNSY001")).start();
        new Thread(new ServiceRun(9366,"KNSY002")).start();
        new Thread(new ServiceRun(9367,"KNSY003")).start();

    }
}

class ServiceRun implements Runnable{
    private int PayPort;
    private String PayName;//这个方法签名用户需要权限
    public ServiceRun(int PayPort,String PayName){
        this.PayName=PayName;
        this.PayPort=PayPort;
        Service.serviceAmounts++;
        System.out.println("=====>启动服务<====="+Service.serviceAmounts+" 用户签名 : "+PayName);
    }
    @Override
    public void run() {
        try {
            Service service=new Service(PayPort,PayName);
//            service.setCalcuInterface(new CalcuImp(this.PayName));
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
}