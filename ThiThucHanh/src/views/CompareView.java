package views;

import ultis.CompareAsc;
import ultis.CompareDes;

import java.util.Scanner;

public class CompareView {
    private Scanner scanner;
    private CompareDes compareDes;
    private CompareAsc compareAsc;
    private boolean flag = true;
    public  CompareView(){
        compareAsc = new CompareAsc();
        compareDes = new CompareDes();
        scanner = new Scanner(System.in);
    }
    public void showCompareView(){
        do{
            headCompareView();
            bodyCompareView();
        }while (flag);
    }
    public void headCompareView(){
        System.out.println("[1]: Giam dan");
        System.out.println("[2]: Tang dan");
        System.out.println("[3]: Quay lai");
    }
    public void bodyCompareView(){
        boolean flag = true;
        int number  = 0;
        do{
            try {
                System.out.print("Nhap option: ");
                number = scanner.nextInt();
                flag = false;
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);
        switch (number){
            case 1:{
                compareDes.desPrice();
                break;
            }
            case 2:{
                compareAsc.ascPrice();
                break;
            }
            case 3:{
                this.flag = false;
            }
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
