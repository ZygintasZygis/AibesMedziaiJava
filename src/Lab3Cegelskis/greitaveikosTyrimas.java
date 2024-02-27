/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab3Cegelskis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import laborai.studijosktu.BstSetKTUx2;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.SortedSetADTx;
import laborai.studijosktu.BstSetKTUx;
import laborai.gui.MyException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.BstSetKTUx;
import laborai.studijosktu.BstSetKTUx2;
import laborai.studijosktu.SortedSetADTx;

/**
 *
 * @author zygis
 */
public class greitaveikosTyrimas {
    
    public static final String FINISH_COMMAND = "finish";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("laborai.gui.messages");

    private static final String[] TYRIMU_VARDAI = {"containsBst", "containsList", "addBst", "addList"};
    private static final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;
    private final String[] errors;

    private final SortedSetADTx<Knyga> knygos1 = new BstSetKTUx(new Knyga(), Knyga.pagalPuslapius);
    private final SortedSetADTx<Knyga> knygos2 = new BstSetKTUx2(new Knyga());
    private final SortedSetADTx<Knyga> knygos3 = new AvlSetKTUx(new Knyga());
    private final List<Knyga> knyguList = new ArrayList<>();
    
    public greitaveikosTyrimas(){
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
        errors = new String[]{
            MESSAGES.getString("error1"),
            MESSAGES.getString("error2"),
            MESSAGES.getString("error3"),
            MESSAGES.getString("error4")
        };
    }
    
    public void pradetiTyrima(){
        try {
            SisteminisTyrimas();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public void SisteminisTyrimas() throws InterruptedException {
        try {
            for (int k : TIRIAMI_KIEKIAI) {
                Knyga[] knyguMas = knyguGamyba.generuotiIrIsmaisyti(k, 1.0);
                knygos1.clear();
                knygos2.clear();
                Knyga a5 = new Knyga("KNYGA23 CATEGORY23 8.77 AUTHOR23 122 2002");
                knygos3.clear();
                tk.startAfterPause();
                tk.start();
                for (Knyga a : knyguMas) {
                    knygos1.add(a);
                   
                }
                tk.finish(TYRIMU_VARDAI[2]);
                knyguList.addAll(Arrays.asList(knyguMas));
                tk.finish(TYRIMU_VARDAI[3]);
                
                knygos1.contains(a5);
                tk.finish(TYRIMU_VARDAI[0]);
                
                knyguList.contains(a5);
                tk.finish(TYRIMU_VARDAI[1]);
                tk.seriesFinish();
            }
            tk.logResult(FINISH_COMMAND);
        } catch (MyException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                tk.logResult(errors[e.getCode()] + ": " + e.getMessage());
            } else if (e.getCode() == 4) {
                tk.logResult(MESSAGES.getString("msg3"));
            } else {
                tk.logResult(e.getMessage());
            }
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
