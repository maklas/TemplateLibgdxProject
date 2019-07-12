package ru.mnw.template.tests;


import com.badlogic.gdx.math.Vector2;
import ru.mnw.template.mnw.MNW;
import ru.mnw.template.utils.ClassUtils;
import ru.mnw.template.utils.StringUtils;

/**
 * Created by maklas on 04-Jan-18.
 */

public class Test {

    public static void main(String[] args){
        for (int i = 1; i < 46; i++) {
            System.out.println(StringUtils.addSpacesLeft(String.valueOf(i), 2) + ": " + f(i));
        }
    }

    private static int f(int x){
        if (x == 1) return 0;
        return f(x - 1) + (x - 1);
    }

    private static void countStrings(){
        System.out.println(ClassUtils.countStrings(MNW.PACKAGE, false, false, false));
    }

    private static void testLerp(){
        Vector2 a = new Vector2();
        Vector2 b = new Vector2();
        Vector2 c = new Vector2();

        b.set(100, 100);
        for (int i = 0; i < 6; i++) {
            a.set(-100, -100);
            System.out.println(a.lerp(b, i * 0.2f));
        }
    }
}
