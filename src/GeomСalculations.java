import com.sun.org.apache.xpath.internal.SourceTree;
import org.jetbrains.annotations.Contract;

import java.util.Scanner;

public class GeomСalculations {
    private double coordinateX = 0;
    private double coordinateY = 0;
    private double rotationAngleInDegrees = 0;//угол поворота
    private double tiltAngleInDegrees = 0;//угол наклона - падения
    private double cornerSize = 0;//the size of the verge
    private double[] interimResults = {0, 0, 0, 0}; //x,y, угол поворота, угол наклона
    private double way = 0;
    private static final String TEXT_ENTER_X = "Enter coordinat X:";
    private static final String TEXT_ENTER_Y = "Enter coordinat Y:";
    private static final String TEXT_ENTER_TILT = "Enter the tilt angle:";
    private static final String TEXT_ENTER_ROTATION = "Enter the rotation angle:";
    private static final String TEXT_ENTER_SIZE = "Enter the sithe of the verge:";

    public void start() {
//        for (int i = 170; i<270; i = i + 10) {
//            choiceoOfMethod(6, 3, i, 60, interimResults, way);
//            System.out.println(way);
//        }
        reading();
        //firstWay(coordinateX, coordinateY, rotationAngleInDegrees, tiltAngleInDegrees, cornerSize);
        interimResults = choiceoOfMethod(coordinateX, coordinateY, rotationAngleInDegrees, tiltAngleInDegrees);
        System.out.println(way);
        interimResults = choiceoOfMethod(interimResults[0], interimResults[1], interimResults[2], interimResults[3]);
        System.out.println(way);
        interimResults = choiceoOfMethod(interimResults[0], interimResults[1], interimResults[2], interimResults[3]);
        System.out.println(way);

    }

    private double[] choiceoOfMethod(double x, double y, double rotationAngle, double tiltAngle) {
        double[] resultChoice = {0,0,0,0};
        if (rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270 || rotationAngle == 360) {
            way = way + 0;
        }
        if (0 < rotationAngle && 90 > rotationAngle) {
            resultChoice = afgo(x, y, rotationAngle, tiltAngle);
        }
        if (90 < rotationAngle && 180 > rotationAngle) {
            double tanORJ = 0;
            double jro = 0;
            tanORJ = y / x;
            jro = 360 - Math.toDegrees(Math.atan(tanORJ));
            if (jro > (180 + rotationAngle)) {
                resultChoice = agfo(x, y, rotationAngle, tiltAngle);
            }
            if (jro < (180 + rotationAngle)) {
                resultChoice = ofjb(x, y, rotationAngle, tiltAngle);
            }
        }
        if (180 < rotationAngle && 270 > rotationAngle) {
            resultChoice = ojfb(x, y, rotationAngle, tiltAngle);
        }
        return resultChoice;
    }

    private double[] test() {
        double[] loh = {1,2,3,4};
        return loh;
    }

    private void reading() {
        Scanner expressionElement = new Scanner(System.in);
        System.out.println(TEXT_ENTER_X);
        coordinateX = expressionElement.nextDouble();
        System.out.println(TEXT_ENTER_Y);
        coordinateY = expressionElement.nextDouble();
        System.out.println(TEXT_ENTER_TILT);
        tiltAngleInDegrees = expressionElement.nextInt();
        System.out.println(TEXT_ENTER_ROTATION);
        rotationAngleInDegrees = expressionElement.nextInt();
//        System.out.println(TEXT_ENTER_SIZE);
//        cornerSize = expressionElement.nextInt();
    }

    private double[] srfUniversal(double fr, double srf) { //resault {FS,RS,RSF}
        double[] resaultU = {0,0,0,0};
        resaultU[0] = fr * Math.tan(Math.toRadians(srf)); // return one of the coordinats fs
        resaultU[1] = fr / Math.cos(Math.toRadians(srf));//rs
        resaultU[2] = 90 - srf;//rsf
        return resaultU;
    }

    private double[] afgo(double go, double gr, double jrd, double tiltAngleInDegrees) {
        double frg = 0;
        double fsg = 0;
        double[] resaultAfgo = {0, 0, 0, 0};//resault {FS,RS,RSF}
        double fg = 0;
        double fo = 0;
        double fr = 0;
        double tanGSF = 0;
        double angl = 0;
        double gsf = 0;
        frg = 90 - jrd;
        fg = gr * Math.tan(Math.toRadians(frg));
        fo = fg + go; //return this is x, one of coordinats
        fr = gr / Math.cos(Math.toRadians(frg));
        resaultAfgo = srfUniversal(fr, tiltAngleInDegrees);
        way = way + resaultAfgo[1];
        tanGSF = fg / resaultAfgo[0];
        gsf = Math.toDegrees(Math.atan(tanGSF));
        fsg = 360 - gsf; // искомый угол поворота
        angl = tiltAngle(gsf,resaultAfgo[1],go);
        resaultAfgo[1] = fo;
        resaultAfgo[2] = fsg;
        resaultAfgo[3] = angl;
        return resaultAfgo;
    }

    private double[] agfo(double go, double gr, double jrd, double tiltAngleInDegrees) {
        double grf = 0;
        double fsg = 0;
        double[] resaultAgfo = {0, 0, 0, 0};//resault {FS,RS,RSF}
        double fg = 0;
        double fo = 0;
        double fr = 0;
        double tanFSG = 0;
        double angl = 0;
        grf = jrd - 90;
        fg = gr * Math.tan(Math.toRadians(grf));
        fo = go - fg;//return this is x, one of coordinats
        fr = gr / Math.cos(Math.toRadians(grf));
        resaultAgfo = srfUniversal(fr, tiltAngleInDegrees);
        way = way + resaultAgfo[1];
        tanFSG = fg / resaultAgfo[0];
        fsg = Math.toDegrees(Math.atan(tanFSG));// искомый угол поворота
        angl = tiltAngle(fsg,resaultAgfo[1],go);
        resaultAgfo[1] = fo;
        resaultAgfo[2] = fsg;
        resaultAgfo[3] = angl;
        return resaultAgfo;
    }

