package rmiMatrixCalulation;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
/*
date: 2020.06.19 10:12 am.
log:   多线程级别
 */
public class Client  {

    private Registry registry;
    private CalcuInterface calcuInterface;


    public Client(int port, String signalName) throws RemoteException, NotBoundException {
        registry=LocateRegistry.getRegistry(port);
        calcuInterface =(CalcuInterface) registry.lookup(signalName);
    }

    public CalcuInterface getCalcuInterface() {
        return calcuInterface;
    }

}
class Clients implements Runnable{

    private Client client;
    private Scanner in;
    private int m,n,k;
    private String CName;
    private int CPort;
    Clients(int CPort,String CName) throws RemoteException, NotBoundException {
        client=new Client(CPort,CName);
        in=new Scanner(System.in);
        this.CName=CName;
    }

    @Override
    public void run() {

        try {
            //端口号要保持一致,否则找不到App,对象签名 是寻找对象的标志
            System.out.println("Hello "+ CName );
            while(true) {
                Options();
                int op=in.nextInt();
                switch (op){
                    case 1:Multiply();break;
                    case 2:ADD();break;
                    case 3:LAMBDA();break;
                    case 0:System.exit(0);break;
                    default:
                        System.out.println("暂时不提供服务");break;
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MatrixFormatException e) {
            e.printStackTrace();
        }
    }
    public void Options(){
        System.out.println("1.矩阵乘法");
        System.out.println("2.矩阵加法");
        System.out.println("3.矩阵数乘");
        System.out.println("0.关闭");
    }

    public void Multiply() throws RemoteException, MatrixFormatException {
        boolean tag=false;
        Matrix matrix1,matrix2;
        do {
            System.out.println("第一个矩阵的大小 : ");
            m=in.nextInt();
            n=in.nextInt();
            matrix1=new Matrix(m,n);
            System.out.println("第一个矩阵 : ");
            matrix1.InputMatrix();

            System.out.println("第二个矩阵的大小 : ");
            m=in.nextInt();
            n=in.nextInt();
            matrix2=new Matrix(m,n);
            System.out.println("第二个矩阵 : ");
            matrix2.InputMatrix();

            if(!matrix1.canPlus(matrix2)){
                System.out.println("您的矩阵形式输入有误,您要进行乘法运算,形式必须是(m,p)*(p,n)的,请检查后重新输入");
            }
            else tag=true;
        }while(tag==false);


        Matrix ans = client.getCalcuInterface().plus(matrix1,matrix2);//因为答案是在服务端显示的 那么这个答案必然要传到客户端

        System.out.println("Answer is : ");

        //矩阵接收 :
        ans.OutputMatrix();
    }
    public void ADD() throws RemoteException, MatrixFormatException {

        boolean tag=false;
        Matrix matrix1,matrix2;
        do{
            System.out.println("第一个矩阵的大小 : ");
            m=in.nextInt();
            n=in.nextInt();
            matrix1=new Matrix(m,n);
            System.out.println("第一个矩阵 : ");
            matrix1.InputMatrix();

            System.out.println("第二个矩阵的大小 : ");
            m=in.nextInt();
            n=in.nextInt();
            matrix2=new Matrix(m,n);
            System.out.println("第二个矩阵 : ");
            matrix2.InputMatrix();
            if(!matrix1.isSamesize(matrix2)){
                System.out.println("您的矩阵形式输入有误,加法运算必须是同型的,请检查后重新输入");
            }
            else{
                tag=true;
            }
        }while(tag==false);


        Matrix ans=client.getCalcuInterface().add(matrix1,matrix2);

        System.out.println("Answer is : ");


        ans.OutputMatrix();
    }
    public void LAMBDA() throws RemoteException {

        System.out.println("矩阵的大小 : ");
        m=in.nextInt();
        n=in.nextInt();
        Matrix matrix1=new Matrix(m,n);
        System.out.println("矩阵 : ");
        matrix1.InputMatrix();

        System.out.println("数字");
        k=in.nextInt();

        Matrix ans=client.getCalcuInterface().lambdaPlus(matrix1,k);

        System.out.println("Answer is : ");
        ans.OutputMatrix();

    }

}


