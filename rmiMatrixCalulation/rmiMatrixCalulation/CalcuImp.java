package rmiMatrixCalulation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcuImp extends UnicastRemoteObject implements CalcuInterface {
    private String SName;
    private rmiMatrixCalulation.MatrixCalculation mCal;
    public CalcuImp(String SName) throws RemoteException {
        super();
        this.SName=SName;
    }
    public CalcuImp() throws RemoteException {
        super();
    }

    public Matrix add(rmiMatrixCalulation.Matrix p1, Matrix p2) throws RemoteException, MatrixFormatException {
        mCal=new rmiMatrixCalulation.MatrixCalculation(p1,p2);
        System.out.println(SName+"进行了加法运算,结果为 : ");
        mCal.Add().OutputMatrix();
        return mCal.Add();
    }

    @Override
    public Matrix plus(Matrix p1, Matrix p2) throws RemoteException, MatrixFormatException {
        mCal=new MatrixCalculation(p1,p2);
        System.out.println(SName+"进行了乘法运算,结果为 : ");
        mCal.Plus().OutputMatrix();
        return mCal.Plus();
    }

    @Override
    public Matrix lambdaPlus(Matrix p,int k) throws RemoteException {
        Matrix res=p.lambdaPlusx(k);
        System.out.println(SName+"进行了数乘运算,结果为 : ");
        res.OutputMatrix();
        return res;
    }
}
