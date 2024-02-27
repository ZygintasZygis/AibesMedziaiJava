/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab3Cegelskis;

import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.SetADT;

/**
 *
 * @author zygis
 */
public class knyguApskaita {
    
    public static SetADT<String> automobiliuMarkes(Knyga[] knygos) {
        SetADT<Knyga> uni = new BstSetKTU<>(Knyga.pagalPav);
        SetADT<String> kart = new BstSetKTU<>();
        for (Knyga a : knygos) {
            int sizeBefore = uni.size();
            uni.add(a);

            if (sizeBefore == uni.size()) {
                kart.add(a.getName());
            }
        }
        return kart;
    }
}