    private double[] ofjb(double rj, double oj, double jrd, double tiltAngleInDegrees) {
        double frj = 0;
        double ksj = 0;
        double[] resaultOfjb = {0, 0, 0, 0};//resault {FS,RS,RSF}
        double fj = 0;
        double fo = 0;
        double fr = 0;
        double tanJSF = 0;
        double jsf = 0;
        double angl = 0;
        frj = 180 - jrd;
        fj = rj * Math.tan(Math.toRadians(frj));
        fo = oj - fj;//return this is x, one of coordinats
        fr = rj / Math.cos(Math.toRadians(frj));
        resaultOfjb = srfUniversal(fr, tiltAngleInDegrees);
        way = way + resaultOfjb[1];
        tanJSF = fj / resaultOfjb[0];
        jsf = Math.toDegrees(Math.atan(tanJSF));
        ksj = 270 - jsf;// искомый угол поворота
        angl = tiltAngle(jsf,resaultOfjb[1],rj);
        resaultOfjb[0] = resaultOfjb[0];
        resaultOfjb[1] = fo;
        resaultOfjb[2] = ksj;
        resaultOfjb[3] = angl;
        return resaultOfjb;
    }

    private double[] ojfb(double rj, double oj, double jrd, double tiltAngleInDegrees) {
        double jrf = 0;
        double ksj = 0;
        double[] resaultOjfb = {0, 0, 0, 0};//resault {FS,RS,RSF}
        double jf = 0;
        double fo = 0;
        double fr = 0;
        double tanFSJ = 0;
        double fsj = 0;
        double angl = 0;
        jrf = jrd - 180;
        jf = rj * Math.tan(Math.toRadians(jrf));
        fo = oj + jf;//return this is x, one of coordinats
        fr = rj / Math.cos(Math.toRadians(jrf));
        resaultOjfb = srfUniversal(fr, tiltAngleInDegrees);
        way = way + resaultOjfb[1];
        tanFSJ = jf / resaultOjfb[0];
        fsj = Math.toDegrees(Math.atan(tanFSJ));
        ksj = 270 + fsj;// искомый угол поворота
        angl = tiltAngle(fsj,resaultOjfb[1],rj);
        resaultOjfb[0] = resaultOjfb[0];
        resaultOjfb[1] = fo;
        resaultOjfb[2] = ksj;
        resaultOjfb[3] = angl;
        return resaultOjfb;
    }

    private double tiltAngle(double s,double fs, double rg){
        double sg = 0;
        double tanRSG = 0;
        double rsg = 0;
        sg = fs / Math.cos(Math.toRadians(s));
        tanRSG = rg/sg;
        rsg = Math.toDegrees(Math.atan(tanRSG));
        return rsg;
    }

    private void firstWay(double jr, double jo, double jrd, double tiltAngleInDegrees, double r){
        double bj = 0;
        double ga = 0;
        double dr = 0;
        double tanJBR = 0;
        double jrb = 0;
        double jra = 0;
        double jbr = 0;
        double re = 0;
        bj = r - jo;
        jbr = Math.toDegrees(Math.atan(jr / bj));
        jrb = 90 - jbr;
        ga = r - jr;
        jra = 270 - Math.toDegrees(Math.atan(ga/jo));
        if(jrd <jrb){
            dr = jr / Math.cos(Math.toRadians(jrd));
            re = dr / Math.cos(Math.toRadians(tiltAngleInDegrees));
        }
        if(jrd > jrb && jrd < jra){
            double rbd = 0;
            double br = 0;
            double brd = 0;
            double bdr = 0;
            rbd = 45 - jbr;
            br = bj / Math.cos(Math.toRadians(jbr));
            brd = jrd - jrb;
            bdr = 180 - rbd - brd;
            dr = br * (Math.sin(Math.toRadians(rbd))/Math.sin(Math.toRadians(bdr)));
            re = dr / Math.cos(Math.toRadians(tiltAngleInDegrees));
        }
        if(jrd < jrb && 0 < jrb){
            double br = 0;
            dr = jr/Math.cos(Math.toRadians(jrd));
            re = dr / Math.cos(Math.toRadians(tiltAngleInDegrees));
        }
        if(jrd > jra && 270 < jrd){
            double grd = 0;
            grd = 270 - jrd;
            dr = jo / Math.cos(Math.toRadians(tiltAngleInDegrees));
            re = dr / Math.cos(Math.toRadians(tiltAngleInDegrees));
        }
        if(jrd > 270){
            double jro = 0;
            double drg = 0;
            jro = 360 - Math.toDegrees(Math.atan(jo / jr));
            drg = jrd - 270;

            if(jrd < jro){
                double rdg = 0;
                rdg = 90 - drg;
                dr = jo / Math.sin(Math.toRadians(rdg));
                re = dr / Math.cos(Math.toRadians(tiltAngleInDegrees));
            }
            if (jrd > jro && 361 > jrd){
                double drj = 0;
                drj = 90 - drg;
                dr = jr / Math.sin(Math.toRadians((90 - drj)));
                re = dr / Math.cos(Math.toRadians(tiltAngleInDegrees));
            }
        }
        way = way + re;
    }


}

